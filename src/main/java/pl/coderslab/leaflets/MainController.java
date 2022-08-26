package pl.coderslab.leaflets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/leaflets")
public class MainController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
            return "Test aplikacji SpringBoot";
    }
}
