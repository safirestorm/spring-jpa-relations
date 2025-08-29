package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static OrderDto toDto(Order order) {
        List<OrderLineDto> orderLines = new ArrayList<>();

        for (var line : order.getOrderLines()) {
            orderLines.add(toDto(line));
        }

        return new OrderDto(order.getId(), order.getOrderDate(), order.getStatus().name(), orderLines);
    }

    public static OrderLineDto toDto(OrderLine orderLine) {
        return new OrderLineDto(orderLine.getId(), orderLine.getUnitPrice(), orderLine.getQuantity(), orderLine.getProduct());
    }

    public static Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderDate(orderDto.orderDate());
        order.setStatus(OrderStatus.valueOf(orderDto.status()));

        for (var lineDto : orderDto.orderLines()) {
            var line = toEntity(lineDto);
            order.addOrderLine(line);
        }

        return order;
    }

    public static OrderLine toEntity(OrderLineDto orderLineDto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(orderLineDto.product());
        orderLine.setUnitPrice(orderLineDto.unitPrice());
        orderLine.setQuantity(orderLineDto.quantity());
        return orderLine;
    }
}
