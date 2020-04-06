package com.web.portfolio.controller;

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
        
        TStock tStock = new TStock();
        tStock.setName(map.get("name"));
        tStock.setSymbol(map.get("symbol"));
        System.out.println(map.get("classify_id"));
        
        service.gettStockRepository().save(tStock);
        
        return tStock;
    }
    
    @Transactional
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    public Boolean delete(@PathVariable("id") Long id) {
        service.gettStockRepository().delete(id);
        return true;
    }
    
    @Transactional
    @GetMapping(value = {"/{id}", "/get/{id}"})
    public TStock get(@PathVariable("id") Long id) {
        TStock tStock = service.gettStockRepository().findOne(id);
        
        return tStock;
    }
    
    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        TStock tStock = get(id);
        if (tStock == null) {
            return false;
        }
        tStock.setName(map.get("name"));
        tStock.setSymbol(map.get("symbol"));
        
        service.gettStockRepository().update(id, tStock.getName(), tStock.getSymbol());
        return true;
    }
    
}
