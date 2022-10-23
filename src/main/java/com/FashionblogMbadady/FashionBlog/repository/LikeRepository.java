package com.FashionblogMbadady.FashionBlog.repository;

import com.FashionblogMbadady.FashionBlog.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findPostById(Long postId);
}
