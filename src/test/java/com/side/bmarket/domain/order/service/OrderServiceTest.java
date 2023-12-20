package com.side.bmarket.domain.order.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.support.CartFixture;
import com.side.bmarket.domain.cart.support.CartItemFixture;
import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.support.CategoryFixture;
import com.side.bmarket.domain.category.support.SubCategoryFixture;
import com.side.bmarket.domain.order.repository.OrderRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    Users user;
    Categorys category;
    SubCategorys subCategory;
    Products product;
    Carts cart;
    CartItems cartItem;

    @InjectMocks
    OrderService orderService;

    @Mock
    ProductRepository productRepository;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        user = UserFixture.createUser("테스트 유저1");
        category = CategoryFixture.createCategory("카테고리1");
        subCategory = SubCategoryFixture.createSubCategory(category, "서브카테고리1");
        product = ProductFixture.createProduct(subCategory, "상품1", 1000, 100, 0, 0);
        cart = CartFixture.createCart(user);
        cartItem = CartItemFixture.createCartItem(cart, product, 1);
    }

    @DisplayName("주문 아이템을 생성합니다.")
    @Test
    void createOrderItem() {
        // given
        Products product1 = ProductFixture.createProduct(subCategory, "상품2", 1000, 100, 0, 0);
        Products product2 = ProductFixture.createProduct(subCategory, "상품3", 1000, 100, 0, 0);
        Products product3 = ProductFixture.createProduct(subCategory, "상품4", 1000, 100, 0, 0);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 1);
        CartItems cartItem2 = CartItemFixture.createCartItem(cart, product2, 1);
        CartItems cartItem3 = CartItemFixture.createCartItem(cart, product3, 1);

        List<CartItems> cartItemsList = List.of(cartItem1, cartItem2, cartItem3);

        given(cartItemRepository.findByCartIdIn(any())).willReturn(cartItemsList);

        // when
        List<Long> cartItemIdList = Arrays.asList(1L, 2L);
        orderService.createOrderItem(cartItemIdList);

        // then
     }

}