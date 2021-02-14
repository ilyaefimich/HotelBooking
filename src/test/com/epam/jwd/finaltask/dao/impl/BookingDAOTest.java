package test.com.epam.jwd.finaltask.dao.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Unit test which validates CRUD methods of the BookingDAO.
 */
@Test
public class BookingDAOTest {
    @BeforeClass
    public void beforeClass() {
        try {

            String DRIVER = resourceBundle.getString("db.driver");

            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.configuration.database");
    private final static String URL = resourceBundle.getString("db.url");
    private final static String LOGIN = resourceBundle.getString("db.login");
    private final static String PASSWORD = resourceBundle.getString("db.password");

    private final static String SELECT_BOOKINGS =
            "SELECT COUNT(*)\n" +
                    "FROM booking b \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId \n" +
                    "LEFT OUTER JOIN bookingStatus bs ON bs.bookingStatusId = b.bookingStatusId \n" +
                    "LEFT OUTER JOIN guest g ON g.GuestId = b.GuestId \n" +
                    "LEFT OUTER JOIN room r ON r.roomId = b.roomId \n" +
                    "LEFT OUTER JOIN paymentmethod pm ON pm.paymentMethodId = b.PreferedpaymentMethodId \n" +
                    "LEFT OUTER JOIN paymentinfo pi ON pi.PaymentInfoId= b.PaymentInfoId \n";

    /**
     * Tests the selection of bookings.
     */
    @Test
    public void testGetBookings() {
        int size = 0;
        try {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOKINGS)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            size = rs.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(size > 0);
    }
}
