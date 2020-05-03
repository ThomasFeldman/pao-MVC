package org.launchcode.pao.Controllers;

import org.launchcode.pao.Models.User;
import org.launchcode.pao.Models.Data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Optional;

@Controller
@RequestMapping("pao")
public class UserController {
    @Autowired
    private UserDao userDao;


    //LaunchCode Authentication Code

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userDao.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }




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
    public String processLogin(@RequestParam(name = "password") String password,
                               @RequestParam(name = "email")String email,
                               Model model){
        for(User theUser: userDao.findAll()){
//            User userCheck = userDao.findById(i).get();

            if(theUser.getEmail().equals(email) && theUser.getPassword().equals(password)) {
                model.addAttribute("userAccount", theUser);
                return "user/account";
            }else{
                model.addAttribute("invalidCredentials", true);
            }
        }

        return "user/account";
    }


    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignUp(Model model){
        model.addAttribute("title", "User Signup");
        model.addAttribute(new User());
        return "user/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignUp(@ModelAttribute @Valid User newUser,
                                     Errors errors, HttpServletRequest request,
                                     Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "User Signup");
            return "user/signup";
        }

        //Will this work? I'm thinking newUser.getUsername will check existing users to see if the name already exists.
        User existingUser = userDao.findByUsername(newUser.getUsername());

        //name.alreadyexists? should I change this to username?
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that name already exists");
            model.addAttribute("title", "User Signup");
            return "user/signup";
        }

        //Unsure if getVerifyPassword does anything since it doesn't seem to check against password in User.java
        String password = newUser.getPassword();
        String verifyPassword = newUser.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "User Signup");
            return "user/signup";
        }
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        model.addAttribute("userAccount", newUser);
        return "user/account";
    }

}
