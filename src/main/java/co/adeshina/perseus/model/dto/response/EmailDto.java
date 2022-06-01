package co.adeshina.perseus.model.dto.response;

import lombok.Data;

@Data
public class EmailDto {
    private long id;
    private long userId;
    private String email;
}
