package com.FashionblogMbadady.FashionBlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "likes")
public class Like {
    // Creating the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private  String email;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", post=" + post +
                '}';
    }


// defining the no argument constructor

    // defining the all argument constructor

    // defining the getters and setters

    // defining the toString()


}
