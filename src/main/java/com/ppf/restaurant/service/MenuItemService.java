package com.ppf.restaurant.service;

import com.ppf.restaurant.dto.CreateMenuItemRequest;
import com.ppf.restaurant.dto.MenuItemResponse;
import com.ppf.restaurant.dto.UpdateMenuItemRequest;
import com.ppf.restaurant.entity.MenuItem;
import com.ppf.restaurant.exception.ResourceNotFoundException;
import com.ppf.restaurant.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MenuItemResponse getMenuItemById(Long id) {
        MenuItem menuItem = getMenuItemEntityById(id);
        return mapToResponse(menuItem);
    }

    public MenuItemResponse createMenuItem(CreateMenuItemRequest request) {
        MenuItem menuItem = new MenuItem(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCategory(),
                request.getActive() == null || request.getActive()
        );

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return mapToResponse(savedMenuItem);
    }

    public MenuItemResponse updateMenuItem(Long id, UpdateMenuItemRequest request) {
        MenuItem existingMenuItem = getMenuItemEntityById(id);

        existingMenuItem.setName(request.getName());
        existingMenuItem.setDescription(request.getDescription());
        existingMenuItem.setPrice(request.getPrice());
        existingMenuItem.setCategory(request.getCategory());

        if (request.getActive() != null) {
            existingMenuItem.setActive(request.getActive());
        }

        MenuItem savedMenuItem = menuItemRepository.save(existingMenuItem);

        return mapToResponse(savedMenuItem);
    }

    public void deleteMenuItem(Long id) {
        MenuItem existingMenuItem = getMenuItemEntityById(id);
        menuItemRepository.delete(existingMenuItem);
    }

    private MenuItem getMenuItemEntityById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));
    }

    private MenuItemResponse mapToResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getCategory(),
                menuItem.getActive()
        );
    }
}