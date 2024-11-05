package com.market_hub.kernel.master.role.domain.ports.apadters;

import com.market_hub.kernel.master.role.domain.ports.RolePort;
import com.market_hub.kernel.master.role.infraestructure.model.Role;
import com.market_hub.kernel.master.user.infraestructure.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public class RoleEmanagerAdapter implements RolePort {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Object> findRoleByName(String name) {
        try {
            Query query = entityManager.createQuery("select r from Role r where r.name=?1",Role.class);
            query.setParameter(1,name);
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }

    }
}
