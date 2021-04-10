package com.immortal.market2hand.dao.common;

import java.util.List;
import java.util.Map;

import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * 物品数据库操作dao
 */

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long>,JpaSpecificationExecutor<Goods> {
	/**
	 * 根据id获取
	 * @return
	 */
	@Query("select g from Goods g where id = :id")
	Goods find(@Param("id") Long id);
	
	/**
	 * 根据学生查询
	 * @param student
	 * @return
	 */
	List<Goods> findByStudent(Student student);
	
	/**
	 * 根据学生id和商品id查询
	 * @param id
	 * @return
	 */
	@Query("select g from Goods g where id = :id and g.student.id = :studentId")
	Goods find(@Param("id") Long id, @Param("studentId") Long studentId);
	
	/**
	 * 根据物品分类查询物品列表
	 * @param cids
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT * from ylrc_goods where goods_category_id IN :cids and `status` = 1 ORDER BY sell_price DESC limit :offset,:pageSize",nativeQuery=true)
	List<Goods> findList(@Param("cids") List<Long> cids, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
	
	/**
	 * 获取根据分类搜索的总条数
	 * @param cids
	 * @return
	 */
	@Query(value="SELECT count(*) from ylrc_goods where goods_category_id IN :cids and `status` = 1 ",nativeQuery=true)
	Long getTotalCount(@Param("cids") List<Long> cids);
	
	/**
	 * 获取指定状态的物品总数
	 * @param status
	 * @return
	 */
	@Query("SELECT count(g) from Goods g where g.status = :status ")
	Long getTotalCount(@Param("status") Integer status);
	
	/**
	 * 根据物品名称模糊搜索
	 * @param name
	 * @return
	 */
	@Query(value="select * from ylrc_goods where name like %:name%",nativeQuery=true)
	List<Goods> findListByName(@Param("name") String name);

	/**
	 * 类别销售额统计
	 * @return
	 */
	@Query(value="select gc.parent_id goods_category_id ,gc.name,sc.countSell sell_price ,sc.time update_time from " +
			"ylrc_goods_category gc, " +
			"(select goods_category_id id, update_time time, SUM(sell_price*need_number) countSell " +
			"FROM ylrc_goods " +
			"where status = 3 group by goods_category_id ) sc where gc.id = sc.id",nativeQuery=true)
	List<Map<String,Object>> findListBySell();

	/**
	*
	*年度销售额统计
	*@return:{@link null}
	*@author:immor
	*@date:2021/4/10
	*/
	@Query( value = "select year(update_time) as years,(sell_price*need_number) sellPrice from ylrc_goods where `status`=3",nativeQuery = true)
	List<Map<String,Object>> findListByYears();

}
