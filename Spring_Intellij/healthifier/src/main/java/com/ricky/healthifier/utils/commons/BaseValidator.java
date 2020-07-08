package com.ricky.healthifier.utils.commons;

import com.ricky.healthifier.utils.exception.AppException;

public class BaseValidator {

    /**
     * If object is null, throw exception
     */
    public static void checkObjectIsNotNull(Object object, String msg) throws AppException {
        if (object == null)
            throw new AppException(msg);
    }

    /**
     * If object is not null, throw exception
     */
    public static void checkObjectIsNull(Object object, String msg) throws AppException {
        if (object != null)
            throw new AppException(msg);
    }
}
