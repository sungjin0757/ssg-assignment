package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PromotionRepository extends CrudRepository<Promotion,Long> {
    @Query("select p from Promotion p where (:startDate between p.promotionStartDate and p.promotionEndDate) " +
            "or (:endDate between p.promotionStartDate and p.promotionEndDate) " +
            "or (p.promotionStartDate between :startDate and :endDate) " +
            "or (p.promotionEndDate between :startDate and :endDate)")
    List<Promotion> findEnablePromotions(@Param("startDate")LocalDateTime startDate,
                                         @Param("endDate")LocalDateTime endDate);
}
