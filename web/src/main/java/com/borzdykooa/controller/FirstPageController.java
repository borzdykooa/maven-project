package com.borzdykooa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс, ответственный за отображение первой страницы приложения
 */
@Controller
public class FirstPageController {

    @GetMapping("/greeting")
    public String showGreetingPage() {
        return "greeting";
    }
}
