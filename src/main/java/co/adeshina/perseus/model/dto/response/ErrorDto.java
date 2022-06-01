package co.adeshina.perseus.model.dto.response;

import lombok.Data;

@Data
public class ErrorDto {
    private String message;
    private int code;
}
