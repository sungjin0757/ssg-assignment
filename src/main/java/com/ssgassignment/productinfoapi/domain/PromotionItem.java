package com.ssgassignment.productinfoapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@SequenceGenerator(
        name = "PROMOTION_ITEM_SEQ_GENERATOR",
        sequenceName = "PROMOTION_ITEM_SEQ",
        initialValue = 1, allocationSize = 20)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionItem extends AbstractDataTraceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROMOTION_ITEM_SEQ_GENERATOR")
    @Column(name = "promotion_item_id")
    private Long promotionItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    private PromotionItem(Item item, Promotion promotion) {
        this.item = item;
        this.promotion = promotion;
    }

    public static PromotionItem newInstance(Item item, Promotion promotion){
        return new PromotionItem(item, promotion);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof PromotionItem))
            return false;
        PromotionItem pi = (PromotionItem) o;
        return Objects.equals(promotionItemId, pi.getPromotionItemId());
    }

    @Override
    public int hashCode() {
        return 31 * promotionItemId.hashCode();
    }
}
