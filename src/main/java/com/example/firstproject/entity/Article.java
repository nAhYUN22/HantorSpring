package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity // entity임을 선언, JPA 에서 제공하는 어노테이션임, "Article" 테이블 생성
public class Article {
    @Id // 엔티티의 대푯값
    @GeneratedValue // 자동 생성
    private Long id;
    @Column // DB에서 각 열과 연결
    private String title;
    @Column // DB에서 각 열과 연결
    private String content;

    public Article() {

    }
}
