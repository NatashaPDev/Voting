package ru.natashapetrenko.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.natashapetrenko.voting.AuthorizedUser;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.service.VoteService;
import ru.natashapetrenko.voting.to.VoteTo;
import ru.natashapetrenko.voting.util.VotesUtil;
import ru.natashapetrenko.voting.util.exception.VoteCantBeChangedException;

import java.net.URI;
import java.util.List;

import static ru.natashapetrenko.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController{
    static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<VoteTo> getAll() {
        return VotesUtil.getAll(service.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {

        int userId = AuthorizedUser.id();
        checkNew(vote);
        Vote created = service.create(vote, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ExceptionHandler(VoteCantBeChangedException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public String handleResourceNotFoundException(VoteCantBeChangedException ex)
    {
        return ex.getMessage();
    }

}