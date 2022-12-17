package kr.mythings.ds.mychef.form;

import lombok.Data;

@Data
public class CustomerRatingDTO {

    private Long id;

    private Long servingId;

    private Long customerId;

    private String customerName;

    private String rating;


    public CustomerRatingDTO() {

    }

    public CustomerRatingDTO(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public CustomerRatingDTO(Long id, Long servingId, Long customerId, String customerName, String rating) {
        this.id = id;
        this.servingId = servingId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.rating = rating;
    }
}
