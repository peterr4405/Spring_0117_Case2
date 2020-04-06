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
        Iterable<TStock> list = service.gettStockRepository().findAll();

        for (TStock tStock : list) {
            try {
                Stock stock = YahooFinance.get(tStock.getSymbol());
                tStock.setChangePrice(stock.getQuote().getChange()); // 漲跌
                tStock.setChangeInPercent(stock.getQuote().getChangeInPercent()); // 漲跌幅%
                tStock.setPreClosed(stock.getQuote().getPreviousClose()); // 昨收
                tStock.setPrice(stock.getQuote().getPrice()); // 報價
                tStock.setTransactionDate(stock.getQuote().getLastTradeTime().getTime()); // 交易時間
                tStock.setVolumn(stock.getQuote().getVolume()); // 交易量
                // 更新報價
                service.gettStockRepository().save(tStock);
            } catch (Exception e) {
            }
        }

        return list;
    }

    @GetMapping(value = {"/histquotes/{symbol:.+}"}) // 歷史股價
    public List<HistoricalQuote> queryHistQuotes(@PathVariable("symbol") String symbol) {
       
         List<HistoricalQuote> HistQuotes = null;
        try {
            
            Calendar from = Calendar.getInstance();
            Calendar to = Calendar.getInstance();
            from.add(Calendar.MONTH,-1);
            
            Stock google = YahooFinance.get(symbol);
            HistQuotes = google.getHistory(from,to,Interval.DAILY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return HistQuotes;
    }
}
