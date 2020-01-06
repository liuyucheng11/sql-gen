package com.itworkorder.sqlgen.pojo.supplier;

import java.util.Date;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  13:49
 * @version 1.0
 */
public class SupplierDTO extends BaseDTO {

    private Long id;

    private String supplierNo;

    private Date effeTime;

    private String supplierName;

    private Integer authenticationStatus;

    /**
     * 是否所有门店
     */
    private Integer allSupplyStore;

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(Integer authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEffeTime() {
        return effeTime;
    }

    public void setEffeTime(Date effeTime) {
        this.effeTime = effeTime;
    }

    public Integer getAllSupplyStore() {
        return allSupplyStore;
    }

    public void setAllSupplyStore(Integer allSupplyStore) {
        this.allSupplyStore = allSupplyStore;
    }
}
