package com.example.be_java_hisp_w23_g3.util;

import com.example.be_java_hisp_w23_g3.dto.ProductDTO;
import com.example.be_java_hisp_w23_g3.dto.request.PostRequestDTO;
import com.example.be_java_hisp_w23_g3.dto.response.PostResponseDTO;
import com.example.be_java_hisp_w23_g3.entity.Post;
import com.example.be_java_hisp_w23_g3.entity.Product;
import com.example.be_java_hisp_w23_g3.entity.Seller;

public class PostMapper {

    public static PostResponseDTO toPostResponseDTO(Post post){
        ProductDTO productDTO = ProductMapper.toProductDTO(post.getProduct());

        return new PostResponseDTO(
            post.getSeller().getId(),
            post.getId(),
            post.getDate(),
            productDTO,
            post.getCategory(),
            post.getPrice()
        );
    }

    public static Post toPost(PostRequestDTO postRequestDTO, Seller seller, Long id){
        Product product = ProductMapper.toProduct(postRequestDTO.getProduct());

        return Post.build(
            id,
            seller,
            product,
            postRequestDTO.getDate(),
            postRequestDTO.getCategory(),
            postRequestDTO.getPrice()
        );
    }

}
