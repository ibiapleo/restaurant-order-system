package com.restaurant.order.order_service.client;


import com.restaurant.order.order_service.model.dtos.MenuItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "menu-service", url = "${services.menu.url}")
public interface MenuServiceClient {

    @GetMapping("/api/v1/menu-items/{id}")
    MenuItemResponse getMenuItemById(@PathVariable("id") UUID id);
}