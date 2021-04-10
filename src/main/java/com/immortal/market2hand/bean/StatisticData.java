package com.immortal.market2hand.bean;

import com.immortal.market2hand.entity.common.GoodsCategory;

import java.util.List;

/**
 * @market2hand
 * @author: immor
 * @date: 2021/4/9
 */
public class StatisticData {
    private List<String> categories;
    private List<Float> data;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StatisticData{" +
                "categories=" + categories +
                ", data=" + data +
                '}';
    }
}
