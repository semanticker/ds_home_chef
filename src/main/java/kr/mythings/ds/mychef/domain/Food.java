package kr.mythings.ds.mychef.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="DMC_FOOD")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue
    @Column(name="food_id")
    private Long id;

    private String name;
}
