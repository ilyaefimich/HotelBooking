package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    int create(String name, String pasword, int userRoleId);

    int update(int userId, String name, String pasword, int userRoleId);

    int delete(int userId);

    User getUserById(int userId);

    User getUserByNameAndPassword(String name, String password);
}
