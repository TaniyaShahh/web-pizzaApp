package com.laziz.backend.service;

import com.laziz.backend.entity.*;
import com.laziz.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LazizService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private AddonRepository addonRepository;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuSizeRepository menuSizeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 1. Authentication
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    // 2. Menu Management
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenuItem(String id, Menu updatedMenu) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            Menu existingMenu = menu.get();
            existingMenu.setName(updatedMenu.getName());
            existingMenu.setDescription(updatedMenu.getDescription());
            existingMenu.setPrice(updatedMenu.getPrice());
            existingMenu.setCategory(updatedMenu.getCategory());
            existingMenu.setImageUrl(updatedMenu.getImageUrl());
            existingMenu.setAvailable(updatedMenu.isAvailable());
            return menuRepository.save(existingMenu);
        }
        return null;
    }

    public void deleteMenuItem(String id) {
        menuRepository.deleteById(id);
    }

    public List<MenuSize> getMenuSizes(String menuId) {
        return menuSizeRepository.findByMenuId(menuId);
    }

    // 3. Add-ons Management
    public List<Addon> getAllAddons() {
        return addonRepository.findAll();
    }

    public Addon addAddon(Addon addon) {
        return addonRepository.save(addon);
    }

    public Addon updateAddon(String id, Addon updatedAddon) {
        Optional<Addon> addon = addonRepository.findById(id);
        if (addon.isPresent()) {
            Addon existingAddon = addon.get();
            existingAddon.setName(updatedAddon.getName());
            existingAddon.setPrice(updatedAddon.getPrice());
            existingAddon.setAvailable(updatedAddon.isAvailable());
            return addonRepository.save(existingAddon);
        }
        return null;
    }

    public void deleteAddon(String id) {
        addonRepository.deleteById(id);
    }

    // 4. Special Offers Management
    public List<SpecialOffer> getAllSpecialOffers() {
        return specialOfferRepository.findAll();
    }

    public SpecialOffer addSpecialOffer(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    public SpecialOffer updateSpecialOffer(String id, SpecialOffer updatedOffer) {
        Optional<SpecialOffer> offer = specialOfferRepository.findById(id);
        if (offer.isPresent()) {
            SpecialOffer existingOffer = offer.get();
            existingOffer.setOfferName(updatedOffer.getOfferName());
            existingOffer.setDescription(updatedOffer.getDescription());
            existingOffer.setPrice(updatedOffer.getPrice());
            existingOffer.setValidFrom(updatedOffer.getValidFrom());
            existingOffer.setValidTo(updatedOffer.getValidTo());
            return specialOfferRepository.save(existingOffer);
        }
        return null;
    }

    public void deleteSpecialOffer(String id) {
        specialOfferRepository.deleteById(id);
    }

    // 5. Cart Management
    public List<Cart> getUserCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCartItem(String id, Cart updatedCart) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            Cart existingCart = cart.get();
            existingCart.setQuantity(updatedCart.getQuantity());
            return cartRepository.save(existingCart);
        }
        return null;
    }

    public void removeCartItem(String id) {
        cartRepository.deleteById(id);
    }

    // 6. Order Management
    public Order placeOrder(Order order, List<OrderItem> orderItems) {
        Order savedOrder = orderRepository.save(order);
        for (OrderItem item : orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(String id, String status) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setOrderStatus(status);
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    // 7. Order History
    public List<OrderHistory> getOrderHistory(String userId) {
        return orderHistoryRepository.findByUserId(userId);
    }

    // 8. Address Management
    public List<Address> getUserAddresses(String userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(String id, Address updatedAddress) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            Address existingAddress = address.get();
            existingAddress.setAddressLine1(updatedAddress.getAddressLine1());
            existingAddress.setAddressLine2(updatedAddress.getAddressLine2());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setZipCode(updatedAddress.getZipCode());
            existingAddress.setCountry(updatedAddress.getCountry());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    public void deleteAddress(String id) {
        addressRepository.deleteById(id);
    }
}
