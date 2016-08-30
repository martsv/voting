package ru.martsv.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.repository.RestaurantRepository;
import ru.martsv.voting.util.exception.ExceptionUtil;
import ru.martsv.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 20.08.2016
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @Override
    public long getVotesOnDate(int id, LocalDate date) {
        return repository.getVotesOnDate(id, date);
    }

    @Override
    public List<Restaurant> getWinnersOnDate(LocalDate date) {
        return repository.getWinnersOnDate(date);
    }
}
