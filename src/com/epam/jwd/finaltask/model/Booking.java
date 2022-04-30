package com.epam.jwd.finaltask.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private GuestType guestType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId &&
                adultsCount == booking.adultsCount &&
                childrenCount == booking.childrenCount &&
                price == booking.price &&
                Objects.equals(checkInDate, booking.checkInDate) &&
                Objects.equals(checkOutDate, booking.checkOutDate) &&
                Objects.equals(comment, booking.comment) &&
                Objects.equals(bookingStatus, booking.bookingStatus) &&
                Objects.equals(roomType, booking.roomType) &&
                Objects.equals(guest, booking.guest) &&
                Objects.equals(offeredRoom, booking.offeredRoom) &&
                Objects.equals(messages, booking.messages) &&
                Objects.equals(preferedPaymentMethod, booking.preferedPaymentMethod) &&
                Objects.equals(paymentInfo, booking.paymentInfo) &&
                Objects.equals(rateType, booking.rateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, checkInDate, checkOutDate, adultsCount, childrenCount, comment, bookingStatus,
                roomType, guest, offeredRoom, messages, preferedPaymentMethod, paymentInfo, price, rateType, guestType);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", adultsCount=" + adultsCount +
                ", childrenCount=" + childrenCount +
                ", comment='" + comment + '\'' +
                ", bookingStatus=" + bookingStatus +
                ", roomType=" + roomType +
                ", guest=" + guest +
                ", offeredRoom=" + offeredRoom +
                ", messages=" + messages +
                ", preferedPaymentMethod=" + preferedPaymentMethod +
                ", paymentInfo=" + paymentInfo +
                ", price=" + price +
                ", rateType=" + rateType +
                ", guestTypeId=" + guestType +
                '}';
    }

    public GuestType getGuestType() {
        return guestType;
    }

    public void setGuestType(GuestType guestType) {
        this.guestType = guestType;
    }

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
