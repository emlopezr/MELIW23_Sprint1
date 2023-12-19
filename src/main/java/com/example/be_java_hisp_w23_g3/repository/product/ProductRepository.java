package com.example.be_java_hisp_w23_g3.repository.product;

import com.example.be_java_hisp_w23_g3.entity.Post;

import java.util.List;

public interface ProductRepository {
    Long getNextId();
    Post save(Post post);
    List<Post> findAll();
}
