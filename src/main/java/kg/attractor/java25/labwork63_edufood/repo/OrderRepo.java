package kg.attractor.java25.labwork63_edufood.repo;

import kg.attractor.java25.labwork63_edufood.model.Order;
import kg.attractor.java25.labwork63_edufood.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
    List<Order> findAllByUserOrderByIdDesc(User user);

    List<Order> findByUserOrderByCreatedAtDesc(User user);

}
