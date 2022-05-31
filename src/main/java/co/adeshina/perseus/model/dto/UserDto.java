package co.adeshina.perseus.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;



    // todo: DO I need a seperate dto for request bodies?? CreateUserDto

}
