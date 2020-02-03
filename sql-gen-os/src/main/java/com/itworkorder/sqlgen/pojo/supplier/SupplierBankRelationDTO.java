package com.itworkorder.sqlgen.pojo.supplier;

import com.itworkorder.sqlgen.pojo.annotation.TableField;
import com.itworkorder.sqlgen.pojo.annotation.TableId;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  17:21
 * @version 1.0
 */
public class SupplierBankRelationDTO extends BaseDTO {

    @TableId
    private Long id;

    /**
     * 银行账号
     */
    @TableField(value = "bank_account")
    private String bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
