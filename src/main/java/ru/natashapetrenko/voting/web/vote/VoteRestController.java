package ru.natashapetrenko.voting.web.vote;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.natashapetrenko.voting.model.Dish;
import ru.natashapetrenko.voting.model.Vote;
import ru.natashapetrenko.voting.to.DishWithExceed;
import ru.natashapetrenko.voting.to.VoteTO;
import ru.natashapetrenko.voting.util.exception.VoteCantBeChangedException;
import ru.natashapetrenko.voting.web.dish.AbstractDishController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/votes";

    @Override
    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<VoteTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id) {
        super.update(vote, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        Vote created = super.create(vote);

        if (created == null) {
            throw new VoteCantBeChangedException();
        }

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