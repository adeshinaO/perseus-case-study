package co.adeshina.perseus.model.dto.response;

import lombok.Data;

@Data
public class PhoneNumberDto {
    private long id;
    private String phoneNumber;
    private long userId;
}
