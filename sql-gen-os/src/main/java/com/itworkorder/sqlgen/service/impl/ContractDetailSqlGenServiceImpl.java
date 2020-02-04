package com.itworkorder.sqlgen.service.impl;

import com.itworkorder.sqlgen.common.Result;
import com.itworkorder.sqlgen.common.SqlContentTemplate;
import com.itworkorder.sqlgen.common.enums.DatePatternEnum;
import com.itworkorder.sqlgen.common.tables.TableSolution;
import com.itworkorder.sqlgen.pojo.contract.ContractDetailDTO;
import com.itworkorder.sqlgen.service.HandlerType;
import com.itworkorder.sqlgen.util.SafeDateUtil;

import java.text.MessageFormat;

import static com.itworkorder.sqlgen.common.tables.TableNameEnum.T_CONTRACT_DETAIL;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-3  16:28
 * @version 1.0
 */
@HandlerType(table = T_CONTRACT_DETAIL)
public class ContractDetailSqlGenServiceImpl extends AbstractHandSqlGenHandler<ContractDetailDTO> {

    @Override
    protected Result genSqlInvoke(ContractDetailDTO dto) {
        StringBuffer buffer = getBuffer();
        if (dto.getSqlType().equals(TableSolution.UPDATE_CONTRACT_END_DATE.getType())) {
            String[] codes = dto.getCode().split(",");
            StringBuffer setBf = new StringBuffer();
            StringBuffer conBf = new StringBuffer();
            setBf.append("end_date = ").append("'").append(SafeDateUtil.format(dto.getEndDate(), DatePatternEnum.YYYY_MM_DD)).append("'");
            conBf.append("code in ( ");
            for (int i = 0; i < codes.length; i++) {
                conBf.append("'").append(codes[i]).append("'");
                if (i < codes.length - 1) {
                    conBf.append(",");
                }
            }
            conBf.append(")").append(";");
            String insert = MessageFormat.format(SqlContentTemplate.GENERAL_INSERT,getBackUpTableName(),T_CONTRACT_DETAIL.getTableName(),conBf.toString());
            String re = MessageFormat.format(SqlContentTemplate.GENERAL_UPDATE, T_CONTRACT_DETAIL.getTableName(), setBf.toString(), conBf.toString());
            buffer.append(insert).append("\n");
            buffer.append(re);
        }
        return Result.getSuccessRe(buffer.toString());
    }

    @Override
    protected void beforeExecute() {
        this.setBindTableName(T_CONTRACT_DETAIL.getTableName());
        super.beforeExecute();
    }
}
