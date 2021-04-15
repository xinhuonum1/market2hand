package com.immortal.market2hand.dao.common;

import com.immortal.market2hand.entity.common.Collector;
import com.immortal.market2hand.entity.common.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/2
 */
@Repository
public interface CollectorsDao extends JpaRepository<Collector, Long>, JpaSpecificationExecutor<Collector> {
    /**
     * 根据用户id查询收藏的商品
     *
     * @author:immor
     * @date:2021/4/2
     */
    @Query("SELECT g from Goods g where g.id in (select cs.goods from Collector cs where cs.collectors.id = :id and cs.status = 1)")
    List<Goods> findCollectorsByStudent(@Param("id") Long id);


    /**
     * 根据用户id查询购买的商品
     *
     * @author:immor
     * @date:2021/4/2
     */
    @Query("SELECT g from Goods g where g.id in (select cs.goods from Collector cs where cs.collectors.id = :id and cs.status = 2)")
    List<Goods> findOrdersByStudent(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Collector s set s.status = 0 where s.goods.id = :id")
    void removeCollectionById(Long id);
}
