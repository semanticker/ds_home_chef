package kr.mythings.ds.mychef.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DMC_SERVING")
@Setter
@Getter
@NoArgsConstructor
public class Serving extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="serving_id")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="serving_id")
    private List<CustomerRating> customerRatingList = new java.util.ArrayList<>();

    private LocalDateTime servingDate;

    public List<CustomerRating> getCustomerRatingList() {
        return customerRatingList;
    }

    public void setCustomerRatingList(List<CustomerRating> customerRatingList) {
        this.customerRatingList = customerRatingList;
    }

}
