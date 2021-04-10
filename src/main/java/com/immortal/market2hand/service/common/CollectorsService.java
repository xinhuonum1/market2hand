package com.immortal.market2hand.service.common;

import com.immortal.market2hand.bean.CodeMsg;
import com.immortal.market2hand.bean.Result;
import com.immortal.market2hand.constant.SessionConstant;
import com.immortal.market2hand.dao.common.CollectorsDao;
import com.immortal.market2hand.entity.admin.User;
import com.immortal.market2hand.entity.common.Collector;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/2
 */
@Service
public class CollectorsService {
    @Resource
    private CollectorsDao collectorsDao;

    @Resource
    private GoodsService goodsService;


   public List<Goods> findCollectorsByStudent(Long id){
       List<Goods> goods = collectorsDao.findCollectorsByStudent(id);

       return goods;
   }

   public List<Goods> findOrdersByStudent(Long id){
       List<Goods> orders = collectorsDao.findOrdersByStudent(id);
       return orders;
   }

    public Result addCollectionByGoodsId(Long id) {
        Student loginer = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Collector collector = new Collector();
        collector.setCollectors(loginer);
        collector.setGoods(goodsService.findById(id));
        collector.setStatus(1);  //收藏状态
        Date date = new Date();
        collector.setCreateTime(date);
        collector.setUpdateTime(date);

        Collector save = collectorsDao.save(collector);
        if(null != save){
            return Result.success("收藏成功！");
        }else{
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }

    public Result removeCollectionById(Long id) {
       collectorsDao.removeCollectionById(id);
       return Result.success("已取消收藏！");
    }

    public Result addOrdersGoods(Long id) {
        Student currentLogin = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        Collector orders = new Collector();
        orders.setCollectors(currentLogin);
        orders.setGoods(goodsService.findById(id));
        orders.setStatus(2);   //购买状态
        Date date = new Date();
        orders.setCreateTime(date);
        orders.setUpdateTime(date);

        Collector save = collectorsDao.save(orders);
        if(null != save){
            return Result.success("购买成功！");
        }else{
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }
}
