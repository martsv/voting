package ru.martsv.voting.repository;

import ru.martsv.voting.model.Restaurant;

import java.util.List;

/**
 * mart
 * 20.08.2016
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant user);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
