package pl.coderslab.leaflets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Region orderRegion;

    private int quantity;
    private double leafletWidth;
    private double leafletHeight;
    private double kilogramPer1000Pieces;
    private Date earliestDistributionDate;
    private Date latestDistributionDate;
    private OrderStatus status;

    @OneToOne
    private Proposal proposal;
   // private List<Address> notDeliveredAddresses; // should be like this to display addresses as markers

    @OneToMany
    private List<Adress> notDeliveredAddresses;



    public static Offer copy(Offer offer){
        return offer;
    }

    public Offer addProposal(Offer offer, Proposal proposal){
        offer.status=OrderStatus.Accepted;
        return offer;
    }

    public Offer acceptProposal(Offer offer, Proposal proposal){
        this.proposal=proposal;
        offer.status=OrderStatus.Accepted;
        return offer;
    }

    public Offer cancel(Offer offer){
        offer.status=OrderStatus.Cancelled;
        return offer;
    }

    public Offer finish(Offer offer, List<Adress> notDeliveredAddresses){
        this.notDeliveredAddresses=notDeliveredAddresses;
        offer.status=OrderStatus.Finished;
        return offer;
    }

}
