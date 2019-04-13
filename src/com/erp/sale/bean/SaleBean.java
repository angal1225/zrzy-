package com.erp.sale.bean;

import com.formwork.base.bean.BaseBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class SaleBean extends BaseBean {

    public static final String STATE_NOT_OUT="未出库";
    public static final String STATE_OUT="已出库";
    private Integer saleOrderDetailId;
    private Integer goodId;
    private String goodName;
    private int price;
    private Integer num;
    private int money;
//    @JSONField(serialize=false)
//    private Sale sale;

    public static String getStateNotOut() {
        return STATE_NOT_OUT;
    }

    public static String getStateOut() {
        return STATE_OUT;
    }

    public Integer getSaleOrderDetailId() {
        return saleOrderDetailId;
    }

    public void setSaleOrderDetailId(Integer saleOrderDetailId) {
        this.saleOrderDetailId = saleOrderDetailId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
