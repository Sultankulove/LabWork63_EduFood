package kg.attractor.java25.labwork63_edufood.controller;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import kg.attractor.java25.labwork63_edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final DishService dishService;


    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<RestaurantDto> restaurants = restaurantService.getRestaurants(PageRequest.of(page, 5));
        model.addAttribute("restaurants", restaurants);
        return "home";
    }

    @GetMapping("/restaurants/{id}")
    public String restaurantDetails(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, Model model) {

    RestaurantDto restaurant = restaurantService.getById(id);
    if (restaurant == null) {
        return "redirect:/";
    }
    Page<DishDto> dishes = dishService.getDishesByRestaurantId(id, PageRequest.of(page, 10));

    model.addAttribute("restaurant", restaurant);
    model.addAttribute("dishes", dishes);
    return "restaurant";
    }

    @PostMapping("/restaurants")
    public String findByNameRestaurants(@RequestParam String name, @RequestParam(defaultValue = "0") int page, Model model) {
        List<RestaurantDto> found = restaurantService.searchByName(name, PageRequest.of(page, 10));
        model.addAttribute("restaurants", found);
        return "restaurant";
    }
}
