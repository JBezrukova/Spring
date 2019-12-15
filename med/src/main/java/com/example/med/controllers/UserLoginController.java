package com.example.med.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {
    private static ModelAndView modelAndView = new ModelAndView("login");

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            modelAndView.addObject("error", "Неверный ввод логина или пароля. Попробуйте еще раз");
        }
        return modelAndView;
    }
}
