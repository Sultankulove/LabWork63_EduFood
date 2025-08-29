package kg.attractor.java25.labwork63_edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private String createdAt;
    private List<OrderItemDto> orderItems;

}
