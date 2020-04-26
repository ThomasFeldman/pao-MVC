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
import javax.validation.constraints.Email;
import java.util.Optional;

@Controller
@RequestMapping("pao")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String displayUser(@RequestParam(value = "password") String password,
                              @RequestParam(value = "email")String email, Errors errors, Model model){
        for(int i = 0; i < userDao.count(); i++){
            Optional<User> userAccount = userDao.findById(i);
            if(userAccount.get().getEmail() == email) {
                if(userAccount.get().getPassword() == password) {
                    model.addAttribute(userAccount);
                }
            }
        }


        if (errors.hasErrors()) {
            return "user/login";
        }
        return "user/account";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLogin(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("users", userDao.findAll());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLogin(@RequestParam(value = "password") String password,
                               @RequestParam(value = "email")String email,
                               Errors errors, Model model){
        for(int i = 0; i < userDao.count(); i++){
            Optional<User> userCheck = userDao.findById(i);
            if(userCheck.get().getEmail() == email) {
                if(userCheck.get().getPassword() == password) {
                    return "user/account";
                }
                return "Wrong Password";
            }else{
                return "Not a user";
            }
        }

        return "user/account";
    }

//    Code from Youtube Video
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLoginForm(@ModelAttribute(name="loginForm") LoginForm loginForm,
                                Model model){
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if("admin".equals(username) && "admin".equals(password)) {
            return "home";
        }else{
            model.addAttribute("invalidCredentials", true);
        }
        return "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignUp(Model model){
        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignUp(@ModelAttribute @Valid User newUser,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "User Signup");
            return "user/signup";
        }
        userDao.save(newUser);
        //Change to User Account
        return "user/signup";
    }

}
