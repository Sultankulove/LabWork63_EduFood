package kg.attractor.java25.labwork63_edufood.service;

import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    Page<RestaurantDto> getRestaurants(PageRequest of);

    RestaurantDto getById(Long id);

    Page<RestaurantDto> searchByName(String name, Pageable pageable);
}
