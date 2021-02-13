package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;

import java.util.List;

public interface IUserDao {
    List<User> getUsers() throws DAOException;
    User getUserById(int userId) throws DAOException;
    User getUserByNameAndPassword(String name, String password) throws DAOException;
    Guest getGuestByUserId(int userId) throws DAOException;
    int delete(int userid) throws DAOException;
    void create(String name, String password, int userRoleId, String guestName, String mobile, String email, String address) throws DAOException;
    int update(int userId, String name, String password, int userRoleId, String guestName, String mobile, String email, String address) throws DAOException;
}

