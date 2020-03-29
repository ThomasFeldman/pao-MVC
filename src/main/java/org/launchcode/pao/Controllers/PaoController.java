package org.launchcode.pao.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaoController {

    @RequestMapping(value="")
    @ResponseBody
    public String index() {
        return "Hello World";
    }

    @RequestMapping(value="goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye";
    }

}
