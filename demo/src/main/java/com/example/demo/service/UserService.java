package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный класс для работы с пользователями.
 */
@Service
public class UserService {
    /**
     * Объект репозитория для работы с БД.
     */
    private final UserRepository userRepository;

    /**
     * Конструктор класса.
     * @param userRepository репозиторий пользователей.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id){
        return userRepository.finfUserById(id);
    }

    /**
     * Получение списка всех пользователей.
     * @return список пользователей.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Создание нового пользователя.
     * @param user объект пользователя.
     * @return объект пользователя с присвоенным id
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя.
     * @param id уникальный индентификатор пользователя
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Изменения данных пользователя
     * @param user объект пользователя с новыми данными.
     */
    public void updateUser(User user) {
        userRepository.update(user);
    }
}
