package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
public class ProductController {

    @Value("${spring.application.name}")
    private String appTitle;

    @Value("${spring.application.caption}")
    private String appCaption;

    @RequestMapping("/")
    String index(final Model model) {

        // th:text="${appTitle}"
        model.addAttribute("appTitle", appTitle);

        //th:text="${appCaption}"
        model.addAttribute("appCaption", appCaption);

        // th:each="product, iterStat : ${products}"
        model.addAttribute("products", ProductLoader.loadProducts());

        // <!--/*
        // */-->

        return "index";
    }

}
