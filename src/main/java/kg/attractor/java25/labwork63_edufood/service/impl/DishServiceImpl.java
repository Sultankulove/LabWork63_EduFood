package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.model.Dish;
import kg.attractor.java25.labwork63_edufood.repo.DishRepo;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepo repo;
    @Override
    public Page<DishDto> getDishesByRestaurantId(Long id, Pageable pageable) {

        return repo.findAllByRestaurant_Id(id, pageable).
                map(dish -> {
            DishDto dto = new DishDto();
            dto.setId(dish.getId());
            dto.setName(dish.getName());
            dto.setDescription(dish.getDescription());
            dto.setPrice(dish.getPrice());
            dto.setImageUrl(dish.getImageUrl());
            dto.setRestaurantId(dish.getRestaurant().getId());

            return dto;
        });
    }

    @Override
    public Dish findById(Long dishId) {
        return repo.findById(dishId).orElse(null);
    }
}
