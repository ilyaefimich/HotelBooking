
package com.epam.jwd.finaltask.command;

import com.epam.jwd.finaltask.command.impl.*;

/**
 * CommandFactory returns instance of a specific command implementation based on the enumeration list.
 */
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
    GETBOOKINGS {
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
        public AbstractCommand createCommand() {
            return new GetRoomsCommand();
        }
    },
    DELETEROOM {
        public AbstractCommand createCommand() {
            return new DeleteRoomCommand();
        }

    },
    UPDATEROOM {
        public AbstractCommand createCommand() {
            return new UpdateRoomCommand();
        }

    },
    GETROOMDETAILS {
        public AbstractCommand createCommand() {
            return new GetRoomDetailsCommand();
        }
    },
    SIGNUP {
        public AbstractCommand createCommand() {
            return new SignupCommand();
        }
    },
    GETPROFILE {
        public AbstractCommand createCommand() {
            return new GetProfileCommand();
        }
    },
    UPDATEPROFILE {
        public AbstractCommand createCommand() {
            return new UpdateProfileCommand();
        }
    },
    UPDATEBOOKING {
        public AbstractCommand createCommand() {
            return new UpdateBookingCommand();
        }
    },
    CALCULATEROOMPRIORITY {
        public AbstractCommand createCommand() {
            return new CalculateRoomPriorityCommand();
        }
    };

    public abstract AbstractCommand createCommand();

}
