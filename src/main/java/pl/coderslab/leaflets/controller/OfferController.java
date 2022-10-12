package pl.coderslab.leaflets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.leaflets.ajax.MyData;
import pl.coderslab.leaflets.model.*;
import pl.coderslab.leaflets.repository.ClientRepository;
import pl.coderslab.leaflets.repository.OfferRepository;
import pl.coderslab.leaflets.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@Transactional
@ControllerAdvice
public class OfferController {

    double[][] staticArray = {
            {51.10569158716107, 17.103798372351886},
            {51.11797095398788, 17.09607570054952},
            {51.104883619687335, 17.074452219502913},
            {51.10062809126138, 17.0893827183208},
            {51.101382264099946, 17.10002284391516}
    };

    double[][] coordinates = {};

    private final OfferRepository offerRepository;
    private final Validator validator;

    private Offer offer;
    private Region region;


    public OfferController(OfferRepository offerRepository, Validator validator) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        offer = new Offer();
        region = new Region();
    }

    @GetMapping("/offer/create1")
    public String creatOffer1Get(Model model, HttpSession session) {
        //System.out.println("Metoda get show editForm " + model.getAttribute("client"));
        System.out.println("Metoda get /offer/create1 OfferController ");
        Offer offer = new Offer();
       // Region region = new Region();  //to display Region name
        offer.setOrderRegion(region);
        model.addAttribute("offer", offer);
        return "client/createOffer1";
    }

    @PostMapping("/offer/create1")
    public String createOffer1Post(@Valid Offer offer,BindingResult result, Model model, HttpSession session ) {

        //Set<ConstraintViolation<Offer>> violations = validator.validate(offer);

        if(result.hasErrors()){
            System.out.println("Błędna data!");
            return "client/createOffer1";
        }
        else {
            System.out.println("Metoda post /offer/create1 OfferController ");
            System.out.println(offer);
            Client client = (Client) session.getAttribute("client");
            offer.setClient(client);
            offer.setStatus(OfferStatus.AWAITING_PROPOSAL);
//            Region region = new Region();  to display Region name
//            offer.setOrderRegion(region);
           // model.addAttribute(offer);
            session.setAttribute("offer",offer);
            session.setAttribute("coordinates",coordinates);
            this.offer = offer;// because problem of sending offer between form and ajax
        }
        return "client/createOffer2";

    }

    @GetMapping("/api/leaflet")
    public @ResponseBody MyData createPartialOfferAjax(HttpSession session) {
            MyData myData = new MyData();
//            myData.setCoordinates((double[][]) session.getAttribute("coordinates"));
//            myData.setOffer((Offer) session.getAttribute("offer"));
            myData.setCoordinates(coordinates);
            myData.setOffer(offer);
            System.out.println("Metoda get /api/leaflet " + myData);
            Arrays.printArray(myData.getCoordinates());
            return myData;
    }


    @PostMapping(value = "/api/leaflet", consumes = {"application/json"})
    public String createCompleteOfferAjax(@RequestBody MyData myData, Model model, HttpSession session) {

        System.out.println("Metoda post /api/leaflet OfferController ");
        //Region region = new Region("Rejon", Arrays.transferToList(myData.getCoordinates()));
        //offer.getOrderRegion().setPoints(Arrays.transferToList(myData.getCoordinates())); to display Region name
        offer.getOrderRegion().setPoints(Arrays.transferToList(myData.getCoordinates()));
        System.out.println(offer.getOrderRegion().getName());
       // offer.setOrderRegion(region);
        Offer offer2 = myData.getOffer();
        // offer2.setOrderRegion(region);
        System.out.println(offer.toString());

       // model.addAttribute("offer", offer);
        offerRepository.save(offer);
        session.setAttribute("offer", offer);
        return "/client/showOfferWithMap";
    }

    @GetMapping("/offer/showOffer")
    public String showOffer(HttpSession session) {
        MyData myData = new MyData();
      //  List<Point> points = new ArrayList<>();
        Region region = offer.getOrderRegion();
        //if(region.getPoints()!=null){
        List<Point> points = region.getPoints();//}
        if(points!=null){
        myData.setCoordinates(Arrays.transferToArray(points));
        myData.setOffer(offer);
        session.setAttribute("offer",offer);
        session.setAttribute("myData", myData);
        return "client/showOfferWithMap";
        }
        else return "client/showOffer";
    }

    @GetMapping("/offer/showOffer/{id}")
    public String showOfferById(HttpSession session, @PathVariable long id) {
        Offer offer = offerRepository.findOfferById(id);
        if(offer!=null) {
            List<Point> points = offer.getOrderRegion().getPoints();
            // model.addAttribute("offer", offer);
            MyData myData = new MyData();
            myData.setCoordinates(Arrays.transferToArray(points));
            myData.setOffer(offer);
            session.setAttribute("offer", offer);
//        session.setAttribute("offer",offer);
//        session.setAttribute("coordinates",points);
            session.setAttribute("myData", myData);
            System.out.println("Metoda /offer/showOffer/{id} " + session.getAttribute("myData"));
            //return "client/showOffer";
            return "client/showOfferWithMap";
        }
        else return "redirect:/client/app/allOffers";
    }


    @GetMapping("/api/showOfferId")

    public @ResponseBody MyData showOfferIdAjax(HttpSession session) {
        MyData myData = (MyData) session.getAttribute("myData");
        System.out.println("Metoda get /offer/showOfferId" + myData);
        return myData;
    }


    @GetMapping("/client/app/allOffers")
    public String getAllClientOffers(HttpSession session, Model model) {
        List<Offer> offers2 = offerRepository.findAll();
        Client client = (Client) session.getAttribute("client");
        List<Offer> offers = offerRepository.findOffersByClientId(client.getId());
        System.out.println(offers);
        model.addAttribute("offers", offers);
       // session.setAttribute("offers",offers);
        return "client/showAllClientOffers";

    }

    @GetMapping("/client/app/allOffers/awaiting")
    public String getAllClientAwaitingOffers(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        List<Offer> awaitingOffers = offerRepository.findAwaitingProposalOffersByClientId(client.getId());
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
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public String resolveException(HttpServletRequest req, Exception exception, Model model) {
        exception.printStackTrace();
        model.addAttribute("exception", exception);
        model.addAttribute("url", req.getRequestURL() );
        model.addAttribute("errorMessage", exception.getMessage());
        return "exception-page";
    }

}
