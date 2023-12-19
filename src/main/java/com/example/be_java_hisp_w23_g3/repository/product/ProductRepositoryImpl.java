package com.example.be_java_hisp_w23_g3.repository.product;

import com.example.be_java_hisp_w23_g3.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    List<Post> posts;

    public ProductRepositoryImpl(){
        posts = new ArrayList<>();
    }

    @Override
    public Long getNextId() {
        return (long) (posts.size() + 1);
    }

    @Override
    public List<Post> readBatchBySellerIds(List<Long> sellerIds) {
        return posts.stream().filter(post -> sellerIds.contains(post.getSeller().getId())).toList();
    }

    @Override
    public Post save(Post post) {
        posts.add(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

}
