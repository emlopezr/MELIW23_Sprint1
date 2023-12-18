package com.example.be_java_hisp_w23_g3.service.product;

import com.example.be_java_hisp_w23_g3.dto.request.PostRequestDTO;
import com.example.be_java_hisp_w23_g3.dto.response.PostResponseDTO;
import com.example.be_java_hisp_w23_g3.entity.Post;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.product.ProductRepository;
import com.example.be_java_hisp_w23_g3.repository.user.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.util.PostMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public PostResponseDTO postProduct(PostRequestDTO postRequestDTO) {
        Seller seller = sellerRepository.findSellerById(postRequestDTO.getUserId());

        // Check if seller really exists
        if (seller == null) {
            throw new NotFoundException(
                "Seller with id " + postRequestDTO.getUserId() + " not found"
            );
        }

        Long id = productRepository.getNextId();
        Post post = PostMapper.toPost(postRequestDTO, seller, id);

        // Save product to repository and add it to seller's posts
        productRepository.save(post);
        seller.getPosts().put(id, post);

        return PostMapper.toPostResponseDTO(post);
    }
}
