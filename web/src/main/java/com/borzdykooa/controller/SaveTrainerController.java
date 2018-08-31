package com.borzdykooa.controller;

import com.borzdykooa.entity.Trainer;
import com.borzdykooa.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Класс, ответственный за отображение страницы для сохранения сущности Trainer
 */
@Controller
public class SaveTrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/saveTrainer")
    public String openTrainerPage(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "save-trainer";
    }

    @PostMapping("/saveTrainer")
    public String saveMedicine(Trainer trainer) {
        trainerService.save(trainer);
        return "redirect:/success";
    }
}
