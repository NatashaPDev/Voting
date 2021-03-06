package ru.natashapetrenko.voting.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.natashapetrenko.voting.AuthorizedUser;
import ru.natashapetrenko.voting.model.User;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }
}