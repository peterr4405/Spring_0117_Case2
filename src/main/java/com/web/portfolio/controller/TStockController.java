package com.web.portfolio.controller;

import com.web.portfolio.entity.Classify;
import com.web.portfolio.entity.TStock;
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
@RequestMapping("/portfolio/tstock")
public class TStockController {
    @Autowired
    private PortfolioService service;
    
    @GetMapping(value = {"/", "/query"})
    public Iterable<TStock> query() {
        return service.gettStockRepository().findAll();
    }
    
    @PostMapping(value = {"/", "/add"})
    @Transactional
    public TStock add(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Long.parseLong(map.get("classify_id")));
        TStock ts = new TStock();
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        service.gettStockRepository().save(ts);
        return ts;
    }
    
    @PutMapping(value = {"/", "/update"})
    @Transactional
    public TStock update(@RequestBody Map<String, String> map) {
        Classify classify = service.getClassifyRepository().findOne(Long.parseLong(map.get("classify_id")));
        TStock ts = service.gettStockRepository().findOne(Long.parseLong(map.get("id")));
        ts.setName(map.get("name"));
        ts.setSymbol(map.get("symbol"));
        ts.setClassify(classify);
        service.gettStockRepository().update(Long.parseLong(map.get("id")), map.get("name"), map.get("symbol"), classify.getId());
        return ts;
    }
    
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Long id) {
        service.gettStockRepository().delete(id);
        return true;
    }
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public TStock get(@PathVariable("id") Long id) {
        TStock tStock = service.gettStockRepository().findOne(id);
        return tStock;
    }
    
}
