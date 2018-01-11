package com.codecool.bookstore.logger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getClass());
    }
}
