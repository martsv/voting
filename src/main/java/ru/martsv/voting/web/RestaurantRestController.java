package ru.martsv.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.martsv.voting.model.Restaurant;
import ru.martsv.voting.service.RestaurantService;
import ru.martsv.voting.util.exception.NotAcceptableException;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 21.08.2016
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurants";
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantRestController.class);

    @Autowired
    private RestaurantService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Restaurant get(@PathVariable("id") int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        LOG.info("update " + restaurant);
        service.update(restaurant);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        restaurant.setId(null);
        LOG.info("create " + restaurant);
        Restaurant created = service.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/{id}/votes", method = RequestMethod.GET)
    public long getVotesOnDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable("id") int id) {
        LOG.info("getVotes on date {} for restaurant {}", date, id);
        return service.getVotesOnDate(id, date);
    }

    @RequestMapping(value = "/winners", method = RequestMethod.GET)
    public List<Restaurant> getWinnersOnDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getWinners on date {}", date);
        return service.getWinnersOnDate(date);
    }

    @RequestMapping(value = "/{id}/vote", method = RequestMethod.POST)
    public void addVote(@PathVariable("id") int id) {
        LOG.info("addVote for restaurant {}", id);
        service.addVote(id);
    }
}
