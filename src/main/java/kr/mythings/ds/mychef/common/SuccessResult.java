package kr.mythings.ds.mychef.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResult {
    private boolean success;
    private String msg;

}
