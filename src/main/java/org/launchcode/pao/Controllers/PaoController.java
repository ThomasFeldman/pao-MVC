package org.launchcode.pao.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cheese")
public class PaoController {

    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

//    @RequestMapping(value="")
//    @ResponseBody
//    public String index(HttpServletRequest request) {
//        String name = request.getParameter("name");
//
//        if (name == null) {
//            name = "World";
//        }
//
//        return "";
//    }
//
//    @RequestMapping(value = "hello/{name}")
//    @ResponseBody
//    public String helloUrlSegment(@PathVariable String name) {
//        return "Hello" + name;
//    }
//
//    @RequestMapping(value="hello", method= RequestMethod.GET)
//    @ResponseBody
//    public String helloForm() {
//        String html = "<form method='post'>"+
//                "<input type='text' name='name'/>"+
//                "<input type='submit' value='Greet Me!'/>"+
//                "</form>";
//
//        return html;
//    }
//
//    @RequestMapping(value="hello", method = RequestMethod.POST)
//    @ResponseBody
//    public String helloPost(HttpServletRequest request){
//        String name = request.getParameter("name");
//
//        return "Hello " + name;
//    }
//
//    @RequestMapping(value="goodbye")
//    public String goodbye() {
//        return "redirect:/";
//    }

}
