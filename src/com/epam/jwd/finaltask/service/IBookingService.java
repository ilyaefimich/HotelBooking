package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.Booking;

import java.util.List;

/**
 * Provides methods for application to manage bookings.
 */
public interface IBookingService {
    List<Booking> getAllBookings();
    List<Booking> getBookingsByGuestId(int guestId);
    Booking getBookingById(int bookingId);
    void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId, int rateTypeId, int guestTypeId);
    void update(int bookingid, Integer roomId, int newStatus, Integer price, String cardholderName, String cardNumber, String ccvCode, String expirationDate, int paymentMethodId);
    void delete(int bookingid);


}
