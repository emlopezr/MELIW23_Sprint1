package com.example.be_java_hisp_w23_g3.repository.seller;

import com.example.be_java_hisp_w23_g3.entity.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerRepositoryImpl implements  SellerRepository{

    List<Seller> sellers = new ArrayList<>();
    @Override
    public Seller findSellerById(Long sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst().orElse(null);
    }
}
