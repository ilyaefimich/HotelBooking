package com.epam.jwd.finaltask.dao.impl;

import com.epam.jwd.finaltask.dao.IUserDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements IUserDao {
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final static String DELETE_USER = "DELETE FROM `web`.`user`\n" +
            "    WHERE userId = ?;";

    private final static String INSERT_USER = "INSERT INTO `web`.`user` (`name`, `password`, `roleId`) " +
            "VALUES (?, ?, ?);";

    private final static String INSERT_GUEST = "INSERT INTO `web`.`guest` (`name`, `mobile`, `email`, 'address', 'userid') " +
            "VALUES (?, ?, ?, ?, ?);";

    private final static String UPDATE_USER = "UPDATE `web`.`user`\n" +
            "    SET\n" +
            "            name = ?,\n" +
            "            password = ?,\n" +
            "            roleId = ?\n" +
            "    WHERE userId = ?;";

    private final static String UPDATE_GUEST = "UPDATE `web`.`guest`\n" +
            "    SET\n" +
            "            name = ?,\n" +
            "            mobile = ?,\n" +
            "            email = ?,\n" +
            "            address = ?\n" +
            "    WHERE userId = ?;";

    private final static String SELECT_USERS =
            "SELECT u.userId, u.name, u.password, u.roleId, ur.name  \n" +
                    "FROM web.user u\n" +
                    "LEFT OUTER JOIN UserRole ur ON ur.roleId = u.roleId;";

    private final static String SELECT_FIND_USER_BYNAMEANDPASSWORD =
            "SELECT u.userId, u.name, u.password, u.roleId, ur.name  \n" +
                    "FROM web.user u\n" +
                    "LEFT OUTER JOIN UserRole ur ON ur.roleId = u.roleId\n" +
                    "WHERE u.name = ? AND u.password = ?;";

    private final static String SELECT_USER_BY_ID =
            "SELECT u.userId, u.name, u.password, u.roleId, ur.name  \n" +
                    "FROM web.user u\n" +
                    "LEFT OUTER JOIN UserRole ur ON ur.roleId = u.roleId\n" +
                    "WHERE u.userId = ?;";

    private final static String SELECT_GUEST_BY_USER_ID =
            "SELECT g.GuestId, g.name, g.mobile, g.email, g.address\n" +
                    "FROM web.guest g\n" +
                    "WHERE g.userId = ?";


    @Override
    public Guest getGuestByUserId(int userId) throws DAOException {
        Guest guest = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_GUEST_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                guest = new Guest();
                guest.setGuestId(rs.getInt(1));
                guest.setName(rs.getString(2));
                guest.setMobile(rs.getString(3));
                guest.setEmail(rs.getString(4));
                guest.setAddress(rs.getString(5));
                guest.setUserId(userId);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to find such a user: " + e);
        }
        return guest;
    }

    @Override
    public List<User> getUsers() throws DAOException {
        List<User> users = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_USERS);
            ResultSet rs = statement.executeQuery();

            users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));

                UserRole userRole = new UserRole();
                userRole.setRoleId(rs.getInt(4));
                userRole.setRoleName(rs.getString(5));
                user.setUserRole(userRole);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to load users: " + e);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) throws DAOException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));

                UserRole userRole = new UserRole();
                userRole.setRoleId(rs.getInt(4));
                userRole.setRoleName(rs.getString(5));
                user.setUserRole(userRole);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to find such a user: " + e);
        }
        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws DAOException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_FIND_USER_BYNAMEANDPASSWORD);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));

                UserRole userRole = new UserRole();
                userRole.setRoleId(rs.getInt(4));
                userRole.setRoleName(rs.getString(5));
                user.setUserRole(userRole);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to find uch a user: " + e);
        }
        return user;
    }

    @Override
    public int delete(int userid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, userid);
            int result = statement.executeUpdate();
            statement.closeOnCompletion();
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when deleting a user: " + e);
        }
    }

    @Override
    public int create(String name, String password, int userRoleId, String guestName, String mobile, String email, String address) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setInt(3, userRoleId);

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            int newUserId = -1;
            if (rs.next()) {
                newUserId = rs.getInt(1);
            }

            statement = connection.prepareStatement(INSERT_GUEST);
            statement.setString(1, guestName);
            statement.setString(2, mobile);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setInt(5, newUserId);

            return statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when creating: " + e);
        }
    }

    @Override
    public int update(int userId, String name, String password, int userRoleId, String guestName, String mobile, String email, String address) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setInt(3, userRoleId);
            statement.setInt(4, userId);

            statement.executeUpdate();

            statement = connection.prepareStatement(UPDATE_GUEST);
            statement.setString(1, guestName);
            statement.setString(2, mobile);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setInt(5, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when updating: " + e);
        }
    }
}
