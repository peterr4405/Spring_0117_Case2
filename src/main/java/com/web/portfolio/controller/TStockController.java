package com.web.portfolio.controller;

import com.web.portfolio.entity.TStock;
import com.web.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/tstock")
public class TStockController {
    
    @Autowired
    private PortfolioService service;
    
    @GetMapping(value = {"/" , "/query"})
    public Iterable<TStock>query(){
        return service.gettStockRepository().findAll();
    }
    
    
}
