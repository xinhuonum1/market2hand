package com.immortal.market2hand.entity.common;

import javax.persistence.*;

import com.immortal.market2hand.annotion.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * 新闻实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "ylrc_news")
@EntityListeners(AuditingEntityListener.class)
public class News extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ValidateEntity(required = true, requiredLeng = true, minLength = 1, maxLength = 64, errorRequiredMsg = "公告标题不能为空!", errorMinLengthMsg = "新闻标题长度需大于1!", errorMaxLengthMsg = "新闻标题长度不能大于64!")
    @Column(name = "title", nullable = false, length = 64)
    private String title;//公告标题


    @ValidateEntity(required = true, requiredLeng = true, minLength = 1, maxLength = 10000, errorRequiredMsg = "公告内容不能为空!", errorMinLengthMsg = "新闻内容长度需大于1!", errorMaxLengthMsg = "新闻内容长度不能大于10000!")
    @Column(name = "content", nullable = false, length = 10024)
    private String content;//公告内容

    @Column(name = "view_number", nullable = false, length = 8)
    private Integer viewNumber = 0;

    @Column(name = "sort", nullable = false, length = 4)
    private Integer sort = 0;//分类顺序，默认升序排列,默认是0

    @Column(name = "status", nullable = false, length = 4)
    private Integer status = 1;//状态码，1是系统公告,0是订单消息

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "isread", nullable = false, length = 2)
    private Integer isread; //1:读了  0：未读

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewNumber=" + viewNumber +
                ", sort=" + sort +
                ", status=" + status +
                ", student=" + student +
                ", isread=" + isread +
                '}';
    }
}
