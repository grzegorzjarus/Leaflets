package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.leaflets.model.Offer;

import java.awt.print.Book;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    //@Query("Select b from Book b where b.publisher.id=?1")
    List<Offer> findOffersByOrOrderRegionId(Long clientId);


    List<Offer> findOffersByClientId(Long clientId);

    Offer findOfferById(long id);

}
