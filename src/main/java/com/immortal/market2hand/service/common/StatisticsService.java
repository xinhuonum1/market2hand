package com.immortal.market2hand.service.common;

import com.immortal.market2hand.bean.StatisticData;
import com.immortal.market2hand.dao.common.GoodsCategoryDao;
import com.immortal.market2hand.dao.common.GoodsDao;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.GoodsCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/8
 */
@Service
public class StatisticsService {
    @Resource
    private GoodsCategoryDao goodsCategoryDao;

    @Resource
    private GoodsDao goodsDao;

    public List<String> findByParentIsNull(){
        List<GoodsCategory> parentIsNull = goodsCategoryDao.findByParentIsNull();
        ArrayList<String> categoryList = new ArrayList<>();
        for (GoodsCategory gc:parentIsNull
             ) {
            categoryList.add(gc.getName());
        }
        return categoryList;
    }

    public List<Float> findListBySell(){
        List<Map<String, Object>> listBySell = goodsDao.findListBySell();
        List<GoodsCategory> byParentIsNull = goodsCategoryDao.findByParentIsNull();

        ArrayList<Float> floats = new ArrayList<>();

        for (GoodsCategory s:byParentIsNull
        ) {
            float sum = 0f;
            for (Map<String,Object> m:listBySell
            ) {
                if(m.get("goods_category_id").equals(BigInteger.valueOf(s.getId()))){
                    Double sell_price = (Double) m.get("sell_price");
                    float v = sell_price.floatValue();
                    sum = sum+ v;
                }
            }
            floats.add(sum);

        }
        return floats;
    }

    public StatisticData findListByYears(){
        StatisticData statisticData = new StatisticData();

        List<String> years = new ArrayList<>();
        List<Float> price = new ArrayList<>();
        for( int i  = 2015;i<=2021;i++){
            years.add(Integer.toString(i));
        }
        List<Map<String, Object>> listByYears = goodsDao.findListByYears();
        for (String year:years
        ) {
            float sum = 0;
            for (Map<String,Object> map:listByYears
            ) {
                if(map.get("years").equals(BigInteger.valueOf(Long.valueOf(year)))){
                   Double sellPrice =  (Double)map.get("sellPrice");
                    float floatValue = sellPrice.floatValue();
                    sum += floatValue;
                }
            }
            price.add(sum);
        }
        statisticData.setCategories(years);
        statisticData.setData(price);

        return statisticData;
    }
    }
