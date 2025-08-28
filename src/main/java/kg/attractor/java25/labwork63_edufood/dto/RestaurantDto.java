package kg.attractor.java25.labwork63_edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
}
