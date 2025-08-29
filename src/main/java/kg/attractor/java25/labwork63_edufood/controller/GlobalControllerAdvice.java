package kg.attractor.java25.labwork63_edufood.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.java25.labwork63_edufood.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalControllerAdvice {
    private final ObjectMapper objectMapper;

    @ModelAttribute("cartItemCount")
    public int getCartItemCount(HttpServletRequest request) {
        int totalQuantity = 0;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    try {
                        String cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                        List<CartItemDto> cartItems = objectMapper.readValue(cartJson, new TypeReference<>() {});
                        totalQuantity += cartItems.stream().mapToInt(CartItemDto::getQuantity).sum();
                    } catch (Exception e) {
                        log.warn("Failed to decode or parse cart cookie", e);
                    }
                    break;
                }
            }
        }
        return totalQuantity;
    }
}
