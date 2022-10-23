package com.FashionblogMbadady.FashionBlog.repository;

import com.FashionblogMbadady.FashionBlog.entity.Comment;
import com.FashionblogMbadady.FashionBlog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByPost(Post post);

    List<Comment> findByEmail(String email);
}
