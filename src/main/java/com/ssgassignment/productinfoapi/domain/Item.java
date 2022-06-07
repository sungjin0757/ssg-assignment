package com.ssgassignment.productinfoapi.domain;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Item extends AbstractDataTraceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int itemPrice;
    @Column(nullable = false)
    private LocalDateTime itemDisplayStartDate;
    @Column(nullable = false)
    private LocalDateTime itemDisplayEndDate;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType itemType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PromotionItem> promotionItems = new ArrayList<>();

    private Item(String itemName, int itemPrice, UserType itemType, LocalDateTime itemDisplayStartDate,
                LocalDateTime itemDisplayEndDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemDisplayStartDate = itemDisplayStartDate;
        this.itemDisplayEndDate = itemDisplayEndDate;
    }

    public static Item newInstance(String itemName, int itemPrice, UserType itemType,
                                   LocalDateTime itemDisplayStartDate,
                                   LocalDateTime itemDisplayEndDate){
        return new Item(itemName, itemPrice, itemType, itemDisplayStartDate, itemDisplayEndDate);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Item))
            return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(itemName, item.itemName)
                && Objects.equals(itemPrice, item.itemPrice);
    }

    @Override
    public int hashCode() {
        int result = itemId == null ? 0 : itemId.hashCode();
        result = 31 * result + itemName.hashCode();
        result = 31 * result + Integer.hashCode(itemPrice);
        return result;
    }
}
