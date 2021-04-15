package com.immortal.market2hand.dao.common;

import com.immortal.market2hand.entity.common.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


/**
 * 新闻公告dao层
 */
@Repository
public interface NewsDao extends JpaRepository<News, Long> {

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Query(value = "select count(*) from ylrc_news where student_id = :id and isread=0", nativeQuery = true)
    Long findNewsCount(@Param("id") Long id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Query("select n from News n where id = :id")
    News find(@Param("id") Long id);

    /**
     * 查找所欲公告消息
     *
     * @return
     */
    @Query("select n from News n where status = 1")
    List<News> findAllNews();

    @Transactional
    @Modifying
    @Query(value = "update ylrc_news set isread = 1 where student_id = :id", nativeQuery = true)
    void updateNewsRead(Long id);
}
