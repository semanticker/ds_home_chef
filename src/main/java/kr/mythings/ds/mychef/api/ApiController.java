package kr.mythings.ds.mychef.api;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiController {
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }
}
