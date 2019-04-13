package com.erp.storehouse.bean;

import com.formwork.base.bean.BaseBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class StoreBean extends BaseBean {

    private Integer warehouseId;
    private String warehouseName;
    private String address;
    private String warehouseKeeper;

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(String warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }
}
