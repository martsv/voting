package ru.martsv.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.martsv.voting.model.Menu;
import ru.martsv.voting.repository.MenuRepository;
import ru.martsv.voting.util.exception.ExceptionUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Override
    public Menu get(int id, int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public void delete(int id, int restaurantId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public void deleteOnDate(LocalDate date, int restaurantId) { repository.deleteOnDate(date, restaurantId); }

    @Override
    public List<Menu> getOnDate(LocalDate date, int restaurantId) {
        return repository.getOnDate(date, restaurantId);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(repository.update(menu, restaurantId), menu.getId());
    }

    @Override
    public Menu save(Menu menu, int restaurantId) {
        return ExceptionUtil.checkNotFoundParentWithId(repository.create(menu, restaurantId), restaurantId);
    }
}
