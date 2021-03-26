package com.immortal.market2hand.dao.admin;

import com.immortal.market2hand.entity.admin.DatabaseBak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 数据库备份处理层
 * @author Administrator
 *
 */
@Repository
public interface DatabaseBakDao extends JpaRepository<DatabaseBak, Long>{

	/**
	 * 根据id来查询
	 * @param id
	 * @return
	 */
	@Query("select db from DatabaseBak db where id = :id")
	DatabaseBak find(@Param("id") Long id);
	
	
}
