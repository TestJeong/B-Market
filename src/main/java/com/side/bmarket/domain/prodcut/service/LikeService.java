package com.side.bmarket.domain.prodcut.service;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.entity.Likes;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.exception.NotFoundProductException;
import com.side.bmarket.domain.prodcut.repository.LikeRepository;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    //    찜하기 추가
    @Transactional
    public void createLike(Long productID) {
        Users user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        Products product = productRepository.findById(productID)
                .orElseThrow(() -> new NotFoundProductException("해당 상품이 없습니다"));

        Likes like = Likes.builder()
                .users(user)
                .products(product)
                .build();

        likeRepository.save(like);
    }

    //    찜하기 삭제
    @Transactional
    public void deleteLike(Long productID) {
        Users user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        Products product = productRepository.findById(productID)
                .orElseThrow(() -> new NotFoundProductException("해당 상품이 없습니다"));

        Likes like = likeRepository.findByUsersIdAndProductsId(user.getId(), product.getId())
                .orElseThrow(() -> new RuntimeException("해당 정보가 없습니다"));

        likeRepository.delete(like);
    }

    //    찜한 목록 리스트
    @Transactional(readOnly = true)
    public List<ProductDto> findLikeByUser() {
        Users user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        List<Likes> likesList = likeRepository.findByUsersId(user.getId());

        return likesList.stream()
                .map((i) -> ProductDto.of(i.getProducts()))
                .collect(Collectors.toList());

    }

}
