package com.web.portfolio.service;

import com.web.portfolio.dao.ClassifyRepository;
import com.web.portfolio.dao.InvestorRepository;
import com.web.portfolio.dao.PortfolioRepository;
import com.web.portfolio.dao.TStockRepository;
import com.web.portfolio.dao.WatchRepository;
import com.web.portfolio.entity.Investor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private ClassifyRepository classifyRepository;
    
    @Autowired
    private TStockRepository tStockRepository;
    
    @Autowired
    private InvestorRepository investorRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private WatchRepository watchRepository;

    public ClassifyRepository getClassifyRepository() {
        return classifyRepository;
    }

    public TStockRepository gettStockRepository() {
        return tStockRepository;
    }

    public InvestorRepository getInvestorRepository() {
        return investorRepository;
    }

    public PortfolioRepository getPortfolioRepository() {
        return portfolioRepository;
    }

    public WatchRepository getWatchRepository() {
        return watchRepository;
    }
    
    
}
