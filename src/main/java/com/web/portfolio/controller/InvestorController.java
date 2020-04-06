package com.web.portfolio.controller;

import com.web.portfolio.entity.Investor;
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
@RequestMapping("/portfolio/investor")
public class InvestorController {
    @Autowired
    private PortfolioService service;
    
    @GetMapping(value = {"/", "/query"})
    public Iterable<Investor> query() {
        return service.getInvestorRepository().findAll();
    }
    
    @PostMapping(value = {"/", "/add"})
    @Transactional
    public Investor add(@RequestBody Map<String, String> map) {
        System.out.println(map);
        Investor investor = new Investor();
        investor.setUsername(map.get("username"));
        investor.setPassword(map.get("password"));
        investor.setEmail(map.get("email"));
        investor.setBalance(Integer.parseInt(map.get("balance")));
        investor.setCode(Integer.toHexString(investor.hashCode()));
        investor.setPass(Boolean.TRUE);
        service.getInvestorRepository().save(investor);
        return investor;
    }
    
    @GetMapping(value = {"/{id}", "/get/{id}"})
    @Transactional
    public Investor get(@PathVariable("id") Long id) {
        Investor investor = service.getInvestorRepository().findOne(id);
        // 當 FetchType.LAZY 配置時 讓 investor 查找 Portfolios
        if(investor != null && investor.getPortfolios() != null && investor.getPortfolios().size() > 0) {
            investor.getPortfolios().size();
        }
        // 當 FetchType.LAZY 配置時 讓 investor 查找 Watchs
        if(investor != null && investor.getWatchs()!= null && investor.getWatchs().size() > 0) {
            investor.getWatchs().size();
        }
        investor.getPortfolios().size();
        return investor;
    }
    
    @PutMapping(value = {"/{id}", "/update/{id}"})
    @Transactional
    public Boolean update(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Investor o_Investor = get(id);
        System.out.println(o_Investor);
        if (o_Investor == null) {
            return false;
        }
        service.getInvestorRepository().update(id, map.get("username"), map.get("password"), map.get("email"), Integer.parseInt(map.get("balance")));
        return true;
    }
    
    @DeleteMapping(value = {"/{id}", "/delete/{id}"})
    @Transactional
    public Boolean delete(@PathVariable("id") Long id) {
        service.getInvestorRepository().delete(id);
        return true;
    }
    
}
