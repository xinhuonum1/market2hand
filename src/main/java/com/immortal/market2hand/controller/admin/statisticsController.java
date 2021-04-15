package com.immortal.market2hand.controller.admin;

import com.immortal.market2hand.bean.StatisticData;
import com.immortal.market2hand.service.common.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/8
 */
@Controller
@RequestMapping("admin/statistics")
public class statisticsController {
    @Resource
    private StatisticsService statisticsService;

    /**
     * 报表分析 /admin/statistics/statistics
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/statistics")
    public String statistics(Model model) {
        model.addAttribute("title", "统计报表");
        return "admin/statistics/statistics";
    }

    /**
     * 类别销售额统计分析
     *
     * @return
     */
    @RequestMapping("/getMyChartData")
    @ResponseBody
    public StatisticData getMyChartData() {
        List<String> byParentIsNull = statisticsService.findByParentIsNull();
        List<Float> listBySelled = statisticsService.findListBySell();
        StatisticData statisticData = new StatisticData();
        statisticData.setCategories(byParentIsNull);
        statisticData.setData(listBySelled);
        return statisticData;
    }

    /**
     * 年度销售额统计分析
     *
     * @return
     */
    @RequestMapping("/getMyChartDataByTime")
    @ResponseBody
    public StatisticData getMyLineData() {
        return statisticsService.findListByYears();
    }


}
