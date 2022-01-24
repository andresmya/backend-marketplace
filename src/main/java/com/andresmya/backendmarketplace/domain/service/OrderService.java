package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.Order;
import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.domain.OrderStatus;
import com.andresmya.backendmarketplace.domain.OrderTransaction;
import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateOrderRequest;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateOrderTransactionRequest;
import com.andresmya.backendmarketplace.domain.dto.response.CreateOrderResponse;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import com.andresmya.backendmarketplace.persistence.OrderPersistence;
import com.andresmya.backendmarketplace.persistence.OrderProductPersistence;
import com.andresmya.backendmarketplace.persistence.OrderTransactionPersistence;
import com.andresmya.backendmarketplace.persistence.OrderStatusPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderPersistence orderPersistence;

    @Autowired
    private OrderStatusPersistence orderStatusPersistence;

    @Autowired
    private OrderProductPersistence orderProductPersistence;

    @Autowired
    private OrderTransactionPersistence orderTransactionPersistence;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAddressService customerAddressService;


    public CreateOrderResponse createOrder(Integer customerId, CreateOrderRequest request) throws Exception{
        if (!customerService.existsById(customerId)) throw new NotFoundException("Customer ID " + customerId);
        Order order = orderPersistence.createOrder(fromCreateOrderRequestToOrder(request, customerId));
        List<OrderProduct> orderProductList = createOrderProductsRelationships(request.getProducts(), order.getId());
        orderProductPersistence.saveOrderProducts(orderProductList);
        OrderTransaction firstOrderTransaction= createDefaultFirstOrderTransaction(customerId, order.getId(), orderProductList);
        return new CreateOrderResponse(order.getId(), order.getCustomerAddress(), orderProductList, firstOrderTransaction);
    }

    public Order getOrderById(Integer customerId, Long id) throws Exception{
        validateIfCustomerExists(customerId);
        Order order = orderPersistence.getOrderById(id).orElseThrow(() -> new NotFoundException("Order ID " + id));
        if (!order.getCustomerId().equals(customerId)) throw new NotFoundException("Order ID " + id + "for Customer ID " + customerId);
        return order;
    }

    public Page<Order> getAllOrdersByCustomerId(Pageable pageable, Integer customerId) throws Exception{
        validateIfCustomerExists(customerId);
        return orderPersistence.getOrdersByCustomerId(customerId, pageable);
    }

    public OrderTransaction addOrderTransaction(Integer customerId, Long orderId, CreateOrderTransactionRequest request) throws Exception {
        validateIfCustomerExists(customerId);
        OrderStatus orderStatus = orderStatusPersistence.getOrderStatusById(request.getOrderStatusId())
                .orElseThrow(() -> new NotFoundException("Order Status ID " + request.getOrderStatusId()));
        if (orderPersistence.existsById(orderId)){
            return orderTransactionPersistence.createOrderTransaction(new OrderTransaction(orderId, orderStatus));
        }
        throw new NotFoundException("Order ID " + orderId);
    }

    public List<OrderTransaction> getOrderTransactionsByOrderId(Integer customerId, Long orderId) throws Exception{
        validateIfCustomerExists(customerId);
        if (!orderPersistence.existsById(orderId)) throw new NotFoundException("Order ID " + orderId);
        return orderTransactionPersistence.getOrderTransactionsByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException("Transactions for order " + orderId));
    }

    private Order fromCreateOrderRequestToOrder(CreateOrderRequest request, Integer customerId) throws Exception{
        CustomerAddress customerAddress = customerAddressService.getById(request.getCustomerAddressId());
        if (customerId != customerAddress.getCustomerId()) throw new NotFoundException("Address ID " + request.getCustomerAddressId() + " for Customer ID " + customerId);
        Order newOrder = new Order();
        newOrder.setCustomerId(customerId);
        newOrder.setCustomerAddress(customerAddress);
        return newOrder;
    }

    private OrderTransaction createDefaultFirstOrderTransaction(Integer customerId, Long orderId, List<OrderProduct> orderProductList) throws Exception {
        try {
            OrderStatus orderStatus = orderStatusPersistence.getOrderStatusByName("PENDING PAYMENT").orElseThrow(() -> new NotFoundException("Default first transaction"));
            CreateOrderTransactionRequest request = new CreateOrderTransactionRequest(orderStatus.getId());
            return addOrderTransaction(customerId, orderId, request);

        } catch (Exception e){
            orderPersistence.deleteById(orderId);
            orderProductPersistence.deleteByList(orderProductList);
            throw e;
        }
    }

    private List<OrderProduct> createOrderProductsRelationships(List<CreateOrderRequest.ProductQuantity> productsQuantities, Long orderId) throws Exception {
        List<Long> ids = productsQuantities.stream().map(CreateOrderRequest.ProductQuantity::getProductId).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIdList(ids);

        if(products.size() != productsQuantities.size()) throw new InvalidArgumentException("Products IDs");

        try {
            List<OrderProduct> orderProductList = products.stream().map(product -> {

                CreateOrderRequest.ProductQuantity request = productsQuantities.stream()
                                .filter(productQuantity -> productQuantity.getProductId().equals(product.getId()))
                                .findAny().orElseThrow();

                if(request.getQuantity() > product.getStock()) return null;
                product.setStock(product.getStock() - request.getQuantity());

                return createNewOrderProduct(product, product.getPrice(), request.getQuantity(), orderId);

            }).collect(Collectors.toList());

            updateStock(products);
            return orderProductList;

        } catch (Exception e) {
            orderPersistence.deleteById(orderId);
            throw new InvalidArgumentException("Validate products IDs and stock");
        }
    }

    private OrderProduct createNewOrderProduct(Product product, BigDecimal price, Integer quantity, Long orderId){
        return new OrderProduct(product, price, quantity, orderId);
    }

    private void updateStock(List<Product> products){
        productService.updateAllProductsByList(products);
    }

    private void validateIfCustomerExists(Integer customerId) throws Exception{
        if (!customerService.existsById(customerId)) throw new NotFoundException("Customer ID " + customerId);
    }

}
