package com.example.be_java_hisp_w23_g3.repository.seller;

import com.example.be_java_hisp_w23_g3.entity.Seller;

public interface SellerRepository {
    Seller findSellerById(Long sellerId);
}
