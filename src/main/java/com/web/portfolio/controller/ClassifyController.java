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
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public Classify get(@PathVariable("id") Long id) {
        Classify classify = service.getClassifyRepository().findOne(id);
        //當 FetchType.LAZY 配置時，讓 classify 查找 tStocks
        if (classify != null && classify.gettStocks() != null) {
            classify.gettStocks().size();
        }
        return classify;
    }

    @PostMapping(value = {"/", "/add"})
    @Transactional
    public Classify add(@RequestBody Map<String, String> map) {
        Classify classify = new Classify();
        classify.setName(map.get("name"));
        System.out.println("map: " + map);
        if (map.get("transaction") == null) {
            classify.setTransaction(false);
        } else {
            classify.setTransaction(true);
        }
        service.getClassifyRepository().save(classify);
        return classify;
    }

    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Classify o_Classify = get(id);
        if (o_Classify == null) {
            return false;
        }
        o_Classify.setName(map.get("name"));
        if (map.get("transaction") == null) {
            o_Classify.setTransaction(false);
        } else {
            o_Classify.setTransaction(true);
        }
        service.getClassifyRepository().update(id, o_Classify.getName(), o_Classify.getTransaction());
        return true;
    }

    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Long id) {
        service.getClassifyRepository().delete(id);
        return true;
    }
    
}
