package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.domain.repository.IOrderProductRepository;
import com.andresmya.backendmarketplace.persistence.entity.OrderProductEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IOrderProductJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IOrderProductPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductPersistence implements IOrderProductRepository {

    @Autowired
    private IOrderProductJpaRepository jpaRepository;

    @Autowired
    private IOrderProductPersistenceMapper mapper;

    @Override
    public List<OrderProduct> getProductsByOrderId(Long orderId) {
        return jpaRepository.findAllByOrderId(orderId);
    }

    @Override
    public List<OrderProduct> saveOrderProducts(List<OrderProduct> orderProductList){
        List<OrderProductEntity> orderProductEntities = jpaRepository.saveAll(mapper.toOrderProductEntities(orderProductList));
        return mapper.toOrderProducts(orderProductEntities);
    }

    @Override
    public void deleteByList(List<OrderProduct> orderProductList) {
        jpaRepository.deleteAll(mapper.toOrderProductEntities(orderProductList));
    }

}
