package com.web.portfolio.dao;

import com.web.portfolio.entity.Classify;
import com.web.portfolio.entity.Investor;
import com.web.portfolio.entity.TStock;
import com.web.portfolio.entity.Watch;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "watchRepository")
public interface WatchRepository extends CrudRepository<Watch, Long>{

}