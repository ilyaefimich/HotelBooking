
package com.epam.jwd.finaltask.command;

import com.epam.jwd.finaltask.command.impl.*;

public enum CommandFactory {
    LOGIN {
        public AbstractCommand createCommand() {
            return new LoginCommand();
        }
    },
    LOGOUT {
        public AbstractCommand createCommand() {
            return new LogoutCommand();
        }
    },
    REGISTRATION {
        public AbstractCommand createCommand() {
            return new RegistrationCommand();
        }
    },
    BOOKINGS {
        public AbstractCommand createCommand() {
            return new GetBookingsCommand();
        }
    },
    BOOKINGDETAILS {
        public AbstractCommand createCommand() {
            return new GetBookingDetailsCommand();
        }
    },
    CHANGELOCALE {
        public AbstractCommand createCommand() {
            return new ChangeLocaleCommand();
        }
    },
    GETUSERS {
        public AbstractCommand createCommand() {
            return new GetUsersCommand();
        }
    },
    UPDATEUSER {
        public AbstractCommand createCommand() {
            return new UpdateUserCommand();
        }
    },
    DELETEUSER {
        public AbstractCommand createCommand() {
            return new DeleteUserCommand();
        }
    },
    GETUSERDETAILS {
        public AbstractCommand createCommand() {
            return new GetUserDetailsCommand();
        }
    },
    DELETEBOOKING {
        public AbstractCommand createCommand() {
            return new DeleteBookingCommand();
        }
    },
    GETROOMS {
        public AbstractCommand createCommand() { return new GetRoomsCommand(); }
    },
    GETAVAILABLEROOM {
        public AbstractCommand createCommand(){ return new GetAvailableRoomCommand(); }
        },
    UPDATEBOOKING {
        public AbstractCommand createCommand() { return new UpdateBookingCommand(); }
    };

    public abstract AbstractCommand createCommand();

}
