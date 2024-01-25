package com.side.bmarket.domain.product.support;

import com.side.bmarket.domain.prodcut.entity.Likes;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.user.entity.Users;

public class LikeFixture {
    public static Likes createLikes(Users users, Products products) {
        return Likes.builder()
                .users(users)
                .products(products)
                .build();
    }
}
