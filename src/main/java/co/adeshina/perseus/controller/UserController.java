package co.adeshina.perseus.controller;

import co.adeshina.perseus.model.dto.UserDto;
import co.adeshina.perseus.model.entity.Email;
import java.util.Optional;
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

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(UserDto userDto) {

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Optional<UserDto>> findUserByName(@RequestParam("name") String name) {

    }

    @PatchMapping("/{user_id}/add-phone-number")
    public ResponseEntity<UserDto> addPhoneNumber(@RequestBody String body, @PathVariable("user_id") long userId) {

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        Email email;

    }

    @PatchMapping("/{user_id}/add-email")
    public ResponseEntity<UserDto> addEmail(@RequestBody String body, @PathVariable("user_id") long userId) {

    }
}
