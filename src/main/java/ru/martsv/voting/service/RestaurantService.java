package ru.martsv.voting.service;

import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 20.08.2016
 */
public interface RestaurantService {
    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    long getVotesOnDate(int id, LocalDate date);

    List<Restaurant> getWinnersOnDate(LocalDate date);

    void addVote(int id);
}
