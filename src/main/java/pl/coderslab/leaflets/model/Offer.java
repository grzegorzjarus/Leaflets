package pl.coderslab.leaflets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Region orderRegion;

    private int quantity;
    private double leafletWidth;
    private double leafletHeight;
    private double kilogramPer1000Pieces;
    private LocalDate earliestDistributionDate;
    private LocalDate latestDistributionDate;
    private OfferStatus status;



    @OneToOne
    private Proposal proposal;
   // private List<Address> notDeliveredAddresses; // should be like this to display addresses as markers

    @OneToMany
    private List<Adress> notDeliveredAddresses;


    public void setEarliestDistributionDate(String stringDate){
        LocalDate date = LocalDate.parse(stringDate);
        this.earliestDistributionDate = date;
    }

    public void setLatestDistributionDate(String stringDate){
        LocalDate date = LocalDate.parse(stringDate);
        this.latestDistributionDate = date;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

    public void setLeafletHeight(double leafletHeight){
        this.leafletHeight=leafletHeight;
    }

    public void setLeafletWidth(double leafletWidth){
        this.leafletWidth=leafletWidth;
    }

    public void setKilogramPer1000Pieces(double kilogramPer1000Pieces){
        this.kilogramPer1000Pieces=kilogramPer1000Pieces;
    }
    public void setStatus(OfferStatus status){
        this.status=status;
    }




    public static Offer copy(Offer offer){
        return offer;
    }

    public Offer addProposal(Offer offer, Proposal proposal){
        offer.status= OfferStatus.Accepted;
        return offer;
    }

    public Offer acceptProposal(Offer offer, Proposal proposal){
        this.proposal=proposal;
        offer.status= OfferStatus.Accepted;
        return offer;
    }

    public Offer cancel(Offer offer){
        offer.status= OfferStatus.Cancelled;
        return offer;
    }

    public Offer finish(Offer offer, List<Adress> notDeliveredAddresses){
        this.notDeliveredAddresses=notDeliveredAddresses;
        offer.status= OfferStatus.Finished;
        return offer;
    }

}
