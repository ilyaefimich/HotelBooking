package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;

import java.util.List;
/**
 * Provides methods for application to manage users.
 */
public interface IUserService {
    List<User> getUsers();
    int create(String name, String password, int userRoleId, String guestName, String mobile, String email, String address);
    int update(int userId, String name, String password, int userRoleId, String guestName, String mobile, String email, String address);
    int delete(int userId);
    User getUserById(int userId);
    User getUserByNameAndPassword(String name, String password);
    Guest getGuestByUserId(int userId);
}
