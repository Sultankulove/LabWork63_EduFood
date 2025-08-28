package kg.attractor.java25.labwork63_edufood.repo;

import kg.attractor.java25.labwork63_edufood.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {
    Page<Dish> findAllByRestaurant_Id(Long id, Pageable pageable);



}
