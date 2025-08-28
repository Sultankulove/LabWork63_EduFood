package kg.attractor.java25.labwork63_edufood.service;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {
    Page<DishDto> getDishesByRestaurantId(Long id, Pageable pageable);

    Dish findById(Long dishId);
}
