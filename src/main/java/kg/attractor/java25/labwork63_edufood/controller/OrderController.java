package kg.attractor.java25.labwork63_edufood.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.java25.labwork63_edufood.dto.CartItemDto;
import kg.attractor.java25.labwork63_edufood.model.Dish;
import kg.attractor.java25.labwork63_edufood.model.Order;
import kg.attractor.java25.labwork63_edufood.model.OrderItems;
import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.service.DishService;
import kg.attractor.java25.labwork63_edufood.service.OrderService;
import kg.attractor.java25.labwork63_edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final DishService dishService;
    private final OrderService orderService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @PostMapping("/order/checkout")
    public String checkout(@CookieValue(value = "cart", defaultValue = "[]") String cartJsonEncoded,
                           @AuthenticationPrincipal UserDetails userDetails,
                           HttpServletResponse response) {
        try {
            String cartJson = URLDecoder.decode(cartJsonEncoded, StandardCharsets.UTF_8);
            List<CartItemDto> cartItems = objectMapper.readValue(cartJson, new TypeReference<>() {});

            if (cartItems.isEmpty()) {
                log.info("User {} tried to checkout with an empty cart", userDetails.getUsername());
                return "redirect:/cart";
            }

            User user = userService.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Order order = new Order();
            order.setUser(user);

            BigDecimal total = BigDecimal.ZERO;
            List<OrderItems> orderItems = new ArrayList<>();

            for (CartItemDto item : cartItems) {
                Dish dish = dishService.findById(item.dishId);
                if (dish == null) {
                    log.warn("Dish with id {} not found while user {} was checking out", item.dishId, user.getEmail());
                    continue;
                }

                OrderItems orderItem = new OrderItems();
                orderItem.setOrder(order);
                orderItem.setDish(dish);
                orderItem.setQuantity(item.quantity);

                BigDecimal itemTotal = dish.getPrice().multiply(BigDecimal.valueOf(item.quantity));
                total = total.add(itemTotal);

                orderItems.add(orderItem);
            }


            if (orderItems.isEmpty()) {
                log.warn("User {} tried to checkout but all dishes in cart were invalid", user.getEmail());
                return "redirect:/cart";
            }

            order.setOrderItems(orderItems);
            order.setTotalPrice(total);

            orderService.save(order);
            log.info("User {} placed an order with id {}", user.getEmail(), order.getId());

            Cookie cookie = new Cookie("cart", "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            return "redirect:/profile/orders";

        } catch (Exception e) {
            log.error("Error during checkout for user {}", userDetails != null ? userDetails.getUsername() : "anonymous", e);
            return "redirect:/cart?error";
        }
    }
}
