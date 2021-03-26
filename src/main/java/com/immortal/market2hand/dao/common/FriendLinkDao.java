package com.immortal.market2hand.dao.common;

import com.immortal.market2hand.entity.common.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 友情链接dao层
 */
@Repository
public interface FriendLinkDao extends JpaRepository<FriendLink, Long> {
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Query("select fl from FriendLink fl where id = :id")
	FriendLink find(@Param("id") Long id);
}
