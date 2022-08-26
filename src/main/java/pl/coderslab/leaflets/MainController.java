package pl.coderslab.leaflets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.leaflets.model.MyData;
import pl.coderslab.leaflets.model.User;

@Controller

public class MainController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
            return "Test aplikacji SpringBoot";
    }


    static MyData staticData;

    User user = new User();

    double[][] a = {
            {1.0, 2.1},
            {4.5, 5.6},
            {7.6, 8.7},
    };

    double[][] b = {
            {51.10569158716107, 17.103798372351886},
            {51.11797095398788, 17.09607570054952},
            {51.104883619687335, 17.074452219502913},
            {51.10062809126138, 17.0893827183208},
            {51.101382264099946, 17.10002284391516}
    };

    @GetMapping("/leaflet")
    public String add(Model model) {
        //model.addAttribute("coordinates", coordinates);
        return "leaflets";
        ///home/grzesiek/Projects/Leaflets/src/main/webapp/WEB-INF/views/leaflets.jsp
    }

    @GetMapping("/api/leaflet")
    public @ResponseBody MyData getCoordinates() {
        if (staticData != null) {
            return staticData;
        } else {
            MyData myData = new MyData();
            myData.setCoordinates(b);
            user.setAge(32);
            user.setName("Grzesiek");
            //myData.setUser(user);
            return myData;
        }
    }

    @GetMapping("/api/leaflet2")
    public @ResponseBody String getCoordinates2() {
        if (staticData != null) {
            return staticData.toString();
        } else {
            MyData myData = new MyData();
            myData.setCoordinates(b);
            return myData.toString();
        }
    }


//    @PostMapping(value = "/api/leaflet", consumes = {"application/json"})
//    public @ResponseBody MyData complicateObject(@RequestBody MyData myData) {
//        // Method details
//        staticData = myData;
//        return myData;
//    }

    @PostMapping(value = "/api/leaflet", consumes = {"application/json"})
    @ResponseBody
    public  MyData complicateObject(@RequestBody MyData myData) {
        // Method details

        staticData = myData;


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getCoordinates");



        System.out.println(myData);
        return myData;
    }


    @ExceptionHandler({Exception.class})
    public void resolveException(Exception e) {
        e.printStackTrace();
    }


}
