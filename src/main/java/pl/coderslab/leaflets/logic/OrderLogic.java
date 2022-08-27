package pl.coderslab.leaflets.logic;

import pl.coderslab.leaflets.model.Order;
import pl.coderslab.leaflets.model.Proposal;

public class OrderLogic {

    public Order createProposal(Order order, int id){
        if(order.getStatus()!= Order.OrderStatus.AwaitingProposal){
            throw new RuntimeException(" Invalid order status "+ order.getStatus());
        }
        return order.addProposal(order,Proposal.initial(id));
    }

    public Order rejectProposal(Order order, int proposalId){
        if(order.getStatus()!= Order.OrderStatus.AwaitingProposal){
            throw new RuntimeException(" Invalid order status "+ order.getStatus());
        }
        Proposal proposal = order.getProposal();

        if(proposal==null){
            throw new RuntimeException(" Order does not contain the specified proposal "+ proposalId);
        }

        if(proposal.getStatus()!= Proposal.ProposalStatus.Initial){
            throw new RuntimeException(" Invalid proposal status"+ proposal.getStatus());
        }
        proposal.setStatus(Proposal.ProposalStatus.Rejected);
        return Order.copy(order);
    }




    public Order AcceptProposal(Order order, int proposalID)
    {
        if (order.getStatus() != Order.OrderStatus.AwaitingProposal)
        {
            throw new RuntimeException(": invalid order status: " + order.getStatus());
        }

        Proposal proposal = order.getProposal();

        if (proposal == null)
        {
            throw new RuntimeException(": offer does not contain the specified proposal: " + proposalID);
        }

        if (proposal.getStatus()!= Proposal.ProposalStatus.Initial)
        {
            throw new RuntimeException(": invalid proposal status: " + proposal.getStatus());
        }

        proposal.setStatus(Proposal.ProposalStatus.Accepted);
        order.setStatus(Order.OrderStatus.Accepted);

       Proposal newProposal = new Proposal();

        if (order.getEarliestDistributionDate()== order.getLatestDistributionDate())
        {
            newProposal = proposal.setDistributionDate(order.getProposal(), order.getEarliestDistributionDate());
        }
        else
        {
            newProposal = proposal.accept(proposal);
        }

        return order.acceptProposal(order,proposal);
    }


}
