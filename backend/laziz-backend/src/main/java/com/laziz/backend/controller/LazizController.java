package com.laziz.backend.controller;

import com.laziz.backend.entity.*;
import com.laziz.backend.service.LazizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LazizController {

    @Autowired
    private LazizService lazizService;

    // 1. Authentication
    @PostMapping("/auth/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(lazizService.registerUser(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Optional<User>> loginUser(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(lazizService.loginUser(email, password));
    }

    // 2. Menu Management
    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        return ResponseEntity.ok(lazizService.getAllMenuItems());
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> addMenuItem(@RequestBody Menu menu) {
        return ResponseEntity.ok(lazizService.addMenuItem(menu));
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable String id, @RequestBody Menu menu) {
        return ResponseEntity.ok(lazizService.updateMenuItem(id, menu));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        lazizService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/menu/{id}/sizes")
    public ResponseEntity<List<MenuSize>> getMenuSizes(@PathVariable String id) {
        return ResponseEntity.ok(lazizService.getMenuSizes(id));
    }

    // 3. Add-ons Management
    @GetMapping("/addons")
    public ResponseEntity<List<Addon>> getAllAddons() {
        return ResponseEntity.ok(lazizService.getAllAddons());
    }

    @PostMapping("/addons")
    public ResponseEntity<Addon> addAddon(@RequestBody Addon addon) {
        return ResponseEntity.ok(lazizService.addAddon(addon));
    }

    @PutMapping("/addons/{id}")
    public ResponseEntity<Addon> updateAddon(@PathVariable String id, @RequestBody Addon addon) {
        return ResponseEntity.ok(lazizService.updateAddon(id, addon));
    }

    @DeleteMapping("/addons/{id}")
    public ResponseEntity<Void> deleteAddon(@PathVariable String id) {
        lazizService.deleteAddon(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Special Offers Management
    @GetMapping("/offers")
    public ResponseEntity<List<SpecialOffer>> getAllSpecialOffers() {
        return ResponseEntity.ok(lazizService.getAllSpecialOffers());
    }

    @PostMapping("/offers")
    public ResponseEntity<SpecialOffer> addSpecialOffer(@RequestBody SpecialOffer specialOffer) {
        return ResponseEntity.ok(lazizService.addSpecialOffer(specialOffer));
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<SpecialOffer> updateSpecialOffer(@PathVariable String id, @RequestBody SpecialOffer specialOffer) {
        return ResponseEntity.ok(lazizService.updateSpecialOffer(id, specialOffer));
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<Void> deleteSpecialOffer(@PathVariable String id) {
        lazizService.deleteSpecialOffer(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Cart Management
    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<Cart>> getUserCart(@PathVariable String userId) {
        return ResponseEntity.ok(lazizService.getUserCart(userId));
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(lazizService.addToCart(cart));
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable String id, @RequestBody Cart cart) {
        return ResponseEntity.ok(lazizService.updateCartItem(id, cart));
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String id) {
        lazizService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Order Management
    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, @RequestBody List<OrderItem> orderItems) {
        return ResponseEntity.ok(lazizService.placeOrder(order, orderItems));
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        return ResponseEntity.ok(lazizService.getUserOrders(userId));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @RequestParam String status) {
        return ResponseEntity.ok(lazizService.updateOrderStatus(id, status));
    }

    // 7. Order History
    @GetMapping("/order-history/{userId}")
    public ResponseEntity<List<OrderHistory>> getOrderHistory(@PathVariable String userId) {
        return ResponseEntity.ok(lazizService.getOrderHistory(userId));
    }

    // 8. Address Management
    @GetMapping("/addresses/{userId}")
    public ResponseEntity<List<Address>> getUserAddresses(@PathVariable String userId) {
        return ResponseEntity.ok(lazizService.getUserAddresses(userId));
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        return ResponseEntity.ok(lazizService.addAddress(address));
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @RequestBody Address address) {
        return ResponseEntity.ok(lazizService.updateAddress(id, address));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        lazizService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
