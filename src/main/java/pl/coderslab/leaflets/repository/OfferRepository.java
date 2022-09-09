package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.leaflets.model.Offer;

import java.awt.print.Book;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    //@Query("Select b from Book b where b.publisher.id=?1")
    List<Offer> findOffersByOrOrderRegionId(Long clientId);


    List<Offer> findOffersByClientId(Long clientId);

    @Query("Select o from Offer o where o.client.id = ?1 and o.status=0 ")
    List<Offer> findAwaitingOffersByClientId(Long clientId);

    @Query("Select o from Offer o where o.client.id = ?1 and ( o.status=3 or o.status=4)")
    List<Offer> findActiveOffersByClientId(Long clientId);

    @Query("Select o from Offer o where o.client.id = ?1 and (o.status=5)")
    List<Offer> findFinishedOffersByClientId(Long clientId);

    Offer findOfferById(long id);

}
