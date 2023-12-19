package com.example.be_java_hisp_w23_g3.service.product;

import com.example.be_java_hisp_w23_g3.dto.request.PostRequestDTO;
import com.example.be_java_hisp_w23_g3.dto.response.FollowedPostsListDTO;
import com.example.be_java_hisp_w23_g3.dto.response.PostResponseDTO;
import com.example.be_java_hisp_w23_g3.entity.Post;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.exception.AlreadyExistsProductException;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.product.ProductRepository;
import com.example.be_java_hisp_w23_g3.repository.seller.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.util.DTOMapper;
import com.example.be_java_hisp_w23_g3.util.PostMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponseDTO postProduct(PostRequestDTO postRequestDTO) {

        // If user is not a seller, turn it into a seller
        Seller seller = sellerRepository.findSellerById(postRequestDTO.getUserId());

        if (seller == null) {
            User user = userRepository.findUserById(postRequestDTO.getUserId());

            if (user == null) {
                throw new NotFoundException("User with id " + postRequestDTO.getUserId() + " not found");
            }

            // Turn user into seller
            seller = Seller.build(user);

            // Save seller to repository and delete user from repository
            sellerRepository.save(seller);
            userRepository.deleteUserById(user.getId());
        }

        // Check if product is already posted (id is unique)
        Long productId = postRequestDTO.getProduct().getProductId();

        if (seller.getPosts().containsKey(productId)) {
            throw new AlreadyExistsProductException(
                "Product with id " + productId + " already posted"
            );
        }

        Long id = productRepository.getNextId();
        Post post = PostMapper.toPost(postRequestDTO, seller, id);

        // Save product to repository and add it to seller's posts
        productRepository.save(post);
        seller.getPosts().put(id, post);

        return PostMapper.toPostResponseDTO(post);
    }

    @Override
    public FollowedPostsListDTO followedPostsList(Long userId, String order) {
        User user = userRepository.findUserByIdOptional(userId)
                .or(() -> sellerRepository.findSellerByIdOptional(userId))
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));


        List<Long> followedSellersIds = user.getFollowing().stream().map(Seller::getId).toList();

        List<Post> allFollowedByUser = productRepository.readBatchBySellerIds(followedSellersIds).stream()
                .filter(post -> !post.getDate().isBefore(LocalDate.now().minusWeeks(2))).toList();

        return PostMapper.mapToFollowedPostsListDTO(allFollowedByUser, userId, order);
    }
}
