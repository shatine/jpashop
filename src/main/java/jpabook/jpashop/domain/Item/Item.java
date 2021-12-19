package jpabook.jpashop.domain.Item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQty;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//

    /**
     * 재고증가
     * @param qty
     */
    public void addStock(int qty) {
        this.stockQty += qty;
    }

    /**
     * 재고감소
     */
    public void reduceStock(int qty) {
        int restStock = this.stockQty - qty;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQty = restStock;
    }
}
