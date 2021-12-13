package com.sergeev.visitcard.service.loggerService;

import com.sergeev.visitcard.data.logger.Log;
import com.sergeev.visitcard.repository.logger.LogRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServ {

    LogRep logRep;

    @Autowired
    public LoggerServ(LogRep logRep) {
        this.logRep = logRep;
    }

    public void saveLog(String cookie, String logStr) {
        Log log = new Log();
        log.setLog(logStr);
        log.setCookie(cookie);
        logRep.save(log);
    }

    public List<Log> getLogs(String cookie) {
        return logRep.getAllByCookie(cookie);
    }
}
