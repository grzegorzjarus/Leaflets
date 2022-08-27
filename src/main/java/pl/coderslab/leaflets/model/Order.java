package pl.coderslab.leaflets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public enum OrderStatus{
        AwaitingProposal,
        Rejected,
        Cancelled,
        Accepted,
        Distribution,
        Finished,
        ReviewDone
    }
    private int Id;
    private Region orderRegion;
    private int quantity;
    private double leafletWidth;
    private double leafletHeight;
    private double kilogramPer1000Pieces;
    private Date earliestDistributionDate;
    private Date latestDistributionDate;
    private OrderStatus status;
    private Proposal proposal;
   // private List<Address> notDeliveredAddresses; // should be like this to display addresses as markers
    private List<Adress> notDeliveredAddresses;

    public static Order copy(Order order){
        return order;
    }

    public Order addProposal(Order order, Proposal proposal){
        order.status=OrderStatus.Accepted;
        return order;
    }

    public Order acceptProposal(Order order, Proposal proposal){
        this.proposal=proposal;
        order.status=OrderStatus.Accepted;
        return order;
    }

    public Order cancel(Order order){
        order.status=OrderStatus.Cancelled;
        return order;
    }

    public Order finish(Order order, List<Adress> notDeliveredAddresses){
        this.notDeliveredAddresses=notDeliveredAddresses;
        order.status=OrderStatus.Finished;
        return order;
    }

}
