package com.hnagano.controllers;

import com.hnagano.models.User;
import com.hnagano.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("login")
public class LoginController {

    private UserServices userServices;

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping(value="/loginPage",method=RequestMethod.GET)
    public String login(HttpSession session, ModelMap model) {
        if (session.getAttribute("user")!=null) {
            return "redirect:/";
        }
        model.addAttribute("modele", new User());
        return "login/loginPage";
    }


    @PostMapping(value = "/loginPage")
    public String doLogin(HttpSession session, User user, ModelMap model) {

        if ("".equals(user.getUsername().trim()) || "".equals(user.getPassword().trim())) {
            model.addAttribute("message", "Veuillez remplir les 2 champs");
            user.setPassword("");
            model.addAttribute("modele", user);
            return "login/loginPage";
        }

        User u = userServices.findUser(user.getUsername(), user.getPassword());
        System.out.println(u);
        if (u == null || !u.getPassword().equals(user.getPassword())) {
            model.addAttribute("message", "Informations incorrectes");
            user.setPassword("");
            model.addAttribute("modele", user);
            return "login/loginPage";
        }
        user.setPassword("");
        session.setAttribute("user", u);
        return "redirect:/";
    }

    @RequestMapping(value="/logoutPage")
    public String doLogout(HttpSession session, ModelMap model) {
        session.invalidate();
        model.addAttribute("modele", new User());
        return "login/logoutPage";
    }

}
