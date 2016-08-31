package ru.martsv.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.martsv.voting.AuthorizedUser;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static ru.martsv.voting.model.Restaurant.ADD_VOTE;
import static ru.martsv.voting.model.Restaurant.GET_WINNERS;

/**
 * mart
 * 20.08.2016
 */
@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProxyRestaurantRepository proxy;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return proxy.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return proxy.findAll();
    }

    @Override
    public long getVotesOnDate(int id, LocalDate date) {
        return proxy.getVotesOnDate(id, date);
    }

    @Override
    public List<Restaurant> getWinnersOnDate(LocalDate date) {
        return em.createNamedQuery(GET_WINNERS, Restaurant.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @Transactional
    public void addVote(int id) {
        em.createNamedQuery(ADD_VOTE)
                .setParameter("userId", AuthorizedUser.id())
                .setParameter("restaurantId", id)
                .setParameter("date", LocalDate.now())
                .executeUpdate();
    }
}
