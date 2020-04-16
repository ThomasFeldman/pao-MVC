package org.launchcode.pao.Controllers;

import org.launchcode.pao.Models.User;
import org.launchcode.pao.Models.Data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("pao")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String displayUser(Model model){

        return "user/account";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignUp(Model model){
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignUp(@ModelAttribute @Valid User newUser,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "User Signup");
            model.addAttribute("user", userDao.findAll());
            return "user/signup";
        }
        userDao.save(newUser);
        return "redirect:";
    }
}
