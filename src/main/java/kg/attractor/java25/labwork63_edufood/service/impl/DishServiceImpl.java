package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.model.Dish;
import kg.attractor.java25.labwork63_edufood.repo.DishRepo;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepo repo;
    @Override
    public Page<DishDto> getDishesByRestaurantId(Long id, Pageable pageable) {
        log.info("Получение блюд для ресторана с id: {} (страница: {}, размер: {})", id, pageable.getPageNumber(), pageable.getPageSize());

        return repo.findAllByRestaurant_Id(id, pageable)
                .map(dish -> {
                    DishDto dto = new DishDto();
                    dto.setId(dish.getId());
                    dto.setName(dish.getName());
                    dto.setDescription(dish.getDescription());
                    dto.setPrice(dish.getPrice());
                    dto.setImageUrl(dish.getImageUrl());

                    RestaurantDto restaurantDto = new RestaurantDto();
                    restaurantDto.setId(dish.getRestaurant().getId());
                    restaurantDto.setName(dish.getRestaurant().getName());
                    restaurantDto.setDescription(dish.getRestaurant().getDescription());
                    restaurantDto.setImageUrl(dish.getRestaurant().getImageUrl());

                    dto.setRestaurant(restaurantDto);

                    return dto;
                });
    }


    @Override
    public Dish findById(Long dishId) {
        log.debug("Поиск блюда по id: {}", dishId);
        return repo.findById(dishId).orElse(null);
    }
}
