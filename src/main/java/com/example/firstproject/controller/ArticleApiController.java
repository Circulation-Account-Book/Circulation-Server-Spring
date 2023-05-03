package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController // RestAPI용 컨트롤러 데이터(JSON)를 반환
public class ArticleApiController {
    @Autowired //DI
    private  ArticleRepository articleRepository;


    //전체 인원 불러오기
    @GetMapping("/api/articles")
    public List<Article> index(){

        return articleRepository.findAll();
    }
    //특정 id 불러오기
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){

        return articleRepository.findById(id).orElse(null);
    }

    //중복체크
    //아이디 만들때 이미 중복인 이메일이 DB에 있으면 오류뜸
    @GetMapping("/check/{email}")
    public ResponseEntity<Article> check(@PathVariable String email) {
        Article result=null;
        result=articleRepository.findByEmail(email);
        log.info(String.valueOf(result));
        if(result!=null){
            log.info("중복되었습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else {
            log.info("중복x");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }


    }
    @PostMapping("/check1")
    public ResponseEntity<Article> check1(@RequestBody String dto){

        if(articleRepository.findByEmail(dto)!=null){
            log.info("중복되었습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else{
            log.info("중복x");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PostMapping("/check")
    public ResponseEntity<Article> check(@RequestBody ArticleForm dto){
        Article article=dto.toEntity();
        if(articleRepository.findByEmail(article.getEmail())!=null){
            log.info("중복되었습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else{
            log.info("중복x");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }



    //회원가입
    @PostMapping("/api/articles")
    public Article create(@RequestBody @Valid ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

//    //로그인 구현
//    @PostMapping("/login")
//    public String login(@RequestBody )


}
