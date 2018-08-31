package com.borzdykooa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс, ответственный за отображение страницы успешного выполнения операции
 */
@Controller
public class SuccessController {

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
