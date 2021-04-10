package com.immortal.market2hand.controller.admin;

import com.immortal.market2hand.bean.CodeMsg;
import com.immortal.market2hand.bean.Result;
import com.immortal.market2hand.constant.SessionConstant;
import com.immortal.market2hand.entity.admin.User;
import com.immortal.market2hand.entity.common.Goods;
import com.immortal.market2hand.entity.common.Student;
import com.immortal.market2hand.service.common.CollectorsService;
import com.immortal.market2hand.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/2
 */
@Controller
public class CollectorsController {
    @Resource
    private CollectorsService collectorsService;

    @RequestMapping("/findGoodsByCollectors")
    @ResponseBody
    public Result findCollectorsByStudent(){
        //获取当前登录的用户
        Student student = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
        if(null == student){
            return Result.error(CodeMsg.USER_SESSION_EXPIRED);
        }
        long id = student.getId();
        List<Goods> goodsList = collectorsService.findCollectorsByStudent(id);

        HashMap<String, List<Goods>> map = new HashMap<>();

        for (Goods g: goodsList
        ) {
            ArrayList<Goods> goods = new ArrayList<Goods>();

            String nickname = g.getStudent().getNickname();
            if (map.containsKey(nickname)){
                map.get(nickname).add(g);
                continue;
            }
            goods.add(g);
            map.put(nickname,goods);
        }
        /*Set<Map.Entry<Long, List<Goods>>> entries = map.entrySet();
        for (Map.Entry<Long, List<Goods>> entry:entries
        ) {
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }*/
        return Result.success(map);
    }
}
