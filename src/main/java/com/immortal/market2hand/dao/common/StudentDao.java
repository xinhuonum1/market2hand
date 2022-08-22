package com.immortal.market2hand.dao.common;
/**
 * 学生信息操作dao类
 */

import com.immortal.market2hand.entity.common.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    /**
     * 根据学号查询
     *
     * @param sn
     * @return
     */
    Student findBySn(String sn);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Query("select s from Student s where id = :id")
    Student find(@Param("id") Long id);

    @Query("select s from Student s where stuemail = :stuemail")
    Student findByEmail(String stuemail);

    @Transactional
    @Modifying
    @Query("update Student s set s.password = :password where s.stuemail = :stuemail")
    void updatePasswordByEmail(@Param("stuemail") String stuemail, @Param("password") String password);

    @Query("select s from Student s where stuemail = :account or nickname=:account or sn=:account")
    Student findByNameAndEmail(@Param("account") String account);
}
