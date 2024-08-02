package com.dsproject.order.services;

import com.dsproject.order.clients.BusinessServiceClient;
import com.dsproject.order.clients.CustomerServiceClient;
import com.dsproject.order.clients.ProductServiceClient;
import com.dsproject.order.dto.BusinessDTO;
import com.dsproject.order.dto.CustomerDTO;
import com.dsproject.order.dto.OrderDTO;
import com.dsproject.order.dto.ProductDTO;
import com.dsproject.order.entities.Order;
import com.dsproject.order.entities.OrderRequest;
import com.dsproject.order.repositories.OrderRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ProductServiceClient productServiceClient;
    private final BusinessServiceClient businessServiceClient;
    private final CustomerServiceClient customerServiceClient;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(ProductServiceClient productServiceClient,
                        BusinessServiceClient businessServiceClient,
                        CustomerServiceClient customerServiceClient,
                        OrderRepository orderRepository) {
        this.productServiceClient = productServiceClient;
        this.businessServiceClient = businessServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public OrderDTO createOrder(OrderRequest orderRequest) {
        ProductDTO product = productServiceClient.getProductById(orderRequest.getProductId());
        System.out.println("buyer id:");
        System.out.println(orderRequest.getBuyerId());
        System.out.println("seller id:");
        System.out.println(orderRequest.getSellerId());
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // Validate and fetch buyer details
        if ("BUSINESS".equals(orderRequest.getBuyerType())) {
            BusinessDTO buyer = businessServiceClient.getBusinessById(orderRequest.getBuyerId());
            if (buyer == null) {
                throw new RuntimeException("Business buyer not found");
            }
        } else if ("CUSTOMER".equals(orderRequest.getBuyerType())) {
            CustomerDTO buyer = customerServiceClient.getCustomerById(orderRequest.getBuyerId());
            if (buyer == null) {
                throw new RuntimeException("Customer buyer not found");
            }
        } else {
            throw new RuntimeException("Invalid buyer type");
        }

        // Validate and fetch seller details
        if ("BUSINESS".equals(orderRequest.getSellerType())) {
            BusinessDTO seller = businessServiceClient.getBusinessById(orderRequest.getSellerId());
            if (seller == null) {
                throw new RuntimeException("Business seller not found");
            }
        } else if ("CUSTOMER".equals(orderRequest.getSellerType())) {
            CustomerDTO seller = customerServiceClient.getCustomerById(orderRequest.getSellerId());
            if (seller == null) {
                throw new RuntimeException("Customer seller not found");
            }
        } else {
            throw new RuntimeException("Invalid seller type");
        }

        // Create and save the order
        Order order = new Order();
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(product.getPrice().longValue());
        order.setBuyerId(orderRequest.getBuyerId());
        order.setBuyerType(orderRequest.getBuyerType());
        order.setSellerId(orderRequest.getSellerId());
        order.setSellerType(orderRequest.getSellerType());
        order.setAmount(product.getPrice().doubleValue() * orderRequest.getQuantity());
        order.setStatus("PENDING");

        Order savedOrder = orderRepository.save(order);

        // Convert Order entity to OrderDTO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(savedOrder.getId());
        orderDTO.setProductId(savedOrder.getProductId());
        orderDTO.setQuantity(savedOrder.getQuantity());
        orderDTO.setPrice(savedOrder.getPrice());
        orderDTO.setBuyerId(savedOrder.getBuyerId());
        orderDTO.setBuyerType(savedOrder.getBuyerType());
        orderDTO.setSellerId(savedOrder.getSellerId());
        orderDTO.setSellerType(savedOrder.getSellerType());
        orderDTO.setAmount(savedOrder.getAmount());
        orderDTO.setStatus(savedOrder.getStatus());

        return orderDTO;
    }
}
