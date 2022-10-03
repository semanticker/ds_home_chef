package kr.mythings.ds.mychef.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DMC_FOOD")
@Getter
@Setter
@NoArgsConstructor
public class Food extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="food_id")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private List<Recipe> recipes = new ArrayList<>();

    public Food(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
        //this.setModifyBy("hyojong-update");
        //this.setModifyDate(LocalDateTime.now().plusHours(2));
    }
}
