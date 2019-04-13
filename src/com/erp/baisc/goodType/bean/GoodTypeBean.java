package com.erp.baisc.goodType.bean;

import com.formwork.base.bean.BaseBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class GoodTypeBean extends BaseBean {

    private String goodTypeId;
    private String goodTypeName;
    private String goodTypeIds;

    public String getGoodTypeId() {
        return goodTypeId;
    }

    public void setGoodTypeId(String goodTypeId) {
        this.goodTypeId = goodTypeId;
    }

    public String getGoodTypeName() {
        return goodTypeName;
    }

    public void setGoodTypeName(String goodTypeName) {
        this.goodTypeName = goodTypeName;
    }

    public String getGoodTypeIds() {
        return goodTypeIds;
    }

    public void setGoodTypeIds(String goodTypeIds) {
        this.goodTypeIds = goodTypeIds;
    }
}
