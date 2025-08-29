package kg.attractor.java25.labwork63_edufood.controller;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import kg.attractor.java25.labwork63_edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final DishService dishService;


    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<RestaurantDto> restaurants = restaurantService.getRestaurants(PageRequest.of(page, 5));
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("currentPage", restaurants.getNumber());
        model.addAttribute("totalPages", restaurants.getTotalPages());
        log.info("Home page accessed, page={}", page);
        return "home";
    }

    @GetMapping("/restaurants/{id}")
    public String restaurantDetails(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model) {
        RestaurantDto restaurant = restaurantService.getById(id);
        if (restaurant == null) {
            log.warn("Restaurant with id={} not found, redirecting to home", id);
            return "redirect:/";
        }

        Page<DishDto> dishes = dishService.getDishesByRestaurantId(id, PageRequest.of(page, 10));

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("dishes", dishes);
        model.addAttribute("currentPage", dishes.getNumber());
        model.addAttribute("totalPages", dishes.getTotalPages());

        log.info("Displaying details for restaurant id={}, page={}", id, page);
        return "restaurant";
    }



    @GetMapping("/search")
    public String searchRestaurants(@RequestParam String name, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<RestaurantDto> found = restaurantService.searchByName(name, PageRequest.of(page, 5));
        model.addAttribute("restaurants", found);
        model.addAttribute("searchQuery", name);
        model.addAttribute("currentPage", found.getNumber());
        model.addAttribute("totalPages", found.getTotalPages());
        log.info("Search restaurants by name='{}', page={}", name, page);
        return "home";
    }
}
