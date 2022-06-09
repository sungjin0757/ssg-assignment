package com.ssgassignment.productinfoapi.service.staticvals;

import com.ssgassignment.productinfoapi.exception.NotValidTimeException;

import java.time.LocalDateTime;

public abstract class CheckTime {
    public static void checkDateTime(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate.isAfter(endDate)){
            throw new NotValidTimeException("시작 날짜는 종료 날짜보다 적어야합니다.");
        }
    }
}
