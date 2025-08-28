package kg.attractor.java25.labwork63_edufood.repo;

import kg.attractor.java25.labwork63_edufood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);}
