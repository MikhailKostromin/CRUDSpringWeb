package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Контрроллер пользователей.
 */
@Controller
public class UserController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор класса.
     * @param userService сервис пользователй.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение всех пользователей.
     * @param model модель для передачи данных в представление.
     * @return представление со списком пользователей.
     */

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    /**
     * Создание нового пользователя.
     * @param user объект  пользователя.
     * @return представление для создания пользователя.
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * Получение данных о новом пользователе с формы представления.
     * @param user объект пользователя с заполненными полями(валидация не проводится).
     * @return перенаправление на страницу со списком пользователей.
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя.
     *
     * @param id уникальный идентификатор пользователя.
     * @return перенаправление на страницу со списком пользоватедей.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect: /users";
    }

    /**
     * Изменение данных пользователя.
     * @param id    уникальный идентификатор пользователя.
     * @param model модель для передачи данных в представление.
     * @return предоставление для изменения данных
     */
    @GetMapping("/user-update/{id}")
    public String updateUserFrom(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Получение данных об измененом пользователе с формы представления.
     * @return перенаправление на страницу со списком пользователей.
     */
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
