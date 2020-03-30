package com.web.portfolio.controller;

import com.web.portfolio.entity.Investor;
import com.web.portfolio.service.PortfolioService;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/portfolio")
public class LoginController {

    @Autowired
    private PortfolioService service;
    
    @RequestMapping(value = {"/login"})
    public String login(HttpSession session, 
            @RequestHeader(value = "referer", required = false) String referer, 
            @RequestParam("username") String username, 
            @RequestParam("password") String password) {
        
        try {
            Investor investor = service.getInvestorRepository().getInvestor(username);
            if (investor != null && investor.getPassword().equals(password)) {
                session.setAttribute("investor", investor);
                session.setAttribute("watch_id", investor.getWatchs().iterator().next().getId());
                if(referer.contains("login.jsp")) {
                    return "redirect:/portfolio/index.jsp";
                }
                return "redirect:" + referer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.invalidate();
        return "redirect:/portfolio/login.jsp";
    }
    
    @RequestMapping(value = {"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/portfolio/login.jsp";
    }
    
}
