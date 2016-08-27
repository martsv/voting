package ru.martsv.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.martsv.voting.model.Menu;
import ru.martsv.voting.service.MenuService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * mart
 * 23.08.2016
 */
@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = "/rest/restaurants/{restaurant_id}/menu";
    private static final Logger LOG = LoggerFactory.getLogger(MenuRestController.class);

    @Autowired
    private MenuService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Menu get(@PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        LOG.info("get menu {} for restaurant {}", id, restaurant_id);
        return service.get(id, restaurant_id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        LOG.info("delete menu {} for restaurant {}", id, restaurant_id);
        service.delete(id, restaurant_id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Menu> getAll(@PathVariable("restaurant_id") int restaurant_id) {
        LOG.info("getAll for restaurant {}", restaurant_id);
        return service.getAll(restaurant_id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Menu menu, @PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        menu.setId(id);
        LOG.info("update {} for restaurant {}", menu, restaurant_id);
        service.update(menu, restaurant_id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody Menu menu, @PathVariable("restaurant_id") int restaurant_id) {
        menu.setId(null);
        LOG.info("create {} for restaurant {}", menu, restaurant_id);
        Menu created = service.save(menu, restaurant_id);

        int id = created.getId();
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant_id, id).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/ondate", method = RequestMethod.GET)
    public List<Menu> getOnDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable("restaurant_id") int restaurant_id) {
        LOG.info("getOnDate date {} for restaurant {}", date, restaurant_id);
        return service.getOnDate(date, restaurant_id);
    }

    @RequestMapping(value = "/ondate", method = RequestMethod.DELETE)
    public void deleteOnDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable("restaurant_id") int restaurant_id) {
        LOG.info("deleteOnDate date {} for restaurant {}", date, restaurant_id);
        service.deleteOnDate(date, restaurant_id);
    }

}
