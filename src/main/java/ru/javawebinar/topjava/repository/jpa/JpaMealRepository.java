package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User udb = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(udb);
            em.persist(meal);
            return meal;
        }
        else {
            Meal old = get(meal.getId(), userId);
            if (old!=null) {
                meal.setUser(udb);
                em.merge(meal);
                return meal;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User udb = em.getReference(User.class, userId);
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("user", udb).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        User udb = em.getReference(User.class, userId);
        List<Meal> meals =  em.createNamedQuery(Meal.GET, Meal.class).setParameter("id", id).setParameter("user", udb).getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        User udb = em.getReference(User.class, userId);
        return em.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter("user", udb).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        User udb = em.getReference(User.class, userId);
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter(1, udb)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}