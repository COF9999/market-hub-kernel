package com.market_hub.kernel.master.user.infraestructure.adapters.implementations;

import com.market_hub.kernel.master.global.domain.ports.DaoCrudPort;
import com.market_hub.kernel.master.user.domain.ports.currentInterfaces.UserDaoPort;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserEmanagerAdapter implements DaoCrudPort<User>, UserDaoPort {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Object> findByEmail(String email) {
        try {
            Query query =  entityManager.createQuery("select u from User u where u.email=?1", User.class);
            query.setParameter(1, email);
            return Optional.ofNullable(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public void activeDeleted(Long id) {
        User user = entityManager.find(User.class,id);
        user.setIsActive(false);
        update(user);
    }

    @Override
    public List<User> selectAll() {
        return List.of();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(entityManager.find(User.class,id));
    }


    @Override
    public Optional<User> create(User object) {
        entityManager.persist(object);
        return Optional.ofNullable(object);
    }


    @Override
    public Optional<User> update(User object) {
        return Optional.ofNullable(entityManager.merge(object));
    }


    @Override
    public Optional<User> delete(Long id) {
       return null;
    }
}
