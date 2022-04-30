package com.epam.jwd.finaltask.dao.impl;

import com.epam.jwd.finaltask.dao.IBookingDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

interface BookingDaoQueries {
    String UPDATE_BOOKING = "UPDATE `web`.`booking`\n" +
            "    SET \n" +
            "            bookingStatusId = ?, \n" +
            "            roomId = COALESCE(?, roomId), \n" +
            "            price = COALESCE(?, price), \n" +
            "            preferedpaymentmethodid = COALESCE(?, preferedpaymentmethodid) \n" +
            "    WHERE bookingId = ?;";
}

public class BookingDaoImpl implements IBookingDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingDaoImpl.class);

    private static final String DELETE_BOOKING = "DELETE FROM `web`.`booking`\n" +
            "    WHERE bookingId = ?;";

    private static final String UPDATE_BOOKING = "UPDATE `web`.`booking`\n" +
            "    SET \n" +
            "            bookingStatusId = ?, \n" +
            "            roomId = COALESCE(?, roomId), \n" +
            "            price = COALESCE(?, price), \n" +
            "            preferedpaymentmethodid = COALESCE(?, preferedpaymentmethodid) \n" +
            "    WHERE bookingId = ?;";

    private static final String INSERT_BOOKING = "INSERT INTO `web`.`booking` (`checkInDate`, `checkOutDate`, " +
            "`adultsCount`, `childrenCount`, `comment`, `bookingStatusId`, `roomTypeId`, `GuestId`, `rateTypeId`, " +
            "`guestTypeId`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_PREFERRED_PAYMENT_METHOD = "INSERT INTO `web`.`preferedpaymentmethod` " +
            "(`name`, `cardholderName`, `expirationDate`, `cardNumber`, `csvCode`, `paymentMethodId`) " +
            "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String INSERT_ROOM_AVAILABILITY = "INSERT INTO `web`.`roomavailability` (`date`, `roomId`) " +
            "VALUES (?, ?);";

    private static final String SELECT_BOOKINGS =
            "SELECT b.checkInDate, b.checkOutDate, b.adultsCount, b.roomTypeId, rt.name roomTypeName, b" +
                    ".bookingStatusId, bs.name bookingStatusName, \n" +
                    "b.bookingId, b.childrenCount, b.comment, bs.name bookingStatusName, g.guestId, g.name guestName," +
                    " g.mobile, g.email, g.address, \n" +
                    "r.roomId, r.name roomName, r.roomStatusId, pm.PreferedpaymentMethodId, pm.name " +
                    "paymentMethodName, pi.PaymentInfoId, pi.sum, pi.transactionId, \n" +
                    "pi.transactionDate, pi.transactionStatus, g.userId, b.price, rat.rateTypeId, rat.name, pm" +
                    ".cardholderName, pm.expirationDate, pm.cardNumber, pm.csvCode, pm.paymentMethodId, b.guestTypeId \n" +
                    "FROM booking b \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId \n" +
                    "LEFT OUTER JOIN bookingStatus bs ON bs.bookingStatusId = b.bookingStatusId \n" +
                    "LEFT OUTER JOIN guest g ON g.GuestId = b.GuestId \n" +
                    "LEFT OUTER JOIN room r ON r.roomId = b.roomId \n" +
                    "LEFT OUTER JOIN PreferedpaymentMethod pm ON pm.PreferedpaymentMethodId = b" +
                    ".PreferedpaymentMethodId \n" +
                    "LEFT OUTER JOIN ratetype rat ON rat.rateTypeId = b.rateTypeId \n" +
                    "LEFT OUTER JOIN paymentinfo pi ON pi.PaymentInfoId= b.PaymentInfoId \n";

    private static final String SELECT_BOOKING_BY_BOOKING_ID =
            "SELECT b.checkInDate, b.checkOutDate, b.roomId, b.price, b.PreferedpaymentMethodId " +
                    "FROM booking b \n" +
                    "WHERE b.bookingId = ?;";


    @Override
    public List<Booking> getBookings() throws DAOException {

        List<Booking> bookings = null;

        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOKINGS)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        bookings = new ArrayList<Booking>();
                        Booking booking;
                        while (rs.next()) {
                            booking = new Booking();
                            booking.setCheckInDate(rs.getDate(1).toLocalDate());
                            booking.setCheckOutDate(rs.getDate(2).toLocalDate());
                            booking.setAdultsCount(rs.getInt(3));
                            booking.setChildrenCount(rs.getInt(9));
                            booking.setComment(rs.getString(10));
                            booking.setPrice(rs.getInt(28));
                            booking.setBookingId(rs.getInt(8));

                            Room room = new Room();
                            room.setRoomId(rs.getInt(17));
                            room.setName(rs.getString(18));
                            RoomStatus roomStatus = new RoomStatus();
                            roomStatus.setRoomStatusId(rs.getInt(19));
                            RoomType roomType = new RoomType();
                            roomType.setRoomTypeId(rs.getInt(4));
                            roomType.setName(rs.getString(5));
                            room.setRoomStatus(roomStatus);
                            room.setRoomType(roomType);
                            booking.setOfferedRoom(room);

                            RateType rateType = new RateType();
                            rateType.setRateTypeId(rs.getInt(29));
                            rateType.setRateName(rs.getString(30));
                            booking.setRateType(rateType);

                            roomType = new RoomType();
                            roomType.setRoomTypeId(rs.getInt(4));
                            roomType.setName(rs.getString(5));
                            booking.setRoomType(roomType);

                            Guest guest = new Guest();
                            guest.setAddress(rs.getString(16));
                            guest.setEmail(rs.getString(15));
                            guest.setMobile(rs.getString(14));
                            guest.setGuestId(rs.getInt(12));
                            guest.setName(rs.getString(13));
                            guest.setUserId(rs.getInt(27));
                            booking.setGuest(guest);

                            BookingStatus bookingStatus = new BookingStatus();
                            bookingStatus.setBookingStatusId(rs.getInt(6));
                            bookingStatus.setName(rs.getString(7));
                            booking.setBookingStatus(bookingStatus);

                            GuestType guestType = new GuestType();
                            guestType.setGuestTypeId(rs.getInt(36));
                            booking.setGuestType(guestType);
/*
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setPaymentInfoId(rs.getInt(22));
                Date transactionDate = rs.getDate(25);
                if (transactionDate != null) {
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
*/

                            PreferedPaymentMethod preferedPaymentMethod = new PreferedPaymentMethod();
                            preferedPaymentMethod.setPreferedPaymentMethodId(rs.getInt(20));
                            preferedPaymentMethod.setCardholderName(rs.getString(31));
                            preferedPaymentMethod.setCardNumber(rs.getInt(33));
                            preferedPaymentMethod.setCsvCode(rs.getInt(34));
                            preferedPaymentMethod.setExpirationDate(rs.getString(32));
                            preferedPaymentMethod.setPaymentMethodId(rs.getInt(35));
                            booking.setPreferedPaymentMethod(preferedPaymentMethod);

                            bookings.add(booking);
                        }

                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error. Impossible to load bookings: " + e);
        }
        return bookings;
    }

    @Override
    public void delete(int bookingid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOKING)) {
                statement.setInt(1, bookingid);
                statement.executeUpdate();
                statement.closeOnCompletion();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when deleting a booking: " + e);
        }
    }

    @Override
    public void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment,
                       int roomTypeId, int guestId, int rateTypeId, int typeId, int guestTypeId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_BOOKING)) {
                statement.setDate(1, Date.valueOf(checkInDate));
                statement.setDate(2, Date.valueOf(checkOutDate));
                statement.setInt(3, adultsCount);
                statement.setInt(4, childrenCount);
                statement.setString(5, comment);
                statement.setInt(6, 1);
                statement.setInt(7, roomTypeId);
                statement.setInt(8, guestId);
                statement.setInt(9, rateTypeId);
                statement.setInt(10, guestTypeId);


                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when creating a booking: " + e);
        }
    }

    @Override
    public void update(int bookingid, Integer roomId, int newStatus, Integer price, String cardholderName,
                       String cardNumber, String ccvCode, String expirationDate, int paymentMethodId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            int newId = -1;
            if (cardholderName != null) {
                try (PreparedStatement statement = connection.prepareStatement(INSERT_PREFERRED_PAYMENT_METHOD,
                        Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, "");
                    statement.setString(2, cardholderName);
                    statement.setString(3, expirationDate);
                    statement.setString(4, cardNumber);
                    statement.setString(5, ccvCode);
                    statement.setInt(6, paymentMethodId);
                    statement.executeUpdate();
                    try (ResultSet rs = statement.getGeneratedKeys()) {
                        if (rs.next()) {
                            newId = rs.getInt(1);
                        }
                    }
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(BookingDaoQueries.UPDATE_BOOKING)) {
                statement.setInt(1, newStatus);
                if (roomId != null) {
                    statement.setInt(2, roomId);
                    statement.setInt(3, price);
                } else {
                    statement.setNull(2, java.sql.Types.INTEGER);
                    statement.setNull(3, java.sql.Types.INTEGER);
                }
                if (newId != -1) {
                    statement.setInt(4, newId);
                } else {
                    statement.setNull(4, java.sql.Types.INTEGER);
                }
                statement.setInt(5, bookingid);
                statement.executeUpdate();

                if (newStatus == 2) {
                    updateRoomAvailability(bookingid);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when updating a booking: " + e);
        }
    }

    public void updateRoomAvailability(int bookingid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            LocalDate checkin = null;
            LocalDate checkout = null;
            int roomid = -1;
            int dateDifInDays;

            try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOKING_BY_BOOKING_ID)) {
                statement.setInt(1, bookingid);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        checkin = rs.getDate(1).toLocalDate();
                        checkout = rs.getDate(2).toLocalDate();
                        roomid = rs.getInt(3);
                    }
                    Period period = Period.between(checkin, checkout);
                    dateDifInDays = period.getDays();
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(INSERT_ROOM_AVAILABILITY)) {
                for (int i = dateDifInDays; i >= 0; i--) {
                    statement.setDate(1, Date.valueOf(checkin.plusDays(i)));
                    statement.setInt(2, roomid);
                    statement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when updating a room availability: " + e);
        }
    }

}
