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
        
        Investor login_inInvestor = (Investor)session.getAttribute("investor");
        Investor investor = service.getInvestorRepository().findOne(login_inInvestor.getId());
        
        if(investor == null) return "Investor None";
        TStock ts = service.gettStockRepository().findOne(tstock_id);
        if(ts == null) return "TStock None";
        
        int buyTotalCost = (int)(ts.getPrice().doubleValue() * amount);
        int balance = investor.getBalance() - buyTotalCost;
        if(balance <0) return "餘額不足";
        
        investor.setBalance(balance);
        
        Portfolio po = new Portfolio();
        po.setInvestor(investor);
        po.settStock(ts);
        po.setCost(ts.getPrice().doubleValue());
        po.setAmount(amount);
        po.setDate(new Date());
        
        investor.getPortfolios().add(po);
        
        service.getInvestorRepository().save(investor);
        service.getPortfolioRepository().save(po);
        
        return "OK";
    }
    
    // 賣出
    @GetMapping(value = {"/sell/{id}/{amount}"})
    @Transactional
    public String sell(HttpSession session, @PathVariable("id") Long id, @PathVariable("amount") Integer amount) {
        Investor login_investor = (Investor)session.getAttribute("investor");
        Investor investor = service.getInvestorRepository().findOne(login_investor.getId());
        if(investor == null) return "Investor None";
        
        Portfolio po = service.getPortfolioRepository().findOne(id);
        if(po == null) return "Portfolio None";
        
        po.setAmount(po.getAmount() - amount);
        
        // 回滾利潤
        int profit = (int)(amount * po.gettStock().getPrice().doubleValue());
        investor.setBalance(investor.getBalance() + profit);
        
        
        service.getPortfolioRepository().save(po);
        service.getInvestorRepository().save(investor);
        return "OK";
    }

    
}