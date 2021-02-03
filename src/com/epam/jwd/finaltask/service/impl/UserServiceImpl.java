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
    static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    private IUserDao UserDao = new UserDaoImpl();

    @Override
    public List<User> getUsers() {
        try {
            return UserDao.getUsers();
        } catch (DAOException e) {
            logger.error("Error when calling the method getUsers(): " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(String name, String password, int userRoleId, String guestName, String mobile, String email, String address) {
        try {
            return UserDao.create(name, password, userRoleId, guestName, mobile, email, address);
        } catch (DAOException e) {
            logger.error("Error when calling the method createUsers(): " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(int userId, String name, String password, int userRoleId, String guestName, String mobile, String email, String address) {
        try {
            return UserDao.update(userId, name, password, userRoleId, guestName, mobile, email, address);
        } catch (DAOException e) {
            logger.error("Error when calling the method updateUsers(): " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int userId) {
        try {
            return UserDao.delete(userId);
        } catch (DAOException e) {
            logger.error("Error when calling the method deleteUsers(): " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getUserById(int UserId) {
        try {
            List<User> Users = UserDao.getUsers();
            for (User User : Users) {
                if (User.getUserId() == UserId) {
                    return User;
                }
            }
        } catch (DAOException e) {
            logger.error("Error when calling the method getUserById(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try {
            return UserDao.getUserByNameAndPassword(name, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Guest getGuestByUserId(int userId) {
        try {
            return UserDao.getGuestByUserId(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        List<User> users = userService.getUsers();

        for (User user : users) {
            logger.debug(user.toString());
            //System.out.println(booking);
        }

    }
}
