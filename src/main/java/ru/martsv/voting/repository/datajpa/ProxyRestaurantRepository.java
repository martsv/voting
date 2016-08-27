package ru.martsv.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.martsv.voting.model.Restaurant;

import java.util.List;

/**
 * mart
 * 20.08.2016
 */
@Transactional(readOnly = true)
public interface ProxyRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Restaurant findOne(Integer id);

    @Override
    @Query("SELECT r FROM Restaurant r ORDER BY r.name")
    List<Restaurant> findAll();

}
