package com.borzdykooa.controller;

import com.borzdykooa.entity.Trainer;
import com.borzdykooa.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Класс, ответственный за отображение списка сущности Trainer
 */
@Controller
public class GetAllTrainersController {

   @Autowired
   private TrainerService trainerService;

    @GetMapping("/trainers")
    public String showAllTrainers(Model model) {
        List<Trainer> trainers = trainerService.findAll();
        model.addAttribute("trainers", trainers);
        return "trainers";
    }

}
