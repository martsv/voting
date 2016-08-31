package ru.martsv.voting.repository;

import ru.martsv.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 20.08.2016
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    long getVotesOnDate(int id, LocalDate date);

    List<Restaurant> getWinnersOnDate(LocalDate date);

    void addVote(int id);
}
