package ru.martsv.voting.service;

import ru.martsv.voting.model.User;
import ru.martsv.voting.to.UserTo;
import ru.martsv.voting.util.exception.NotFoundException;

import java.util.List;

/**
 * mart
 * 28.08.2016
 */
public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(UserTo user);

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);

}
