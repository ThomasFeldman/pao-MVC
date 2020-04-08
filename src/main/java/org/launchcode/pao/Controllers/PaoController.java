package org.launchcode.pao.Controllers;

import javax.validation.constraints.NotNull;
import org.launchcode.pao.Models.Category;
import org.launchcode.pao.Models.Cheese;
import org.launchcode.pao.Models.Pao;
import org.launchcode.pao.Models.Data.CategoryDao;
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
import java.util.Optional;

//CHEESE CODE FROM LAUNCHCODE CLASS
@Controller
@RequestMapping("cheese")
public class PaoController {

    @Autowired
    private PaoDao paoDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", paoDao.findAll());
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("categories", categoryDao.findAll());

        return "index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model  model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "add";
        }

        //.findOne has been deprecated. Need to find updated method.
        //Category cat = categoryDao.findAllById(categoryId); Why does this one not work?? The other one also doesn't work maybe try getting this line to work.
        Category cat = categoryDao.findById(categoryId).get();
        newCheese.setCategory(cat);
        paoDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", paoDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            paoDao.deleteById(cheeseId);
        }

        return "redirect:";
    }

    //END OF CHEESE CODE FROM LAUNCHCODE CLASS

    //START OF REAL PAO CONTROLLER METHODS

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String displayEditPao(Model model) {
        model.addAttribute(new Pao());
        return "";
    }
}
