package com.ricky.healthifier.service.init;

import com.ricky.healthifier.utils.exception.AppException;

public interface InitService {

    boolean initializeDBs() throws AppException;

    boolean deleteDBsAtExit();
}
