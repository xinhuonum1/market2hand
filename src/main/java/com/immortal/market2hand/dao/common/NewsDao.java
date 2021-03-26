package com.immortal.market2hand.dao.common;

import com.immortal.market2hand.entity.common.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 新闻公告dao层
 */
@Repository
public interface NewsDao extends JpaRepository<News, Long> {
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Query("select n from News n where id = :id")
	News find(@Param("id") Long id);
}
