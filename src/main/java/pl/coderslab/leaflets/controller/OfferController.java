package pl.coderslab.leaflets.controller;

import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.leaflets.ajax.AjaxUser;
import pl.coderslab.leaflets.ajax.MyData;
import pl.coderslab.leaflets.model.*;
import pl.coderslab.leaflets.repository.OfferRepository;
import pl.coderslab.leaflets.util.Arrays;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
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

    private Offer offer;

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
    public String createOffer1Post(Offer offer, Model model, HttpSession session) {
        System.out.println("Metoda post /offer/create1 OfferController ");
        System.out.println(offer);
        Client client = (Client) session.getAttribute("client");
        offer.setClient(client);
        offer.setStatus(OfferStatus.AwaitingProposal);
        model.addAttribute(offer);
        this.offer = offer;
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
            System.out.println("Metoda get /api/leaflet " + myData);
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

        model.addAttribute("offer", offer);
        offerRepository.save(offer);
        session.setAttribute("offer", offer);
        //return offer.toString();
        return "redirect:/offer/showOffer";
    }

    @GetMapping("/offer/showOffer")
    public String showOffer() {
        return "client/showOffer";
    }

    @GetMapping("/offer/showOffer/{id}")
    public String showOfferById(HttpSession session, @PathVariable long id) {
        Offer offer = offerRepository.findOfferById(id);
        List<Point> points = offer.getOrderRegion().getPoints();
        // model.addAttribute("offer", offer);
        MyData myData = new MyData();
        myData.setCoordinates(Arrays.transferToArray(points));
        myData.setOffer(offer);
        session.setAttribute("offer",offer);
//        session.setAttribute("offer",offer);
//        session.setAttribute("coordinates",points);
        session.setAttribute("myData", myData);
        System.out.println("Metoda /offer/showOffer/{id} " + session.getAttribute("myData"));
        //return "client/showOffer";
        return "client/showOfferWithMap";
    }


    @GetMapping("/api/showOfferId")

    public @ResponseBody MyData showOfferId(HttpSession session) {
//        if (staticData != null) {
//            return staticData;
//        } else {
        MyData myData = (MyData) session.getAttribute("myData");
        System.out.println("Metoda get /offer/showOfferId" + myData);
        return myData;
    }




//    @GetMapping("/offer/showOffer2/{id}")
//    public ModelAndView showOfferWithMap(Model model, @PathVariable long id) {
////        if (staticData != null) {
////            return staticData;
////        } else {
//        System.out.println("Metoda get /offer/showOffer2/{id} ");
//        Offer offer = offerRepository.findOfferById(id);
//        List<Point> points = offer.getOrderRegion().getPoints();
//        // model.addAttribute("offer", offer);
//        MyData myData = new MyData();
//        myData.setCoordinates(Arrays.transferToArray(points));
//        myData.setOffer(offer);
//        model.addAttribute("myData", myData);
//
//
//        ModelAndView modelAndView = new ModelAndView("client/showOfferWithMap");
//        //ModelAndView modelAndView = new ModelAndView();
//        // modelAndView.addObject("offer",offer);
//        return modelAndView;
//        //  Arrays.printArray(myData.getCoordinates());
//        //  return "client/showOfferWithMap";
//
//    }

    @GetMapping("/client/app/allOffers")
    public String getAllClientOffers(HttpSession session, Model model) {
        List<Offer> offers2 = offerRepository.findAll();
        Client client = (Client) session.getAttribute("client");
        List<Offer> offers = offerRepository.findOffersByClientId(client.getId());
        System.out.println(offers);
        model.addAttribute("offers", offers);
        return "client/showAllClientOffers";

    }

    @GetMapping("/client/app/allOffers/awaiting")
    public String getAllClientAwaitingOffers(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        List<Offer> awaitingOffers = offerRepository.findAwaitingOffersByClientId(client.getId());
        model.addAttribute("offers", awaitingOffers);
        return "client/showAllClientOffers";
    }

    @GetMapping("/client/app/allOffers/active")
    public String getAllClientActiveOffers(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        List<Offer> activeOffers = offerRepository.findActiveOffersByClientId(client.getId());
        model.addAttribute("offers", activeOffers);
        return "client/showAllClientOffers";
    }

    @GetMapping("/client/app/allOffers/finished")
    public String getAllClientFinishedOffers(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        List<Offer> finishedOffers = offerRepository.findFinishedOffersByClientId(client.getId());
        model.addAttribute("offers", finishedOffers);
        return "client/showAllClientOffers";
    }


    @ExceptionHandler({Exception.class})
    public void resolveException(Exception e) {
        e.printStackTrace();
    }

}
