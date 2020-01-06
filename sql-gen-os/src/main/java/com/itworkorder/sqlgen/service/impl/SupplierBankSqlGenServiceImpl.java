package com.itworkorder.sqlgen.service.impl;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.SqlContentTemplate;
import com.itworkorder.sqlgen.pojo.supplier.SupplierBankRelationDTO;
import com.itworkorder.sqlgen.service.HandlerType;

import java.text.MessageFormat;

import static com.itworkorder.sqlgen.common.tables.TableNameEnum.T_SUPPLIER_BANK_RELATION;
import static com.itworkorder.sqlgen.common.tables.TableSolution.*;

/**
 * Description: 供应商银行关系sql处理
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-4  13:59
 * @version 1.0
 */
@HandlerType(table = T_SUPPLIER_BANK_RELATION)
public class SupplierBankSqlGenServiceImpl extends AbstractHandSqlGenHandler<SupplierBankRelationDTO> {

    @Override
    protected void beforeExecute() {
        this.setBindTableName(T_SUPPLIER_BANK_RELATION.getTableName());
        super.beforeExecute();
    }

    @Override
    public Result handGenSql(SupplierBankRelationDTO dto) {
        return super.handGenSql(dto);
    }

    @Override
    protected Result genSqlInvoke(SupplierBankRelationDTO dto) {
        StringBuffer buffer = getBuffer();
        buffer.append(MessageFormat.format(SqlContentTemplate.INSERT_BASE_UP_DATA, getBackUpTableName(),
                getBindTableName(), dto.getId().toString())).append(";\n");
        if (dto.getType().equals(DELETE_SUPPLIER_BANK.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.DELETE_DATA_BY_FIELD, getBindTableName(),
                    "id", dto.getId().toString())).append(";\n");
        } else if (UPDATE_BANK_ACCOUNT.getType().equals(dto.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.UPDATE_SIMPLE_FIELD, getBindTableName(), "bank_account", "'"+dto.getBankAccount()+"'",
                    "id", dto.getId()));
        }
        return Result.getSuccessRe(buffer.toString());
    }
}
