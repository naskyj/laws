package com.tnd.laws.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocalResponse<T> {
    private String responseCode;
    private String responseMessage;
    private T data;

    public LocalResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
