/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bryan
 */

@Controller
public class IndexController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(ModelMap model, HttpSession session) {
        if (session.getAttribute("admin") == null)
            session.setAttribute("admin", true);
        return "mainPage";
    }
}
