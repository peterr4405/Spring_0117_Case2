package com.web.portfolio.controller;

import com.web.portfolio.entity.Classify;
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
@RequestMapping("/portfolio/classify")
public class ClassifyController {

    @Autowired
    private PortfolioService service;

    @GetMapping(value = {"/", "/query"})
    public Iterable<Classify> query() {
        return service.getClassifyRepository().findAll();
    }

    @PostMapping(value = {"/", "/add"})
    @Transactional
    public Classify add(@RequestBody Map<String, String> map) {

        Classify classify = new Classify();
        classify.setName(map.get("name"));
        if (map.get("transaction") == null) {
            classify.setTransaction(false);
        } else {
            classify.setTransaction(true);
        }

        service.getClassifyRepository().save(classify);

        return classify;
    }

    @Transactional
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    public Boolean delete(@PathVariable("id") Long id) {
        service.getClassifyRepository().delete(id);
        return true;
    }

    @Transactional
    @GetMapping(value = {"/{id}", "/get/{id}"})
    public Classify get(@PathVariable("id") Long id) {
        Classify classify = service.getClassifyRepository().findOne(id);
        if (classify != null && classify.gettStocks() != null) {
            classify.gettStocks().size();
        }
        return classify;
    }
    
    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Classify classify = get(id);
        if (classify == null) {
            return false;
        }
        classify.setName(map.get("name"));
        
 if (map.get("transaction") == null) {
            classify.setTransaction(false);
        } else {
            classify.setTransaction(true);
        }
        service.getClassifyRepository().update(id, classify.getName(), classify.getTransaction());
        return true;
    }
     
}
