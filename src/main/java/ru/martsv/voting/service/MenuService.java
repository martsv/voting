package ru.martsv.voting.service;

import ru.martsv.voting.model.Menu;
import ru.martsv.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
public interface MenuService {
    Menu get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    void deleteOnDate(LocalDate date, int restaurantId);

    List<Menu> getOnDate(LocalDate date, int restaurantId);

    List<Menu> getAll(int restaurantId);

    Menu update(Menu meal, int restaurantId) throws NotFoundException;

    Menu save(Menu meal, int restaurantId);
}
