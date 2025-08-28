package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.model.Restaurant;
import kg.attractor.java25.labwork63_edufood.repo.RestaurantRepo;
import kg.attractor.java25.labwork63_edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo repo;

    @Override
    public Page<RestaurantDto> getRestaurants(PageRequest pageRequest) {
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
        Restaurant restaurant = repo.findById(id).orElse(null);
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setImageUrl(restaurant.getImageUrl());
        return dto;
    }

    @Override
    public List<RestaurantDto> searchByName(String name, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(name, pageable).stream().map(restaurant -> {
            RestaurantDto dto = new RestaurantDto();
            dto.setId(restaurant.getId());
            dto.setName(restaurant.getName());
            dto.setDescription(restaurant.getDescription());
            dto.setImageUrl(restaurant.getImageUrl());
            return dto;
        }).toList();
    }
}
