package com.side.bmarket.domain.product.service;

import com.side.bmarket.domain.prodcut.entity.Likes;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.LikeRepository;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.prodcut.service.LikeService;
import com.side.bmarket.domain.product.support.LikeFixture;
import com.side.bmarket.domain.product.support.ProductFixture;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import com.side.bmarket.domain.user.support.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {
    Users user = UserFixture.createUser("테스트 유저1");
    Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 10);

    @InjectMocks
    LikeService likeService;

    @Mock
    LikeRepository likeRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    UserRepository userRepository;

    @DisplayName("좋아요를 추가합니다.")
    @Test
    void createLikeTest() {
        // given
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product1));

        // when
        likeService.createLike(1L, 1L);

        // then
        then(likeRepository).should().save(any());
    }

    @DisplayName("좋아요를 삭제합니다.")
    @Test
    void deleteLikeTest() {
        // given
        Likes likes = LikeFixture.createLikes(user, product1);
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product1));
        given(likeRepository.findByUsersIdAndProductsId(any(), any())).willReturn(Optional.ofNullable(likes));

        // when
        likeService.deleteLike(1L, 1L);

        // then
        then(likeRepository).should().delete(any());
    }


}
