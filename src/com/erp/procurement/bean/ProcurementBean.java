package com.erp.procurement.bean;

import com.formwork.base.bean.BaseBean;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class ProcurementBean extends BaseBean {

    public static final String STATE_NOT_OUT="未出库";
    public static final String STATE_OUT="已出库";
    private Integer saleId;
    private Date inDate;
    private Date outDate;
    private String  salesmanName;
    private Integer salesmanId;
    private String warehouseKeeper;
    private Integer warehouseKeeperId;
//    private List<SaleOrderDetail> saleOrderDetail;
    private Integer clientId;
    private String clientName;
    private int totalMoney;
    private String state;

    public static String getStateNotOut() {
        return STATE_NOT_OUT;
    }

    public static String getStateOut() {
        return STATE_OUT;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(String warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public Integer getWarehouseKeeperId() {
        return warehouseKeeperId;
    }

    public void setWarehouseKeeperId(Integer warehouseKeeperId) {
        this.warehouseKeeperId = warehouseKeeperId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
