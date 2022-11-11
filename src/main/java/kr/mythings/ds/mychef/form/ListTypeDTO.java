package kr.mythings.ds.mychef.form;

import lombok.Data;

@Data
public class ListTypeDTO {

    String code;

    String text;

    public ListTypeDTO(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
