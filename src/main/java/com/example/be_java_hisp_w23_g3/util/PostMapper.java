package com.example.be_java_hisp_w23_g3.util;

import com.example.be_java_hisp_w23_g3.dto.ProductDTO;
import com.example.be_java_hisp_w23_g3.dto.request.PostRequestDTO;
import com.example.be_java_hisp_w23_g3.dto.response.PostResponseDTO;
import com.example.be_java_hisp_w23_g3.entity.Post;
import com.example.be_java_hisp_w23_g3.entity.Product;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        // Parse string of dd-MM-yyyy to LocalDate
        ArgumentValidator.validateRequired(postRequestDTO.getDate(), "Date is required");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(postRequestDTO.getDate(), formatter);

        return Post.build(
            id,
            seller,
            product,
            date,
            postRequestDTO.getCategory(),
            postRequestDTO.getPrice()
        );
    }

}
