package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.leaflets.model.Offer;

import java.util.List;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findOffersByOrOrderRegionId(Long clientId);


    List<Offer> findOffersByClientId(Long clientId);

    //@Query(value = "Select o from Offer o where o.client.id = ?1 and o.status=:AWAITING_PROPOSAL")
    @Query(value = "Select o from Offer o where o.client.id = ?1 and o.status=pl.coderslab.leaflets.model.OfferStatus.AWAITING_PROPOSAL")
    List<Offer> findAwaitingProposalOffersByClientId(Long clientId);

    //@Query("Select o from Offer o where o.client.id = ?1 and ( o.status=:ACCEPTED or o.status=:DISTRIBUTION)")
    @Query("Select o from Offer o where o.client.id = ?1 and ( o.status=pl.coderslab.leaflets.model.OfferStatus.ACCEPTED or o.status=pl.coderslab.leaflets.model.OfferStatus.DISTRIBUTION)")
    List<Offer> findActiveOffersByClientId(Long clientId);

    //@Query("Select o from Offer o where o.client.id = ?1 and (o.status=:FINISHED)")
    @Query("Select o from Offer o where o.client.id = ?1 and o.status=pl.coderslab.leaflets.model.OfferStatus.FINISHED")
    List<Offer> findFinishedOffersByClientId(Long clientId);

    Offer findOfferById(long id);

}
