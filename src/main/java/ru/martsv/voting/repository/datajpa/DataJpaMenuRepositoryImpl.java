package ru.martsv.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.martsv.voting.model.Menu;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.repository.MenuRepository;
import ru.martsv.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
@Repository
public class DataJpaMenuRepositoryImpl implements MenuRepository {
    @Autowired
    private ProxyMenuRepository proxy;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProxyRestaurantRepository restaurantProxy;

    @Override
    @Transactional
    public Menu update(Menu Menu, int restaurantId) {
        if (get(Menu.getId(), restaurantId) == null) {
            return null;
        }
        Menu.setRestaurant(restaurantProxy.getOne(restaurantId));
        return proxy.save(Menu);
    }

    @Override
    @Transactional
    public Menu create(Menu Menu, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        if (restaurant == null) {
            return null;
        }
        Menu.setRestaurant(restaurant);
        return proxy.save(Menu);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return proxy.delete(id, restaurantId) != 0;
    }

    @Override
    public boolean deleteOnDate(LocalDate date, int restaurantId) { return proxy.deleteOnDate(date, restaurantId) != 0; }

    @Override
    public Menu get(int id, int restaurantId) {
        return proxy.get(id, restaurantId);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return proxy.getAll(restaurantId);
    }

    @Override
    public List<Menu> getOnDate(LocalDate date, int restaurantId) {
        return proxy.getOnDate(date, restaurantId);
    }
}
