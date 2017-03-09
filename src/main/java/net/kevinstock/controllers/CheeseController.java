package net.kevinstock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kevinstock on 3/7/17.
 */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheese");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddCheeseForm(HttpServletRequest request) {
//        String cheeseName = request.getParameter("cheeseName");
//    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription, Model model) {
        if (cheeseName.equals("")) {
            String error = "Cheese name is required";
            model.addAttribute("error", error);
            return "/cheese/add";
        }
        else {
            cheeses.put(cheeseName, cheeseDescription);

            // redirect to /cheese
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(HttpServletRequest request, Model model) {
        String cheese = request.getParameter("cheese");
        if (cheese == null) {
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "Remove Cheese");
            return "cheese/remove";
        }
        else {
            cheeses.remove(cheese);
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseName) {
        for (String cheese : cheeseName) {
            cheeses.remove(cheese);
            System.out.println(cheeseName + " removed");
        }

        return "redirect:";
    }

}
