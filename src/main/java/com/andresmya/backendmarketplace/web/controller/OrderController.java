package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.Order;
import com.andresmya.backendmarketplace.domain.OrderTransaction;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateOrderRequest;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateOrderTransactionRequest;
import com.andresmya.backendmarketplace.domain.dto.response.CreateOrderResponse;
import com.andresmya.backendmarketplace.domain.service.OrderService;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize(RoleService.HAS_ROLE_ADMIN_OR_CUSTOMER)
@RestController
@RequestMapping("/customers")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<Page<Order>> getAllOrdersByCustomerId(@PathVariable("customerId") Integer customerId,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size")int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(orderService.getAllOrdersByCustomerId(pageable, customerId), HttpStatus.OK);
    }

    @PostMapping("/{customerId}/orders")
    public ResponseEntity<CreateOrderResponse> createOrder(@PathVariable("customerId") Integer customerId,
                                                           @RequestBody CreateOrderRequest request) throws Exception {
        return new ResponseEntity<>(orderService.createOrder(customerId, request), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("customerId") Integer customerId,
                                              @PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(orderService.getOrderById(customerId, id), HttpStatus.OK);
    }

    @PostMapping("/{customerId}/orders/{orderId}/transactions")
    public ResponseEntity<OrderTransaction> createOrderTransaction(@PathVariable("customerId") Integer customerId,
                                                                   @PathVariable("orderId") Long orderId,
                                                                   @RequestBody CreateOrderTransactionRequest request) throws Exception {
        return new ResponseEntity<>(orderService.addOrderTransaction(customerId, orderId, request), HttpStatus.CREATED);
    }

    @GetMapping("{customerId}/orders/{orderId}/transactions")
    public ResponseEntity<List<OrderTransaction>> getOrderTransactionsByOrderId(@PathVariable("customerId") Integer customerId,
                                                                                @PathVariable("orderId") Long orderId) throws Exception {
        return new ResponseEntity<>(orderService.getOrderTransactionsByOrderId(customerId, orderId), HttpStatus.OK);
    }
}
