package pl.coderslab.leaflets.logic;

import pl.coderslab.leaflets.model.Offer;
import pl.coderslab.leaflets.model.OrderStatus;
import pl.coderslab.leaflets.model.Proposal;
import pl.coderslab.leaflets.model.ProposalStatus;

public class OfferService {

    public Offer createProposal(Offer offer, int id){
        if(offer.getStatus()!= OrderStatus.AwaitingProposal){
            throw new RuntimeException(" Invalid order status "+ offer.getStatus());
        }
        return offer.addProposal(offer,Proposal.initial(id));
    }

    public Offer rejectProposal(Offer offer, int proposalId){
        if(offer.getStatus()!= OrderStatus.AwaitingProposal){
            throw new RuntimeException(" Invalid order status "+ offer.getStatus());
        }
        Proposal proposal = offer.getProposal();

        if(proposal==null){
            throw new RuntimeException(" Order does not contain the specified proposal "+ proposalId);
        }

        if(proposal.getStatus()!= ProposalStatus.Initial){
            throw new RuntimeException(" Invalid proposal status"+ proposal.getStatus());
        }
        proposal.setStatus(ProposalStatus.Rejected);
        return Offer.copy(offer);
    }




    public Offer AcceptProposal(Offer offer, int proposalID)
    {
        if (offer.getStatus() != OrderStatus.AwaitingProposal)
        {
            throw new RuntimeException(": invalid order status: " + offer.getStatus());
        }

        Proposal proposal = offer.getProposal();

        if (proposal == null)
        {
            throw new RuntimeException(": offer does not contain the specified proposal: " + proposalID);
        }

        if (proposal.getStatus()!= ProposalStatus.Initial)
        {
            throw new RuntimeException(": invalid proposal status: " + proposal.getStatus());//@ControllerAdvice
        }

        proposal.setStatus(ProposalStatus.Accepted);
        offer.setStatus(OrderStatus.Accepted);

       Proposal newProposal = new Proposal();

        if (offer.getEarliestDistributionDate()== offer.getLatestDistributionDate())  // TODO: nie robić tak, bo wyskoczy trudny do wykrycia błąd
        {
            newProposal = proposal.setDistributionDate(offer.getProposal(), offer.getEarliestDistributionDate());
        }
        else
        {
            newProposal = proposal.accept(proposal);
        }

        return offer.acceptProposal(offer,proposal);
    }


}
