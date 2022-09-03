package pl.coderslab.leaflets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.leaflets.model.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor,Long> {
}
