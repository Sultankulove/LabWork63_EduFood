package kg.attractor.java25.labwork63_edufood.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.java25.labwork63_edufood.dto.CartItemDto;
import kg.attractor.java25.labwork63_edufood.model.Dish;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final DishService dishService;
    private final ObjectMapper objectMapper;

    @GetMapping("/cart")
    public String showCart(@CookieValue(value = "cart", defaultValue = "[]") String cartJsonEncoded, Model model) throws Exception {
        try {
            String cartJson = URLDecoder.decode(cartJsonEncoded, StandardCharsets.UTF_8);
            List<CartItemDto> cartItems = objectMapper.readValue(cartJson, new TypeReference<>() {});
            List<Map<String, Object>> dishesWithQty = new ArrayList<>();

            for (CartItemDto item : cartItems) {
                Dish dish = dishService.findById(item.dishId);
                if (dish != null) {
                    dishesWithQty.add(Map.of(
                            "dish", dish,
                            "restaurant", dish.getRestaurant(),
                            "quantity", item.quantity
                    ));
                }
            }

            model.addAttribute("cartItems", dishesWithQty);
            log.info("User viewed cart with {} items", cartItems.size());
            return "cart";
        } catch (Exception e) {
            log.error("Failed to decode or parse cart cookie", e);
            model.addAttribute("cartItems", List.of());
            return "cart";
        }
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long dishId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @CookieValue(value = "cart", defaultValue = "[]") String cartJsonEncoded,
                            HttpServletResponse response,
                            @RequestHeader(value = "referer", required = false) String referer) throws Exception {

        if (quantity < 1 || quantity > 100) {
            log.warn("Некорректное количество: {}. Допустимо от 1 до 100", quantity);
            return "redirect:" + (referer != null ? referer : "/") + "?error=invalid_quantity";
        }

        try {
            String cartJson = URLDecoder.decode(cartJsonEncoded, StandardCharsets.UTF_8);

            List<CartItemDto> cartItems = objectMapper.readValue(cartJson, new TypeReference<>() {});

            boolean found = false;
            for (CartItemDto item : cartItems) {
                if (item.dishId.equals(dishId)) {
                    item.quantity += quantity;
                    found = true;
                    break;
                }
            }

            if (!found) {
                cartItems.add(new CartItemDto(dishId, quantity));
            }

            String updatedCart = objectMapper.writeValueAsString(cartItems);
            String encodedCart = URLEncoder.encode(updatedCart, StandardCharsets.UTF_8);

            Cookie cookie = new Cookie("cart", encodedCart);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 3600);
            response.addCookie(cookie);

            log.info("Added dish id={} quantity={} to cart. Total items now: {}", dishId, quantity, cartItems.size());

        } catch (Exception e) {
            log.error("Error adding dish id={} to cart", dishId, e);
        }

        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long dishId,
                                 @CookieValue(value = "cart", defaultValue = "[]") String cartJsonEncoded,
                                 HttpServletResponse response) {
        try {
            List<CartItemDto> cartItems = objectMapper.readValue(URLDecoder.decode(cartJsonEncoded, StandardCharsets.UTF_8), new TypeReference<>() {});
            int beforeSize = cartItems.size();

            cartItems.removeIf(item -> item.dishId.equals(dishId));

            String updatedCart = objectMapper.writeValueAsString(cartItems);
            String encodedCart = URLEncoder.encode(updatedCart, StandardCharsets.UTF_8);

            Cookie cookie = new Cookie("cart", encodedCart);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 3600);
            response.addCookie(cookie);

            log.info("Removed dish id={} from cart. Items before: {}, after: {}", dishId, beforeSize, cartItems.size());

        } catch (Exception e) {
            log.error("Error removing dish id={} from cart", dishId, e);
        }

        return "redirect:/cart";
    }
}
