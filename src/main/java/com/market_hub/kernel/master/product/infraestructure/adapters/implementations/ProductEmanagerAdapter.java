package com.market_hub.kernel.master.product.infraestructure.adapters.implementations;

import com.market_hub.kernel.master.global.domain.ports.DaoCrudPort;
import com.market_hub.kernel.master.product.domain.currentInterfaces.ProductDao;
import com.market_hub.kernel.master.product.infraestructure.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class ProductEmanagerAdapter implements DaoCrudPort<Product>, ProductDao {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Product> selectAll() {
        return List.of();
    }

    @Override
    public Optional<Product> get(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class,id));
    }

    @Override
    public Optional<Product> create(Product object) {
        entityManager.persist(object);
        return Optional.ofNullable(object);
    }

    @Override
    public Optional<Product> update(Product object) {
        return Optional.ofNullable(entityManager.merge(object));
    }

    @Override
    public Optional<Product> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> allProductOfUser(String identification, Boolean isChanged) {
        Query query =  entityManager.createQuery("select p from Product p where p.user.email =?1 and p.isChanged=?2",Product.class);
        query.setParameter(1, identification);
        query.setParameter(2,isChanged);
        return query.getResultList();
    }

    @Override
    public List<Product> findBySku(String sku) {
        Query query = entityManager.createQuery("select p from Product p where p.sku = ?1",Product.class);
        query.setParameter(1,sku);
        return query.getResultList();
    }

    @Override
    public void activeDeleted(Long id) {

    }
}
