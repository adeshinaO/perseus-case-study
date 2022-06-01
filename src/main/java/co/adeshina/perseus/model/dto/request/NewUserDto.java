package co.adeshina.perseus.model.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class NewUserDto {
    private String firstName;
    private String lastName;
    private List<NewEmailDto> emails;
    private List<NewPhoneNumberDto> phoneNumbers;
}
