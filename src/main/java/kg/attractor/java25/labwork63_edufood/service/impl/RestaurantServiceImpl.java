package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.model.Restaurant;
import kg.attractor.java25.labwork63_edufood.repo.RestaurantRepo;
import kg.attractor.java25.labwork63_edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo repo;

    @Override
    public Page<RestaurantDto> getRestaurants(PageRequest pageRequest) {
        log.info("Запрос списка ресторанов: страница {}, размер {}", pageRequest.getPageNumber(), pageRequest.getPageSize());

        return repo.findAll(pageRequest)
                .map(restaurant -> {
                    RestaurantDto dto = new RestaurantDto();
                            dto.setId(restaurant.getId());
                            dto.setName(restaurant.getName());
                            dto.setDescription(restaurant.getDescription());
                            dto.setImageUrl(restaurant.getImageUrl());
                            return dto;
                }
        );
    }

    @Override
    public RestaurantDto getById(Long id) {

        log.info("Получение ресторана по ID: {}", id);

        Restaurant restaurant = repo.findById(id).orElse(null);
        if (restaurant == null) {
            log.warn("Ресторан с ID {} не найден", id);
            return null;
        }

        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setImageUrl(restaurant.getImageUrl());
        return dto;
    }

    @Override
    public Page<RestaurantDto> searchByName(String name, Pageable pageable) {
        log.info("Поиск ресторанов по имени: '{}', страница {}, размер {}", name, pageable.getPageNumber(), pageable.getPageSize());

        return repo.findByNameContainingIgnoreCase(name, pageable)
                .map(restaurant -> {
                    RestaurantDto dto = new RestaurantDto();
                    dto.setId(restaurant.getId());
                    dto.setName(restaurant.getName());
                    dto.setDescription(restaurant.getDescription());
                    dto.setImageUrl(restaurant.getImageUrl());
                    return dto;
        });
    }
}
