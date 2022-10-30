package com.inventory.data.model.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {

    private Boolean isSuccess;

    private String message;
}
