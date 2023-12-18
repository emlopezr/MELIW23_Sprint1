package com.example.be_java_hisp_w23_g3.repository.seller;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SellerRepositoryImpl implements SellerRepository{

    private final List<Seller> sellers;

    public SellerRepositoryImpl() {
        this.sellers = new ArrayList<>();
    }

    @PostConstruct
    private void load() {
        sellers.addAll(List.of(
                Seller.build(User.build(7L, "seller1"),
                        new HashSet<>(
                                Arrays.asList(
                                        User.build(13L, "abcdef"),
                                        User.build(6L, "bcdefg"),
                                        User.build(15L, "cdefgh")
                                )),
                        new HashMap<>()),
                Seller.build(User.build(8L, "seller2")),
                Seller.build(User.build(9L, "seller3")),
                Seller.build(User.build(10L, "seller4")),
                Seller.build(User.build(11L, "seller5")),
                Seller.build(User.build(12L, "seller6"))
        ));
    }

    @Override
    public Seller findSellerById(Long sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst().orElse(null);
    }

    @Override
    public Optional<Seller> findSellerByIdOptional(Long sellerId) {
        return sellers.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst();
    }

    @Override
    public Seller save(Seller seller) {
        sellers.add(seller);
        return seller;
    }
}
