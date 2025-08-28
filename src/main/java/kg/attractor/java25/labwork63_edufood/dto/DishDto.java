package kg.attractor.java25.labwork63_edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long restaurantId;
}
