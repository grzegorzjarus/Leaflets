package pl.coderslab.leaflets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {



    private int Id;
    private Date distributionDate;
    private ProposalStatus status;

    public static Proposal initial(int id){
        Proposal proposal = new Proposal();
        proposal.status=ProposalStatus.Initial;
        return proposal;
    }
    public Proposal reject(Proposal proposal){
        proposal.setStatus(ProposalStatus.Rejected);
        return proposal;
    }

    public Proposal accept(Proposal proposal){
        proposal.setStatus(ProposalStatus.Accepted);
        return proposal;
    }

    public Proposal setDistributionDate(Proposal proposal, Date date){
        proposal.setDistributionDate(date);
        proposal.setStatus(ProposalStatus.Ready);
        return proposal;
    }

    public Proposal startDistribution(Proposal proposal){
        proposal.setStatus(ProposalStatus.Distribution);
        return proposal;
    }

    public Proposal finishDistribution(Proposal proposal){
        proposal.setStatus(ProposalStatus.Finished);
        return proposal;
    }

    public Proposal cancel(Proposal proposal){
        proposal.setStatus(ProposalStatus.Cancelled);
        return proposal;
    }
}
