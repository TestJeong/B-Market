package com.side.bmarket.domain.prodcut.repository;

import com.side.bmarket.domain.prodcut.entity.Likes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUsersIdAndProductsId(Long userId, Long productId);

    Slice<Likes> findByUsersId(Long userId, Pageable pageable);

}
