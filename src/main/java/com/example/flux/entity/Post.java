package com.example.flux.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @CreatedDate
    private LocalDate date;

    @ManyToOne
    private User user;

    public Post updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

}
