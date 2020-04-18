package org.launchcode.pao.Controllers;


import org.launchcode.pao.Models.Pao;
import org.launchcode.pao.Models.Data.PaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Scanner;

@Controller
@RequestMapping("pao")
public class PaoController {

    @Autowired
    private PaoDao paoDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("paos", paoDao.findAll());
        model.addAttribute("title", "My PAO");
        return "Pao/index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String displayHomePage(){
        return "home";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String displayEditPaoForm(Model model) {
        model.addAttribute("title", "Add PAO");
        model.addAttribute("paos", paoDao.findAll());
        model.addAttribute(new Pao());
        return "pao/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditPaoForm(@ModelAttribute @Valid Pao newPao,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Pao");
            model.addAttribute("pao", paoDao.findAll());
            return "pao/edit";
        }
        paoDao.save(newPao);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePaoForm(Model model) {
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
        model.addAttribute("title", "Train PAO");
        model.addAttribute("paos", paoDao.findAll());
        return "pao/train";
    }

    @RequestMapping(value = "train", method = RequestMethod.POST)
    public String processTrainForm(Model model, @RequestParam int[] paoIds){
        model.addAttribute(paoIds);
        model.addAttribute("paos", paoDao.findAll());

//        I'm going to get the pao object which has the selected Num or Id and then
//        return the whole pao to the front end so I can grab its person/action/object text for flash cards
        for (int paoId : paoIds) {
            model.addAttribute(paoId);
        }

        return "pao/train";
    }

}
