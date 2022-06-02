package co.adeshina.perseus.controller;

import co.adeshina.perseus.model.dto.request.NewEmailDto;
import co.adeshina.perseus.model.dto.request.NewPhoneNumberDto;
import co.adeshina.perseus.model.dto.request.NewUserDto;
import co.adeshina.perseus.model.dto.response.EmailDto;
import co.adeshina.perseus.model.dto.response.PhoneNumberDto;
import co.adeshina.perseus.model.dto.response.UserDto;
import co.adeshina.perseus.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody NewUserDto createUserDto) {
        UserDto userDto = userService.createUser(createUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<UserDto>> findUserByName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        List<UserDto> users = userService.findByName(firstName, lastName);
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{user_id}/add/phone")
    public ResponseEntity<List<PhoneNumberDto>> addPhoneNumber(
            @RequestBody NewPhoneNumberDto createPhoneNumberDto,
            @PathVariable("user_id") long userId) {
        List<PhoneNumberDto> userPhoneNumbers = userService.addPhoneNumber(userId, createPhoneNumberDto);
        return ResponseEntity.ok(userPhoneNumbers);
    }

    @PatchMapping("/{user_id}/add/email")
    public ResponseEntity<List<EmailDto>> addEmail(
            @RequestBody NewEmailDto createEmailDto,
            @PathVariable("user_id") long userId) {
        List<EmailDto> userEmails = userService.addEmail(userId, createEmailDto);
        return ResponseEntity.ok(userEmails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
