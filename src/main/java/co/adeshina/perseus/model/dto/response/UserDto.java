package co.adeshina.perseus.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private List<EmailDto> emails;
    private List<PhoneNumberDto> phoneNumbers;
}
