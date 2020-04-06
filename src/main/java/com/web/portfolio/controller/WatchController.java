package com.web.portfolio.controller;

import com.web.portfolio.entity.Watch;
import com.web.portfolio.service.PortfolioService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/watch")
public class WatchController {

    @Autowired
    private PortfolioService service;

    @GetMapping(value = {"/", "/query"})
    public Iterable<Watch> query() {
        return service.getWatchRepository().findAll();
    }

    @PostMapping(value = {"/", "/add"})
    @Transactional
    public Watch add(@RequestBody Map<String, String> map) {

        Watch watch = new Watch();
        watch.setName(map.get("name"));

        service.getWatchRepository().save(watch);

        return watch;
    }

    @Transactional
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    public Boolean delete(@PathVariable("id") Long id) {
        service.getWatchRepository().delete(id);
        return true;
    }

    @Transactional
    @GetMapping(value = {"/{id}", "/get/{id}"})
    public Watch get(@PathVariable("id") Long id) {
        Watch watch = service.getWatchRepository().findOne(id);

        return watch;
    }

    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Watch watch = get(id);
        if (watch == null) {
            return false;
        }
        watch.setName(map.get("name"));

        service.getWatchRepository().update(id, watch.getName());
        return true;
    }

}
