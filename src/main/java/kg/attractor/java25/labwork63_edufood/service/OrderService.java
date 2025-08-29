package kg.attractor.java25.labwork63_edufood.service;

import kg.attractor.java25.labwork63_edufood.dto.OrderDto;
import kg.attractor.java25.labwork63_edufood.model.Order;
import kg.attractor.java25.labwork63_edufood.model.User;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<OrderDto> findByUserOrderByIdDesc(User user);

    List<OrderDto> findByUserOrderByCreatedAtDesc(User user);
}
