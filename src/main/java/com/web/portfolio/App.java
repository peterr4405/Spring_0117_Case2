package com.web.portfolio;

import com.web.portfolio.entity.Classify;
import com.web.portfolio.entity.Investor;
import com.web.portfolio.entity.TStock;
import com.web.portfolio.entity.Watch;
import com.web.portfolio.service.PortfolioService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    static PortfolioService service;
    
    static {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.web.portfolio");
        appContext.refresh();
        service = (PortfolioService) appContext.getBean("portfolioService");
    }
    public static void main(String[] args) throws Exception {
        Investor investor1 = new Investor("admin", "1234", "admin@java.com", 10000000);
        Investor investor2 = new Investor("john", "1111", "john@java.com", 5000000);
        service.getInvestorRepository().save(investor1);
        service.getInvestorRepository().save(investor2);

        Classify classify1 = new Classify("股票", true);
        Classify classify2 = new Classify("匯率", true);
        Classify classify3 = new Classify("指數", false);
        service.getClassifyRepository().save(classify1);
        service.getClassifyRepository().save(classify2);
        service.getClassifyRepository().save(classify3);

        TStock ts1 = new TStock("2330.TW", "台積電", classify1);
        TStock ts2 = new TStock("2317.TW", "鴻海", classify1);
        TStock ts3 = new TStock("1101.TW", "台泥", classify1);
        
        TStock ts4 = new TStock("USDTWD=x", "美金台幣", classify2);
        TStock ts5 = new TStock("JPYTWD=x", "日幣台幣", classify2);
        TStock ts6 = new TStock("CNYTWD=x", "人民幣台幣", classify2);
        
        TStock ts7 = new TStock("^TWII", "台灣加權", classify3);
        TStock ts8 = new TStock("^IXIC", "納斯達克", classify3);
        TStock ts9 = new TStock("^DJI", "道瓊工業", classify3);
        
        service.gettStockRepository().save(ts1);
        service.gettStockRepository().save(ts2);
        service.gettStockRepository().save(ts3);
        service.gettStockRepository().save(ts4);
        service.gettStockRepository().save(ts5);
        service.gettStockRepository().save(ts6);
        service.gettStockRepository().save(ts7);
        service.gettStockRepository().save(ts8);
        service.gettStockRepository().save(ts9);
        
        //Portfolio portfolio1 = new Portfolio(60.5, 2000, investor1, ts1);
        //Portfolio portfolio2 = new Portfolio(35.5, 5000, investor1, ts2);
        //portfolioRepository.save(portfolio1);
        //portfolioRepository.save(portfolio2);
        
        Watch watch = new Watch();
        watch.setInvestor(investor1);
        watch.setName("我的觀察股");
        service.getWatchRepository().save(watch);
    }
    
    
    
}
