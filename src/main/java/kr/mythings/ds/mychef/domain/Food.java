package kr.mythings.ds.mychef.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public Food(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
        //this.setModifyBy("hyojong-update");
        //this.setModifyDate(LocalDateTime.now().plusHours(2));
    }
}
