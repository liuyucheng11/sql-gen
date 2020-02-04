package com.itworkorder.sqlgen.common.tables;

import com.itworkorder.sqlgen.pojo.contract.ContractDetailDTO;
import com.itworkorder.sqlgen.pojo.contractapply.ContractApplyDetailDTO;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;
import com.itworkorder.sqlgen.pojo.supplier.SupplierBankRelationDTO;
import com.itworkorder.sqlgen.pojo.supplier.SupplierDTO;

/**
 * <p>表枚举</p>
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2019-12-31  16:50
 * @version 1.0
 */
public enum TableNameEnum {
    T_SUPPLIER("供应商详情表", "t_contract_supplier_details", 1, SupplierDTO.class),
    T_SUPPLIER_BANK_RELATION("供应商银行关系表", "t_contract_supplier_bank_relation ", 2, SupplierBankRelationDTO.class),
    T_CONTRACT_APPLY_DETAIL("采购申请明细表", "t_contract_supplier", 3, ContractApplyDetailDTO.class),
    T_CONTRACT_DETAIL("采购合同明细","t_contract_detail",4, ContractDetailDTO.class);
    private String tableName;

    private String tableDesc;

    private Integer code;

    /**
     * 对应前端DTO参数
     */
    private Class<? extends BaseDTO> dto;

    TableNameEnum(String tableDesc, String tableName, Integer code, Class dto) {
        this.tableName = tableName;
        this.tableDesc = tableDesc;
        this.code = code;
        this.dto = dto;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Class<? extends BaseDTO> getDto() {
        return dto;
    }

    public void setDto(Class<? extends BaseDTO> dto) {
        this.dto = dto;
    }
}
