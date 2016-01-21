package com.yart.common.bean;

import com.yart.user.bean.User;

/**
 * Wrapper class for holding user bean and various workflow statuses
 * 
 * @author bilinbs
 *
 */
public class UserWrapper {

    private User user;

    private STATUS_CODES statusCode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public STATUS_CODES getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(STATUS_CODES statusCode) {
        this.statusCode = statusCode;
    }

    public enum STATUS_CODES {
        CREATE_OK, MODIFY_OK, USERID_EXISTS, EMAIL_EXISTS, VALID_CREDENTIALS, 
        INVALID_PASSWORD, USER_INACTIVE, OPERATION_FAILED
    }

}
