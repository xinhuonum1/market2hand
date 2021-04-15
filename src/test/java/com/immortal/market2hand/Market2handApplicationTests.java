package com.immortal.market2hand;

import com.immortal.market2hand.dao.common.GoodsCategoryDao;
import com.immortal.market2hand.dao.common.GoodsDao;
import com.immortal.market2hand.dao.common.NewsDao;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.GoodsCategory;
import com.immortal.market2hand.service.common.CollectorsService;
import com.immortal.market2hand.service.common.GoodsCategoryService;
import com.immortal.market2hand.service.common.StatisticsService;
import com.immortal.market2hand.util.MailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.math.BigInteger;
import java.util.*;


@SpringBootTest
class Market2handApplicationTests {

    public static String getType(Object test) {
        return test.getClass().getName().toString();

    }

    @Resource
    private CollectorsService collectorsService;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private GoodsCategoryDao goodsCategoryDao;

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private NewsDao newsDao;

    @Test
    void mailTest() throws MessagingException {
        MailUtils.sendMail("876332171@qq.com");
    }

    @Test
    public void findCollectorsByStudent(){
        long id = 4;
        List<Goods> goodsList = collectorsService.findCollectorsByStudent(id);

        HashMap<Long, List<Goods>> map = new HashMap<>();

        for (Goods g: goodsList
        ) {
            ArrayList<Goods> goods = new ArrayList<Goods>();

            long key = g.getStudent().getId();
                if (map.containsKey(key)){
                    map.get(key).add(g);
                    continue;
            }
            goods.add(g);
            map.put(key,goods);
        }
        Set<Map.Entry<Long, List<Goods>>> entries = map.entrySet();
        for (Map.Entry<Long, List<Goods>> entry:entries
             ) {
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }
    }

    @Test
    public void getSqlMap(){

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
        System.out.println(floats.toString());
    }


    @Test
    public void testFindByYears(){
        List<String> years = new ArrayList<>();
        List<Double> price = new ArrayList<>();

        //此处年份可以通过数据库查询  为了减轻数据压力 此项目开发阶段年份自定义
        for( int i  = 2015;i<=2021;i++){
            years.add(Integer.toString(i));
        }
        List<Map<String, Object>> listByYears = goodsDao.findListByYears();
        for (String year:years
             ) {
            double sum = 0;
            for (Map<String,Object> map:listByYears
            ) {
                if(map.get("years").equals(BigInteger.valueOf(Long.valueOf(year)))){
                    sum += (double)map.get("sellPrice");
                }
            }
            price.add(sum);
        }
        System.out.println(price.toString());
    }

    @Test
    public void testNewsCount(){
        Long newsCount = newsDao.findNewsCount(4l);
        System.out.println(newsCount);
    }
}
