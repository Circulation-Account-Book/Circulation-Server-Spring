package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();


    public Article findByEmail(String email);



    //ArrayList<Article> findby(String email);




}
