package com.epam.jwd.finaltask.model;

import java.time.LocalDate;
import java.util.List;

public class Booking {
    private int bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adultsCount;
    private int childrenCount;
    private String comment;
    private BookingStatus bookingStatus;
    private RoomType roomType;
    private Guest guest;
    private Room offeredRoom;
    private List<Message> messages;
    private PreferedPaymentMethod preferedPaymentMethod;
    private PaymentInfo paymentInfo;
    private int price;
    private RateType rateType;

    public  RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getOfferedRoom() {
        return offeredRoom;
    }

    public void setOfferedRoom(Room offeredRoom) {
        this.offeredRoom = offeredRoom;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public PreferedPaymentMethod getPreferedPaymentMethod() {
        return preferedPaymentMethod;
    }

    public void setPreferedPaymentMethod(PreferedPaymentMethod preferedPaymentMethod) {
        this.preferedPaymentMethod = preferedPaymentMethod;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @Override
    public String toString() {
        return checkInDate.toString() + "-" + checkOutDate.toString() + "-" + adultsCount + "Adult" + "-" + roomType.getName()+ "-" + bookingStatus.getName();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(int adultsCount) {
        this.adultsCount = adultsCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }




}
