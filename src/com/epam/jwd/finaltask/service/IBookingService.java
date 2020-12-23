package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.PaymentInfo;
import com.epam.jwd.finaltask.model.PaymentMethod;

import java.util.List;

public interface IBookingService {
    List<Booking> getBookings();

}
