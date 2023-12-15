package com.example.be_java_hisp_w23_g3.repository.seller;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerRepositoryImpl implements SellerRepository{

    @Override
    public Seller findSellerById(Long sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst().orElse(null);
    }
}
