package ru.martsv.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.martsv.voting.model.User;
import ru.martsv.voting.repository.UserRepository;

import java.util.List;

/**
 * mart
 * 28.08.2016
 */
@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private ProxyUserRepository proxy;

    @Override
    public User save(User user) {
        return proxy.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxy.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll();
    }


}
