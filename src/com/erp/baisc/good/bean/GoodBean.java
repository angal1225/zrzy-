package com.erp.baisc.good.bean;

import com.formwork.base.bean.BaseBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class GoodBean extends BaseBean {

    private Integer goodId;
    private String goodName;
    private String goodPlace;
    private String goodProductor;
    private String countUnit;
    private String purchasingPrice;
    private String sellingPrice;
//    private GoodType goodType;


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

    public String getGoodPlace() {
        return goodPlace;
    }

    public void setGoodPlace(String goodPlace) {
        this.goodPlace = goodPlace;
    }

    public String getGoodProductor() {
        return goodProductor;
    }

    public void setGoodProductor(String goodProductor) {
        this.goodProductor = goodProductor;
    }

    public String getCountUnit() {
        return countUnit;
    }

    public void setCountUnit(String countUnit) {
        this.countUnit = countUnit;
    }

    public String getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(String purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
