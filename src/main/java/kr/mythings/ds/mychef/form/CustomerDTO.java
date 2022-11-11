package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;

    private String name;

    public CustomerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
