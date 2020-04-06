package com.web.portfolio.controller;



import com.web.portfolio.entity.TStock;
import com.web.portfolio.service.PortfolioService;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping("/portfolio/price")
public class PriceController {
    
    @Autowired
    private PortfolioService service;
    
    @GetMapping(value = {"/refresh"})
    @Transactional
    public Iterable<TStock> refresh() {
        // Block of code
        return null;
    }
    
    @GetMapping(value = {"/histquotes/{symbol:.+}"}) // 歷史股價
    public List<HistoricalQuote> queryHistQuotes(@PathVariable("symbol") String symbol) {
        // Block of code
        return null;
    }
}

