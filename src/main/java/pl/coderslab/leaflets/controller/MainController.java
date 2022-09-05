package pl.coderslab.leaflets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.leaflets.ajax.AjaxUser;
import pl.coderslab.leaflets.ajax.MyData;
import pl.coderslab.leaflets.model.Client;
import pl.coderslab.leaflets.model.User;
import pl.coderslab.leaflets.util.Arrays;

@Controller

public class MainController {

    static MyData staticData;

    static AjaxUser user;

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


    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "Test aplikacji SpringBoot";
    }

    @GetMapping("/register")
    public String registerUser(){
        return "register";
    }



//    @PostMapping("/register")
//    @ResponseBody
//    public String save(User user) {
////        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
////        userService.save(user);
//        //user.setEmail();
//        String result = user.toString();
//        return result;
//    }


    @GetMapping("/leaflet")
    public String add(Model model) {
        //model.addAttribute("coordinates", coordinates);
        return "leaflets";

    }

    @GetMapping("/api/leaflet")
    public @ResponseBody MyData getCoordinates() {
        if (staticData != null) {
            return staticData;
        } else {
            MyData myData = new MyData();
            myData.setCoordinates(b);
            user = new AjaxUser();
            user.setAge(32);
            user.setName("Grzesiek");
            user.setSurname("Jarus");
            user.setPoints(99);
            myData.setUser(user);
            System.out.println("Metoda get /api/leaflet "+ myData);
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

    @PostMapping(value = "/api/leaflet", consumes = {"application/json"})
    @ResponseBody
    public  MyData complicateObject(@RequestBody MyData myData) {
        // Method details

        staticData = myData;


//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("getCoordinates");

//        System.out.println("Metoda post /api/leaflet "+ myData);
//        System.out.println(myData);
//        System.out.println(myData.getUser().getName());
//        System.out.println(myData.getUser().getAge());
        Arrays.printArray(myData.getCoordinates());
        System.out.println(myData.getOffer());
        return myData;
    }

    @ExceptionHandler({Exception.class})
    public void resolveException(Exception e) {
        e.printStackTrace();
    }

}
