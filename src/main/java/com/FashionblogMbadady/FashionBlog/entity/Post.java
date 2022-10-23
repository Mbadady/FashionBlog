package com.FashionblogMbadady.FashionBlog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "category")
    private String category; // the category such as classic, traditional etc

    @Column(name = "description")
    private  String description; // the body of the post under the category

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp createAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp updatedAt;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Like> like;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", createAt=" + createAt +
                ", updatedAt=" + updatedAt +
                ", comments=" + comments +
                ", like=" + like +
                '}';
    }
}
