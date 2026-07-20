package com.ppf.restaurant.controller;

import com.ppf.restaurant.dto.CreateMenuItemRequest;
import com.ppf.restaurant.dto.MenuItemResponse;
import com.ppf.restaurant.dto.UpdateMenuItemRequest;
import com.ppf.restaurant.entity.MenuItem;
import com.ppf.restaurant.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItemResponse getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponse createMenuItem(@Valid @RequestBody CreateMenuItemRequest request) {
        return menuItemService.createMenuItem(request);
    }

    @PutMapping("/{id}")
    public MenuItemResponse updateMenuItem(@PathVariable Long id, @Valid @RequestBody UpdateMenuItemRequest request) {
        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}