package com.ssgassignment.productinfoapi.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
public class Item extends AbstractDataTraceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int itemPrice;
    @Column(nullable = false)
    private LocalDateTime itemDisplayStartDate;
    @Column(nullable = false)
    private LocalDateTime itemDisplayEndDate;

    private Item(String itemName, int itemPrice, LocalDateTime itemDisplayStartDate,
                LocalDateTime itemDisplayEndDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDisplayStartDate = itemDisplayStartDate;
        this.itemDisplayEndDate = itemDisplayEndDate;
    }

    public static Item newInstance(String itemName, int itemPrice, LocalDateTime itemDisplayStartDate,
                                   LocalDateTime itemDisplayEndDate){
        return new Item(itemName, itemPrice, itemDisplayStartDate, itemDisplayEndDate);
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
