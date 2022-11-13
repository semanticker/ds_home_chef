package kr.mythings.ds.mychef.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DMC_CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="customer_id")
    private Long id;

    private String name;

    private LocalDateTime birthDate;

    boolean active = false;

    public Customer(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
    }


}
