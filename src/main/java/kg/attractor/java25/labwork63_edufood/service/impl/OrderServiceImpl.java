package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.dto.DishDto;
import kg.attractor.java25.labwork63_edufood.dto.OrderDto;
import kg.attractor.java25.labwork63_edufood.dto.OrderItemDto;
import kg.attractor.java25.labwork63_edufood.dto.RestaurantDto;
import kg.attractor.java25.labwork63_edufood.model.Order;
import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.repo.OrderRepo;
import kg.attractor.java25.labwork63_edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo repo;
    @Override
    public void save(Order order) {
        repo.save(order);
        log.info("Сохранён новый заказ: ID = {}, пользователь = {}, сумма = {}",
                order.getId(),
                order.getUser().getEmail(),
                order.getTotalPrice()
        );
    }

    @Override
    public List<OrderDto> findByUserOrderByIdDesc(User user) {
        log.info("Получение заказов по ID по убыванию для пользователя: {}", user.getEmail());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return repo.findAllByUserOrderByIdDesc(user)
                .stream()
                .map(order -> {
                    OrderDto orderDto = new OrderDto();
                    orderDto.setId(order.getId());
                    orderDto.setUserId(order.getUser().getId());
                    orderDto.setTotalPrice(order.getTotalPrice());
                    orderDto.setCreatedAt(order.getCreatedAt().format(formatter));
                    return orderDto;
                }).toList();
    }

    @Override
    public List<OrderDto> findByUserOrderByCreatedAtDesc(User user) {
        log.info("Получение заказов по дате по убыванию для пользователя: {}", user.getEmail());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return repo.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(order -> {
                    OrderDto dto = new OrderDto();
                    dto.setId(order.getId());
                    dto.setUserId(order.getUser().getId());
                    dto.setTotalPrice(order.getTotalPrice());
                    dto.setCreatedAt(order.getCreatedAt().format(formatter));

                    List<OrderItemDto> orderItemDtos = order.getOrderItems()
                            .stream()
                            .map(orderItem -> {
                                OrderItemDto orderItemDto = new OrderItemDto();
                                orderItemDto.setId(orderItem.getId());
                                orderItemDto.setQuantity(orderItem.getQuantity());

                                DishDto dishDto = new DishDto();
                                dishDto.setId(orderItem.getDish().getId());
                                dishDto.setName(orderItem.getDish().getName());
                                dishDto.setDescription(orderItem.getDish().getDescription());
                                dishDto.setPrice(orderItem.getDish().getPrice());
                                dishDto.setImageUrl(orderItem.getDish().getImageUrl());

                                RestaurantDto restaurantDto = new RestaurantDto();
                                restaurantDto.setId(orderItem.getDish().getRestaurant().getId());
                                restaurantDto.setName(orderItem.getDish().getRestaurant().getName());
                                restaurantDto.setDescription(orderItem.getDish().getRestaurant().getDescription());
                                restaurantDto.setImageUrl(orderItem.getDish().getRestaurant().getImageUrl());

                                dishDto.setRestaurant(restaurantDto);

                                orderItemDto.setDish(dishDto);

                                OrderDto orderDtoInItem = new OrderDto();
                                orderDtoInItem.setId(orderItem.getOrder().getId());
                                orderDtoInItem.setUserId(orderItem.getOrder().getUser().getId());
                                orderDtoInItem.setTotalPrice(orderItem.getOrder().getTotalPrice());
                                orderDtoInItem.setCreatedAt(orderItem.getOrder().getCreatedAt().format(formatter));

                                orderItemDto.setOrder(orderDtoInItem);

                                return orderItemDto;
                            }).toList();

                    dto.setOrderItems(orderItemDtos);
                    return dto;
                }).toList();
    }

}
