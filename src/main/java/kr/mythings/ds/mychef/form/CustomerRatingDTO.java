package kr.mythings.ds.mychef.form;

import lombok.Data;

@Data
public class CustomerRatingDTO {

    private Long id;

    private Long servingId;

    private Long customerId;

    private String customerName;

    private Long rating;


    public CustomerRatingDTO() {

    }

    public CustomerRatingDTO(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }
}
