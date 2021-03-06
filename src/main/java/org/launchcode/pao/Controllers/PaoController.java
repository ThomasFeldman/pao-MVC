package org.launchcode.pao.Controllers;

import org.launchcode.pao.Controllers.UserController;
import org.launchcode.pao.Models.Data.UserDao;
import org.launchcode.pao.Models.Pao;
import org.launchcode.pao.Models.Data.PaoDao;
import org.launchcode.pao.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.taglibs.TagLibConfig;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequestMapping("pao")
public class PaoController {

    @OneToOne
    public UserController userController;

    @Autowired
    private PaoDao paoDao;

    @Autowired
    private UserDao userDao;



    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayHomePage(){
        return "home";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("paos", paoDao.findAll());
        model.addAttribute("title", "My PAO");
        return "Pao/index";
    }


    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String displayEditPaoForm(Model model) {
        model.addAttribute("title", "Add PAO");
        model.addAttribute(new Pao());
        return "pao/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditPaoForm(@ModelAttribute @Valid Pao newPao,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Pao");
            return "pao/edit";
        }

        paoDao.save(newPao);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePaoForm(HttpSession session, Model model) {

//        if (userController.getUserFromSession(session) == null){
//            model.addAttribute("title", "Login");
//            model.addAttribute("users", userDao.findAll());
//            return "redirect:login";
//        }

        model.addAttribute("paos", paoDao.findAll());
        model.addAttribute("title", "Remove Pao");
        return "pao/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePaoForm(@RequestParam int[] paoIds){
        for(int paoId : paoIds) {
            paoDao.deleteById(paoId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "train", method = RequestMethod.GET)
    public String displayTrainForm(Model model) {
        model.addAttribute("paos", paoDao.findAll());
        return "pao/flashcards";
    }

    @RequestMapping(value = "train", method = RequestMethod.POST)
    public String processTrainForm(Model model, @RequestParam int[] paoIds){
        model.addAttribute("paos", paoDao.findAll());
        model.addAttribute("paoId", paoIds);

        return "pao/flashcards";
    }

}
