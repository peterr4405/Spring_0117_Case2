package com.web.portfolio.controller;



import com.web.portfolio.entity.Investor;
import com.web.portfolio.entity.Portfolio;
import com.web.portfolio.entity.TStock;
import com.web.portfolio.service.PortfolioService;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio/order")
public class OrderController {

    @Autowired
    private PortfolioService service;
    
    // 買進
    @GetMapping(value = {"/buy/{tstock_id}/{amount}"})
    @Transactional
    public String buy(HttpSession session, @PathVariable("tstock_id") Long tstock_id, @PathVariable("amount") Integer amount) {
        // Block of code
        return null;
    }
    
    // 賣出
    @GetMapping(value = {"/sell/{id}/{amount}"})
    @Transactional
    public String sell(HttpSession session, @PathVariable("id") Long id, @PathVariable("amount") Integer amount) {
        // Block of code
        return null;
    }

    
}