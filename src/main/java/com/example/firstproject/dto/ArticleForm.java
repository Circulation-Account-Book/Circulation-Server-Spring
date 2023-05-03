package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@ToString

public class ArticleForm {

    private Long id;
    @NotEmpty
    @Size(min =2)
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String birth;
    @NotEmpty
    private String gen;
    @NotEmpty
    @Size(min =2,max=20)
    private String password;

    public Article toEntity() {
        return new Article(id,email,name,birth,gen,password);
    }

}

