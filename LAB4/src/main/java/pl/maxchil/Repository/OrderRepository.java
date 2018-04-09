package pl.maxchil.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.maxchil.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    List<Order> findAll();
}
