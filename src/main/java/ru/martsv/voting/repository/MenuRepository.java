package ru.martsv.voting.repository;

import ru.martsv.voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
public interface MenuRepository {
    // null if updated menu do not belong to restaurantId
    Menu update(Menu Menu, int restaurantId);

    // null if restaurantId not found
    Menu create(Menu Menu, int restaurantId);

    // false if menu do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    boolean deleteOnDate(LocalDate date, int restaurantId);

    // null if menu do not belong to restaurantId
    Menu get(int id, int restaurantId);

    List<Menu> getAll(int restaurantId);

    List<Menu> getOnDate(LocalDate date, int restaurantId);
}
