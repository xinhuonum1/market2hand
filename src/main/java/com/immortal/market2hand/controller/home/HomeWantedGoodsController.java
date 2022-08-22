package com.immortal.market2hand.controller.home;

import com.immortal.market2hand.bean.PageBean;
import com.immortal.market2hand.entity.common.WantedGoods;
import com.immortal.market2hand.service.common.WantedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 求购物品控制器
 *
 * @author Administrator
 */
@RequestMapping("/home/wanted_goods")
@Controller
public class HomeWantedGoodsController {

    @Autowired
    private WantedGoodsService wantedGoodsService;

    /**
     * 求购物品列表页面
     *
     * @param model
     * @param pageBean
     * @param WantedGoods
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, PageBean<WantedGoods> pageBean, WantedGoods WantedGoods) {
        model.addAttribute("pageBean", wantedGoodsService.findWantedGoodslist(pageBean, WantedGoods));
        return "home/wanted_goods/list";
    }
}
