package com.ssgassignment.productinfoapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@SequenceGenerator(
        name = "PROMOTION_SEQ_GENERATOR",
        sequenceName = "PROMOTION_SEQ",
        initialValue = 1, allocationSize = 20)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promotion extends AbstractDataTraceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROMOTION_SEQ_GENERATOR")
    @Column(name = "promotion_id")
    private Long promotionId;

    @Column(nullable = false)
    private String promotionName;

    private Integer discountAccount;
    private Double discountRate;

    @Column(nullable = false)
    private LocalDateTime promotionStartDate;
    @Column(nullable = false)
    private LocalDateTime promotionEndDate;

    private Promotion(String promotionName, int discountAccount, double discountRate,
                     LocalDateTime promotionStartDate, LocalDateTime promotionEndDate) {
        this.promotionName = promotionName;
        this.discountAccount = discountAccount;
        this.discountRate = discountRate;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }

    public static Promotion newInstance(String promotionName, int discountAccount, double discountRate,
                                        LocalDateTime promotionStartDate, LocalDateTime promotionEndDate){
        return new Promotion(promotionName, discountAccount, discountRate,
                promotionStartDate, promotionEndDate);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Promotion))
            return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(promotionId, promotion.promotionId) &&
                Objects.equals(promotionName, promotion.promotionName) &&
                Objects.equals(discountAccount, promotion.discountAccount) &&
                Objects.equals(discountRate, promotion.discountRate);
    }

    @Override
    public int hashCode() {
        int result = promotionId == null ? 0 : promotionId.hashCode();
        result = 31 * result + promotionName.hashCode();
        result = 31 * result + (discountAccount == null ? 0 : discountAccount.hashCode());
        result = 31 * result + (discountRate == null ? 0 : discountRate.hashCode());
        return result;
    }
}
