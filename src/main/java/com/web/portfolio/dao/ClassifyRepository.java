package com.web.portfolio.dao;

import com.web.portfolio.entity.Classify;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "classifyRepository")
public interface ClassifyRepository extends CrudRepository<Classify, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Classify SET name=?2, transaction=?3 WHERE id=?1")
    public void update(Long id, String name, Boolean transaction);

}
