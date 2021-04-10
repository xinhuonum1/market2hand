package com.immortal.market2hand.entity.common;

import com.immortal.market2hand.annotion.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/2
 */
@Entity
@Table(name="ylrc_collector")
@EntityListeners(AuditingEntityListener.class)
public class Collector extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private static final int COLLECTORS_STATUS_ENABLE = 1;
    private static final int COLLECTORS_STATUS_UNABLE = 0;

    @ManyToOne
    @JoinColumn(name="collectors_id")
    private Student collectors;//所属学生

    @ManyToOne
    @JoinColumn(name="goods_id")
    private Goods goods;//所属学生

    @ValidateEntity(required=false)
    @Column(name="status",length=1)
    private int status = COLLECTORS_STATUS_ENABLE;//收藏状态，默认可用



    public Student getCollectors() {
        return collectors;
    }

    public void setCollectors(Student collectors) {
        this.collectors = collectors;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Collector{" +
                "collectors=" + collectors +
                ", goods=" + goods +
                ", status=" + status +
                '}';
    }
}
