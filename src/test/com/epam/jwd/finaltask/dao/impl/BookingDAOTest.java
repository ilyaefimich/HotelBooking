package test.com.epam.jwd.finaltask.dao.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ResourceBundle;

@Test
public class BookingDAOTest {
    private final static String SELECT_BOOKINGS =
            "SELECT b.checkInDate, b.checkOutDate, b.adultsCount, b.roomTypeId, rt.name roomTypeName, b.bookingStatusId, bs.name bookingStatusName, \n" +
                    "b.bookingId, b.childrenCount, b.comment, bs.name bookingStatusName, g.guestId, g.name guestName, g.mobile, g.email, g.address, \n" +
                    "r.roomId, r.name roomName, r.roomStatusId, pm.paymentMethodId, pm.name paymentMethodName, pi.PaymentInfoId, pi.sum, pi.transactionId, pi.transactionDate, pi.transactionStatus \n" +
                    "FROM booking b \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId \n" +
                    "LEFT OUTER JOIN bookingStatus bs ON bs.bookingStatusId = b.bookingStatusId \n" +
                    "LEFT OUTER JOIN guest g ON g.GuestId = b.GuestId \n" +
                    "LEFT OUTER JOIN room r ON r.roomId = b.roomId \n" +
                    "LEFT OUTER JOIN paymentmethod pm ON pm.paymentMethodId = b.PreferedpaymentMethodId \n" +
                    "LEFT OUTER JOIN paymentinfo pi ON pi.PaymentInfoId= b.PaymentInfoId \n";

    @Test
    public void testGetBookings() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.configuration.database");
            String DRIVER = resourceBundle.getString("db.driver");
            String URL = resourceBundle.getString("db.url");
            String LOGIN = resourceBundle.getString("db.login");
            String PASSWORD = resourceBundle.getString("db.password");

            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOKINGS);
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                rs.beforeFirst();
                rs.last();
                int size = rs.getRow();
                Assert.assertTrue(size > 0);
            }


        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
