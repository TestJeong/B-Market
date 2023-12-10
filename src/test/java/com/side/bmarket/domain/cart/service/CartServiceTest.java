package com.side.bmarket.domain.cart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.cart.support.CartFixture;
import com.side.bmarket.domain.cart.support.CartItemFixture;
import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.support.CategoryFixture;
import com.side.bmarket.domain.category.support.SubCategoryFixture;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.product.support.ProductFixture;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import com.side.bmarket.domain.user.support.UserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    Users user;
    Categorys category;
    SubCategorys subCategory;
    Products product;
    Carts cart;
    CartItems cartItem;

    @InjectMocks
    CartService cartService;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = UserFixture.createUser("테스트1");
        category = CategoryFixture.createCategory("카테고리1");
        subCategory = SubCategoryFixture.createSubCategory(category, "서브카테고리1");
        product = ProductFixture.createProduct(subCategory, "상품1", 1000, 100, 0, 0);
        cart = CartFixture.createCart(user);
        cartItem = CartItemFixture.createCartItem(cart, product, 1);
    }

    @DisplayName("장바구니에 상품을 저장합니다.")
    @Test
    void saveCartItem() {
        // given
        given(userRepository.findById(any())).willReturn(
                Optional.ofNullable(user));
        given(cartRepository.findByUsersId(any())).willReturn(
                Optional.ofNullable(cart));
        given(productRepository.findById(any())).willReturn(
                Optional.ofNullable(product));
        given(cartItemRepository.save(any())).willReturn(
                cartItem);

        // when
        cartService.saveCartItem(1L, 1L, 1);

        // then
        then(cartItemRepository).should().save(any());
    }

    @DisplayName("장바구니에 같은 상품이 있을경우 수량만 증가합니다.")
    @Test
    void updateCartItemQuantity() {
        // given
        given(cartItemRepository.findById(any())).willReturn(
                Optional.ofNullable(cartItem));

        // when
        cartService.updateCartItemQuantity(1L, 2);

        // then
        assertThat(cartItem.getProductQuantity()).isEqualTo(3);
    }


}