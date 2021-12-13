package com.sergeev.visitcard.repository.logger;

import com.sergeev.visitcard.data.logger.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRep extends JpaRepository<Log, Long> {
    List<Log> getAllByCookie(String cookie);
}
