package com.itworkorder.sqlgen.service.impl;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.SqlContentTemplate;
import com.itworkorder.sqlgen.common.enums.DatePatternEnum;
import com.itworkorder.sqlgen.pojo.supplier.SupplierDTO;
import com.itworkorder.sqlgen.service.HandlerType;
import com.itworkorder.sqlgen.util.SafeDateUtil;

import java.text.MessageFormat;

import static com.itworkorder.sqlgen.common.tables.TableNameEnum.T_SUPPLIER;
import static com.itworkorder.sqlgen.common.tables.TableSolution.*;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-3  14:03
 * @version 1.0
 */

@HandlerType(table = T_SUPPLIER)
public class SupplierSqlGenServiceImpl extends AbstractHandSqlGenHandler<SupplierDTO> {
    private static final String NO = "supplier_no";

    @Override
    public void beforeExecute() {
        this.setBindTableName(T_SUPPLIER.getTableName());
        super.beforeExecute();
    }

    @Override
    public Result handGenSql(SupplierDTO dto) {
        return super.handGenSql(dto);
    }

    @Override
    protected Result genSqlInvoke(SupplierDTO dto) {
        StringBuffer buffer = getBuffer();
        String tableName = T_SUPPLIER.getTableName();
        buffer.append(MessageFormat.format(SqlContentTemplate.INSERT_BACKUP_DATA, getBackUpTableName(),
                tableName, tableName, NO, "'" + dto.getSupplierNo() + "'")).append("; \n");
        if (dto.getType().equals(UPDATE_SUPPLIER_AU.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.UPDATE_SUPPLIER_AUTH,
                    dto.getAuthenticationStatus(), NO, "'" + dto.getSupplierNo() + "'"));
        } else if (dto.getType().equals(UPDATE_SUPPLIER_NAME.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.UPDATE_SUPPLIER_NAME,
                    "'" + dto.getSupplierName() + "'", NO, "'" + dto.getSupplierNo() + "'"));
        } else if (dto.getType().equals(UPDATE_SUPPLIER_EFFECTIVE_DATE.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.UPDATE_SUPPLIER_EFFECTIVE_DATE,
                    SafeDateUtil.format(dto.getEffeTime(), DatePatternEnum.YYYY_MM_DD), dto.getSupplierNo()));
        } else if (dto.getType().equals(UPDATE_SUPPLIER_ALL_SUPPLIER_STORE.getType())) {
            buffer.append(MessageFormat.format(SqlContentTemplate.UPDATE_SIMPLE_FIELD, getBindTableName(),
                    "all_supply_store", dto.getAllSupplyStore(), NO, "'" + dto.getSupplierNo() + "'"));
        }
        return Result.getSuccessRe(buffer.toString());
    }
}
