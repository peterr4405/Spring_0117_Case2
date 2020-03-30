package com.web.portfolio.dao;

import com.web.portfolio.entity.Classify;
import com.web.portfolio.entity.Investor;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "investorRepository")
public interface InvestorRepository extends CrudRepository<Investor, Long> {

    @Query(value = "Select i From Investor i Where i.pass=true And i.username=?1")
    public Investor getInvestor(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Investor SET username=?2, password=?3, email=?4, balance=?5 WHERE id=?1")
    public void update(Long id, String username, String password, String email, Integer balance);

}
