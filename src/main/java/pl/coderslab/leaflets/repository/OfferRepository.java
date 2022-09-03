package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.leaflets.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
