package kg.attractor.java25.labwork63_edufood.controller;

import kg.attractor.java25.labwork63_edufood.dto.OrderDto;
import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.service.OrderService;
import kg.attractor.java25.labwork63_edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final UserService userService;
    private final OrderService orderService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/orders")
    public String ordersPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<OrderDto> orders = orderService.findByUserOrderByCreatedAtDesc(user);
        model.addAttribute("orders", orders);

        log.info("User {} accessed their orders page, {} orders found", user.getEmail(), orders.size());

        return "profile/orders";
    }
}
