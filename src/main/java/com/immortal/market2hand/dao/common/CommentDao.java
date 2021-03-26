package com.immortal.market2hand.dao.common;

import java.util.List;

import com.immortal.market2hand.entity.common.Comment;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 评论物品数据库操作dao
 */

@Repository
public interface CommentDao extends JpaRepository<Comment, Long>,JpaSpecificationExecutor<Comment> {
	/**
	 * 根据id获取
	 * @return
	 */
	@Query("select c from Comment c where id = :id")
	Comment find(@Param("id") Long id);
	
	/**
	 * 根据学生查询
	 * @param student
	 * @return
	 */
	List<Comment> findByStudent(Student student);
	
	/**
	 * 根据物品查询
	 * @param goods
	 * @return
	 */
	List<Comment> findByGoods(Goods goods);
	
	/**
	 * 根据学生id和商品id查询
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select c from Comment c where c.goods.id = :goodsId and c.student.id = :studentId")
	Comment find(@Param("goodsId") Long goodsId, @Param("studentId") Long studentId);
	
	
}
