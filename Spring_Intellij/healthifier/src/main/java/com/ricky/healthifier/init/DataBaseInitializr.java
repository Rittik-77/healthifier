package com.ricky.healthifier.init;

import com.ricky.healthifier.service.init.InitService;
import com.ricky.healthifier.utils.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataBaseInitializr {

    @Autowired
    private InitService initService;

    @PostConstruct
    public void feedValuesToDB() throws AppException {
        boolean response = initService.initializeDBs();

        if(!response) {
            throw new AppException("Error initializing Food DB");
        }
    }

    @PreDestroy
    public void deleteValuesFromDB() throws AppException {
        boolean response = initService.deleteDBsAtExit();

        if(!response) {
            throw new AppException("Error deleting Food DB");
        }
    }
}
