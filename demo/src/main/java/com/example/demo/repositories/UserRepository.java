package com.example.demo.repositories;

import com.example.demo.model.User;
import com.example.demo.utils.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для запроса к БД сущности пользователей.
 */
@Repository
public class UserRepository {
    /**
     * Объект подключения к БД
     */
    private final JdbcTemplate jdbc;

    /**
     * Конструктора класса
     * @param jdbc
     */
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public User finfUserById(Integer id){
        String sql = "SELECT * FROM userTable WHERE id=?";
        RowMapper<User> userRowMapper = (r,i)->{
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.query(sql,new Object[]{id}, new UserMapper())
                .stream().findFirst().orElse(null);
    }

    /**
     * Получение всех пользователей из БД
     * @return список пользователей.
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

//        RowMapper<User> userRowMapper = (r, i) -> {
//            User rowObject = new User();
//            rowObject.setId(r.getInt("id"));
//            rowObject.setFirstName(r.getString("firstName"));
//            rowObject.setLastName(r.getString("lastName"));
//            return rowObject;
//        };
        return jdbc.query(sql, new UserMapper());
    }

    /**
     * Сохранение пользователя в БД
     * @param user объект пользователя.
     * @return объект пользователя с id.
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Удаление пользователя из БД
     * по ID
     * @param id идентификатор пользователя
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    /**
     * Обновление пользователя в БД
     * @param user объект пользователя с изменнеными данными.
     */
    public void  update(User user){
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql,user.getFirstName(),user.getLastName(),user.getId());
    }
}
