package com.food.OnlineFoodOrdering.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.Address;
import com.food.OnlineFoodOrdering.Model.Cart;
import com.food.OnlineFoodOrdering.Model.CartItem;
import com.food.OnlineFoodOrdering.Model.Order;
import com.food.OnlineFoodOrdering.Model.OrderItem;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.repository.AddressRepository;
import com.food.OnlineFoodOrdering.repository.OrderItemRepository;
import com.food.OnlineFoodOrdering.repository.OrderRepository;
import com.food.OnlineFoodOrdering.repository.UserRepository;
import com.food.OnlineFoodOrdering.request.OrderRequest;


@Service
public class OrderServiceImpl implements OrderService {
       
       @Autowired
       private OrderRepository orderRepository;

       @Autowired
       private OrderItemRepository orderItemRepository;


       @Autowired
       private AddressRepository addressRepository;


       @Autowired
       private UserRepository userRepository;


       @Autowired
       private RestaurantService restaurantService;

       @Autowired
       private CartService cartService;
      

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
       Address shippingAddress = order.getDeliveryAddress();
       Address savedAddress = addressRepository.save(shippingAddress);
       if(!user.getAddresses().contains(savedAddress)){
        user.getAddresses().add(savedAddress);
        userRepository.save(user);
       }

       Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
       
       Order createdOrder = new Order();
       createdOrder.setCustomer(user);
       createdOrder.setCreatedAt(new Date());
       createdOrder.setOrderStatus("pending");
       createdOrder.setDeliveryAddress(savedAddress);
       createdOrder.setRestaurant(restaurant);
       
       Cart cart = cartService.findCartByUserId(user.getId());

      List<OrderItem> orderItems = new ArrayList<>();

      for(CartItem cartItem : cart.getItems()){
        OrderItem orderItem = new OrderItem();
        orderItem.setFood(cartItem.getFood());
        orderItem.setIngredients(cartItem.getIngredients());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setTotalPrice(cartItem.getTotalPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        orderItems.add(savedOrderItem);
    }

       Long totalPrice = cartService.calculateCartTotals(cart);

       createdOrder.setItems(orderItems);
       createdOrder.setTotalPrice(totalPrice);
       Order savedOrder = orderRepository.save(createdOrder);
       restaurant.getOrders().add(savedOrder);
       return createdOrder;

    }

    @Override
    public Order updateOrder(Long OrderId, String OrderStatus) throws Exception {
        Order order =  findOrderById(OrderId);
        if(OrderStatus.equals("OUT_FOR_DELIVERY")
        ||OrderStatus.equals("DELIVERED")
        ||OrderStatus.equals("COMPLETED")
        ||OrderStatus.equals("PENDING")){
            order.setOrderStatus(OrderStatus);
            return orderRepository.save(order);
        }
       throw new  Exception("Please select a valid Order status");


    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
      
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
         return  orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders=  orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders=orders.stream().filter(order->
                  order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("orders not found");
        }
        return optionalOrder.get();
    }

    
}
