package pl.coderslab.leaflets.controller;

import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.leaflets.ajax.AjaxUser;
import pl.coderslab.leaflets.ajax.MyData;
import pl.coderslab.leaflets.model.Client;
import pl.coderslab.leaflets.model.Offer;
import pl.coderslab.leaflets.model.OfferStatus;
import pl.coderslab.leaflets.model.Region;
import pl.coderslab.leaflets.repository.OfferRepository;
import pl.coderslab.leaflets.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OfferController {

    Region region = new Region();

    static MyData staticData;

    double[][] b = {
            {51.10569158716107, 17.103798372351886},
            {51.11797095398788, 17.09607570054952},
            {51.104883619687335, 17.074452219502913},
            {51.10062809126138, 17.0893827183208},
            {51.101382264099946, 17.10002284391516}
    };


    private final OfferRepository offerRepository;

    Offer offer;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
        offer = new Offer();
    }

    @GetMapping("/offer/create1")
    public String creatOffer1Get(Model model, HttpSession session) {
        //System.out.println("Metoda get show editForm " + model.getAttribute("client"));
        System.out.println("Metoda get /offer/create1 OfferController ");
        model.addAttribute("offer", offer);
        return "client/createOffer1";
    }

    @PostMapping("/offer/create1")
    public String createOffer1Post(Offer offer, Model model){
        System.out.println("Metoda post /offer/create1 OfferController ");
        System.out.println(offer);
        model.addAttribute(offer);
        this.offer=offer;
        return "client/createOffer2";
       // return "/api/leaflet";
    }

//    @GetMapping("/api/leaflet")
//    public String getCoordinates() {
//            System.out.println("Metoda get /api/leaflet OfferController ");
//            return "client/createOffer2";
//    }

    @GetMapping("/api/leaflet")
    public @ResponseBody MyData getCoordinates2() {
        if (staticData != null) {
            return staticData;
        } else {
            MyData myData = new MyData();
            myData.setCoordinates(b);
            myData.setOffer(offer);
            System.out.println("Metoda get /api/leaflet "+ myData);
            Arrays.printArray(myData.getCoordinates());
            return myData;
        }
    }


    @PostMapping(value = "/api/leaflet", consumes = {"application/json"})
   // @ResponseBody
    public String createCompleteOffer(@RequestBody MyData myData, Model model, HttpSession session) {

        System.out.println("Metoda post /api/leaflet OfferController ");
        Region region = new Region("Rejon", Arrays.transferToList(myData.getCoordinates()));
        offer.setOrderRegion(region);
        Offer offer2 = myData.getOffer();
       // offer2.setOrderRegion(region);
        System.out.println(offer.toString());

        model.addAttribute("offer",offer);
        offerRepository.save(offer);
        session.setAttribute("offer",offer);
        //return offer.toString();
        return "redirect:/offer/showOffer";
    }

    @GetMapping("/offer/showOffer")
    public String showOffer() {
        return "client/showOffer";
    }


    @ExceptionHandler({Exception.class})
    public void resolveException(Exception e) {
        e.printStackTrace();
    }

}
