package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity // entity임을 선언, JPA 에서 제공하는 어노테이션임, "Article" 테이블 생성
public class Article {
    @Id // 엔티티의 대푯값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;
    @Column // DB에서 각 열과 연결
    private String title;
    @Column // DB에서 각 열과 연결
    private String content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        } if(article.content != null){
            this.content = article.content;
        }
    }

//    public Long getId() {
//        return id;
//    }
}
