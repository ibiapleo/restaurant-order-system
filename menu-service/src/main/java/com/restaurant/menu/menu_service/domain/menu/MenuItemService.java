package com.restaurant.menu.menu_service.domain.menu;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ModelMapper mapper;

    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .map(item -> mapper.map(item, MenuItemResponse.class))
                .collect(Collectors.toList());
    }

    public MenuItemResponse getMenuItemById(UUID id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));
        return mapper.map(menuItem, MenuItemResponse.class);
    }

    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItem menuItem = mapper.map(request, MenuItem.class);
        return mapper.map(menuItemRepository.save(menuItem), MenuItemResponse.class);
    }

    public MenuItemResponse updateMenuItem(UUID id, MenuItemRequest request) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setCategory(request.getCategory());

        return mapper.map(menuItemRepository.save(existing), MenuItemResponse.class);
    }

    public void deleteMenuItem(UUID id) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        menuItemRepository.delete(existing);
    }
}