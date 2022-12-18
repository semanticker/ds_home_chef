package kr.mythings.ds.mychef.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DMC_CUSTOMER_RATING")
@Getter
@Setter
public class CustomerRating extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "serving_id")
    private Serving serving;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Long rating;

}