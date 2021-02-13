package com.epam.jwd.finaltask.service.impl;

import com.epam.jwd.finaltask.dao.IUserDao;
import com.epam.jwd.finaltask.dao.impl.UserDaoImpl;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserServiceImpl implements IUserService {
    static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);
    private IUserDao UserDao = new UserDaoImpl();

    @Override
    public List<User> getUsers() {
        try {
            return UserDao.getUsers();
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getUsers(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public int create(String name, String password, int userRoleId, String guestName, String mobile, String email, String address) {
        try {
            UserDao.create(name, password, userRoleId, guestName, mobile, email, address);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method createUsers(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public int update(int userId, String name, String password, int userRoleId, String guestName, String mobile, String email, String address) {
        try {
            return UserDao.update(userId, name, password, userRoleId, guestName, mobile, email, address);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method updateUsers(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public int delete(int userId) {
        try {
            return UserDao.delete(userId);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method deleteUsers(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public User getUserById(int UserId) {
        try {
            List<User> users = UserDao.getUsers();
            for (User user : users) {
                if (user.getUserId() == UserId) {
                    return user;
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getUserById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try {
            return UserDao.getUserByNameAndPassword(name, password);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getUserByNameAndPassword(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public Guest getGuestByUserId(int userId) {
        try {
            return UserDao.getGuestByUserId(userId);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getGuestByUserId(): " + e.getMessage());
        }
        return null;
    }
}
