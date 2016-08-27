package ru.martsv.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.martsv.voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
@Transactional(readOnly = true)
public interface ProxyMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.date=:date")
    int deleteOnDate(@Param("date") LocalDate date, @Param("restaurantId") int restaurantId);

    @Override
    Menu save(Menu item);

    @Query("SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    Menu get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC, m.dish")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m from Menu m WHERE m.restaurant.id=:restaurantId AND m.date=:date ORDER BY m.dish")
    List<Menu> getOnDate(@Param("date") LocalDate date, @Param("restaurantId") int restaurantId);
}
