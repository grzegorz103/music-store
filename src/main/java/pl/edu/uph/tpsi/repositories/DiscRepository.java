package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.uph.tpsi.models.Disc;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Long> {
}
