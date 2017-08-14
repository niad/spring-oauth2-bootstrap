package kr.pe.ned.resourceserver.controller;


import kr.pe.ned.resourceserver.model.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping()
    public Object list() {
        List<Article> list = buildDummies();
        return list;
    }

    private List<Article> buildDummies() {
        List<Article> list = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            list.add(new Article(1L, "테스트제목1", "테스트내용1"));
        }
        return list;
    }

}
