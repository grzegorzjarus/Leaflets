package pl.coderslab.leaflets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.leaflets.model.Offer;
import pl.coderslab.leaflets.model.OfferStatus;
import pl.coderslab.leaflets.model.Region;
import pl.coderslab.leaflets.repository.OfferRepository;
import pl.coderslab.leaflets.util.Arrays;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OfferController {

    Region region = new Region();
    double[][] b = {
            {51.10569158716107, 17.103798372351886},
            {51.11797095398788, 17.09607570054952},
            {51.104883619687335, 17.074452219502913},
            {51.10062809126138, 17.0893827183208},
            {51.101382264099946, 17.10002284391516}
    };


    @Autowired
    OfferRepository offerRepository;

    Offer offer = new Offer();

//    @GetMapping("/offer")
//    @Transactional
//    public String createOffer(){
//        Date edd = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ");
//        System.out.println(formatter.format(edd));
//
//        Date ldd = new Date(2022,9,30);
//
//        region.setPoints(Arrays.transferToList(b));
//        region.setName("Rejon do zrobienia");
//
//
//        offer.setStatus(OfferStatus.Distribution);
//        offer.setLeafletHeight(220);
//        offer.setLeafletWidth(150);
//        offer.setKilogramPer1000Pieces(1.5);
//        offer.setQuantity(5000);
//        offer.setEarliestDistributionDate(edd);
//        offer.setLatestDistributionDate(ldd);
//        offer.setOrderRegion(region);
//
//
//        offerRepository.save(offer);
//        return "leaflets";
//    }

}
