package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;

import java.util.Optional;

public interface SellerRepository {
    Seller findSellerById(Long sellerId);
}