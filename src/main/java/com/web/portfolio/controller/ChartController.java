package com.web.portfolio.controller;

import com.web.portfolio.service.PortfolioService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/chart")
public class ChartController {
    
    @PersistenceContext
    protected EntityManager em;
    
    @GetMapping(value = {"/asset/{id}"})
    public Object asset(@PathVariable("id") Long id) {
        // Block of code
        return null;
    }
    
    @GetMapping(value = {"/profit/{id}"})
    public List profit(@PathVariable("id") Long id) {
        // Block of code
        return null;
    }
    
}
