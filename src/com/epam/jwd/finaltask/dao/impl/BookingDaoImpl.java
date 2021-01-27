package com.epam.jwd.finaltask.dao.impl;

import com.epam.jwd.finaltask.dao.IBookingDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.*;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookingDaoImpl implements IBookingDao {

    static final Logger logger = LoggerFactory.getLogger(BookingDaoImpl.class);

    private final static String DELETE_BOOKING = "DELETE FROM `web`.`booking`\n" +
            "    WHERE bookingId = ?;";

    private final static String UPDATE_BOOKING = "UPDATE `web`.`booking`\n" +
            "    SET\n" +
            "            checkInDate = ?,\n" +
            "            checkOutDate = ?,\n" +
            "            adultsCount = ?,\n" +
            "            childrenCount = ?,\n" +
            "            comment = ?,\n" +
            "            bookingStatusId = ?,\n" +
            "            roomTypeId = ?,\n" +
            "            GuestId = ?\n" +
            "    WHERE bookingId = ?;";

    private final static String INSERT_BOOKING = "INSERT INTO `web`.`booking` (`checkInDate`, `checkOutDate`, `adultsCount`, `childrenCount`, `comment`, `bookingStatusId`, `roomTypeId`, `GuestId`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

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


    @Override
    public List<Booking> getBookings() throws DAOException {
        List<Booking> bookings = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_BOOKINGS);
            ResultSet rs = statement.executeQuery();

            bookings = new ArrayList<Booking>();
            Booking booking;
            while (rs.next()) {
                booking = new Booking();
                booking.setCheckInDate(rs.getDate(1).toLocalDate());
                booking.setCheckOutDate(rs.getDate(2).toLocalDate());
                booking.setAdultsCount(rs.getInt(3));
                booking.setBookingId(rs.getInt(8));


                Room room = new Room();
                room.setName(rs.getString(17));
                booking.setOfferedRoom(room);

                RoomType roomType = new RoomType();
                roomType.setRoomTypeId(rs.getInt(4));
                roomType.setName(rs.getString(5));
                booking.setRoomType(roomType);

                Guest guest = new Guest();
                guest.setAddress(rs.getString(16));
                guest.setEmail(rs.getString(15));
                guest.setMobile(rs.getString(14));
                guest.setName(rs.getString(13));
                booking.setGuest(guest);

                BookingStatus bookingStatus = new BookingStatus();
                bookingStatus.setBookingStatusId(rs.getInt(6));
                bookingStatus.setName(rs.getString(7));
                booking.setBookingStatus(bookingStatus);

                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setPaymentInfoId(rs.getInt(22));
                Date transactionDate = rs.getDate(25);
                if (transactionDate!=null) {
                    paymentInfo.setTransactionDate(transactionDate.toLocalDate());
                }
                paymentInfo.setTransactionId(rs.getInt(24));
                paymentInfo.setTransactionStatus(rs.getInt(26));
                paymentInfo.setSum(rs.getInt(23));
                booking.setPaymentInfo(paymentInfo);

                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setPaymentMethodId(rs.getInt(20));
                paymentMethod.setName(rs.getString(21));
                booking.setSelectedPaymentMethod(paymentMethod);


/*                PreferedPaymentMethod preferedPaymentMethod = new PreferedPaymentMethod();
                preferedPaymentMethod.setPreferedPaymentMethodId(rs.getInt(rs.getInt(27)));
                preferedPaymentMethod.setCardholderName(rs.getString(28));
                preferedPaymentMethod.setCardNumber(rs.getInt(29));
                preferedPaymentMethod.setCsvCode(rs.getInt(30));
                preferedPaymentMethod.setExpirationDate(rs.getDate(31).toLocalDate());
                preferedPaymentMethod.setName(rs.getString(32)); */

                bookings.add(booking);


            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to load bookings: " + e);
        }
        return bookings;
    }

    @Override
    public void delete(int bookingid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOKING);
            statement.setInt(1, bookingid);
            int result = statement.executeUpdate();
            statement.closeOnCompletion();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when deleting a booking: " + e);
        }
    }
    @Override
    public void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Booking booking = new Booking();

            PreparedStatement statement = connection.prepareStatement(INSERT_BOOKING);
            statement.setDate(1, Date.valueOf(checkInDate));
            statement.setDate(2, Date.valueOf(checkOutDate));
            statement.setInt(3, adultsCount);
            statement.setInt(4, childrenCount);
            statement.setString(5, comment);
            statement.setInt(6, 1);
            statement.setInt(7, roomTypeId);
            statement.setInt(8, guestId);

            int result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when creating a booking: " + e);
        }
    }

    @Override
    public void update(int bookingid, String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Booking booking = new Booking();

            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKING);
            statement.setDate(1, Date.valueOf(checkInDate));
            statement.setDate(2, Date.valueOf(checkOutDate));
            statement.setInt(3, adultsCount);
            statement.setInt(4, 0);
            statement.setString(5, "");
            statement.setInt(6, newStatus);
            statement.setInt(7, 1);
            statement.setInt(8, 1);
            statement.setInt(9, bookingid);

            int result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when updating a booking: " + e);
        }
    }

}
