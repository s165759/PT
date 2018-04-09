package pl.maxchil.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.maxchil.model.Disc;

import java.util.List;
import java.util.UUID;

public interface DiscRepository extends CrudRepository<Disc, UUID> {
    Disc findByTitle(String title);
    List<Disc> findAll();
}
