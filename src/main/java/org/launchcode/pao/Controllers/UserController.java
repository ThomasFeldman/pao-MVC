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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("pao")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String displayUser(@RequestParam User user, Model model){
        //will need to return a user object to pass here to get user details
        //will need to pass user object to other controller so they can get that user's specific PAO
        return "user/account";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignUp(Model model){
        model.addAttribute("title", "User Signup");
//        model.addAttribute("users", userDao.findAll());
//        model.addAttribute(new User());
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
        return "user/account";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLogin(Model model){
        model.addAttribute("title", "Login");
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLogin(Model model){

        return "user/account";
    }

}
