package com.example.be_java_hisp_w23_g3.repository.product;

import com.example.be_java_hisp_w23_g3.entity.Post;

public interface ProductRepository {
    Long getNextId();
    Post save(Post post);
}
