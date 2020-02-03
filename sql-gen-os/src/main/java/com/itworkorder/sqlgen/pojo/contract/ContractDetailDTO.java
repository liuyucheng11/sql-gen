package com.itworkorder.sqlgen.pojo.contract;

import com.itworkorder.sqlgen.pojo.annotation.TableField;
import com.itworkorder.sqlgen.pojo.annotation.TableId;
import com.itworkorder.sqlgen.pojo.supplier.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:采购合同
 *
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-3  13:55
 * @version 1.0
 */
public class ContractDetailDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    //id
    @TableId
    private Long id;


    //编号
    @TableField(value = "code")
    private String code;


    //合同名称
    @TableField(value = "name")
    private String name;


    //合同类型id
    private Integer contractTypeId;


    //采购计划id
    private Integer purchaseId;


    //是否补充合同
    private Integer addedType;


    //被补充合同
    private String addedContract;


    //是否框架协议类合同下的子合同
    private Integer frameworkType;


    //所属框架协议类合同
    private String frameworkContract;


    //是否续签合同
    private Integer renewType;


    //原合同
    private String renewContract;


    //是否其他类型关联合同
    private Integer otherType;


    //其他关联合同
    private String otherContract;


    //是否标准合同
    private Integer normalType;


    //法务部审核人
    private Integer contractAdministratorId;


    //财务关联说明
    private String financialRemarks;


    //合同分类
    private Integer type;


    //对方签约主体
    private String otherContractSubjectIds;


    //我司签约主体
    private Integer ourContractSubjectId;


    //预计合同生效日期
    private Date preEffectiveDate;


    //合同结束日期是否确定
    private Integer endDateType;


    //合同结束日期
    @TableField(value = "end_date")
    private Date endDate;


    //合同重要条款备注
    private String constractTermsRemarks;


    //合作事项
    private String matters;


    //合同附件
    private String appendicesUrl;


    //比价单附件
    private String pricelistUrl;


    //盖章合同附件
    private String sealUrl;


    //单价(含税价)
    private BigDecimal taxUnitPrice;


    //单价(不含税价)
    private BigDecimal untaxUnitPrice;


    //数量
    private Integer num;


    //合同含税价格
    private BigDecimal taxAllPrice;


    //最后一次付款价格
    private BigDecimal preLastPrice;


    //税率
    private Double taxRate;


    //发票类型(1、其他 0、专票)
    private Integer invoiceType;


    //发票开具时间（1、付款前 2、付款后五日内）
    private Integer invoiceTime;


    //是否分期付款（0 否 1是）
    private Integer installPayType;


    //金额备注
    private String amountRemarks;


    //审批进度
    private Integer approvalProgress;


    //状态(0 无效, 1 待审核, 2 审核中, 3 有效, 4 终止)
    private Integer status;


    //创建人ID
    private Integer createUserId;


    //新建人
    private String createUser;


    //新建时间
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    //修改人id
    private Integer updateUserId;


    //修改人
    private String updateUser;


    //修改时间
    private Date updateTime;


    //部门
    private String deparment;


    //备注
    private String remarks;


    //押金
    private BigDecimal foregift;


    //押金到账日期
    private Date foregiftDate;


    //免租起始日期
    private Date freeStartDate;


    //免租结束日期
    private Date freeEndDate;


    //是否有装修质保金(0：有  1：没有)
    private Integer warrantyMoneyType;


    //上线前已付
    private BigDecimal beforePayPrice;


    //部门名称
    private String departmentName;


    //部门主键
    private String departmentId;


    //收款方
    private String payee;


    //合同生效日期
    private Date contractEffectiveDate;


    //是否有摊销变更(0:否，1:是)
    private Integer isshare;


    //合同是否变更(0:否 1:是)
    private Integer ischange;


    //合同期数
    private Integer contractIssue;


    //变更生效日期
    private Date changeEffictDate;


    //租赁总金额（含税）
    private BigDecimal leaseTotalMoney;


    //物业费总金额（含税）
    private BigDecimal propertyTotalMoney;

    /**
     * 采购申请主键ID,多个以","分割
     */
    private String applicationIds;

    //币种
    private String currencyType;

    /**
     * 付款周期(字典表)
     */
    private String payCycle;

    /**
     * 付款方式(字典表)
     */
    private String payType;

    //原币金额
    private BigDecimal currencyPrice;

    /**
     * 来源类型(1:框架决议;2:普通决议;3:招标决议;4:框架招标决议)
     */
    private String originType;

    /**
     * 上线前补录(0:否;1:是)
     */
    private Integer onlineMend;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Integer contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getAddedType() {
        return addedType;
    }

    public void setAddedType(Integer addedType) {
        this.addedType = addedType;
    }

    public String getAddedContract() {
        return addedContract;
    }

    public void setAddedContract(String addedContract) {
        this.addedContract = addedContract;
    }

    public Integer getFrameworkType() {
        return frameworkType;
    }

    public void setFrameworkType(Integer frameworkType) {
        this.frameworkType = frameworkType;
    }

    public String getFrameworkContract() {
        return frameworkContract;
    }

    public void setFrameworkContract(String frameworkContract) {
        this.frameworkContract = frameworkContract;
    }

    public Integer getRenewType() {
        return renewType;
    }

    public void setRenewType(Integer renewType) {
        this.renewType = renewType;
    }

    public String getRenewContract() {
        return renewContract;
    }

    public void setRenewContract(String renewContract) {
        this.renewContract = renewContract;
    }

    public Integer getOtherType() {
        return otherType;
    }

    public void setOtherType(Integer otherType) {
        this.otherType = otherType;
    }

    public String getOtherContract() {
        return otherContract;
    }

    public void setOtherContract(String otherContract) {
        this.otherContract = otherContract;
    }

    public Integer getNormalType() {
        return normalType;
    }

    public void setNormalType(Integer normalType) {
        this.normalType = normalType;
    }

    public Integer getContractAdministratorId() {
        return contractAdministratorId;
    }

    public void setContractAdministratorId(Integer contractAdministratorId) {
        this.contractAdministratorId = contractAdministratorId;
    }

    public String getFinancialRemarks() {
        return financialRemarks;
    }

    public void setFinancialRemarks(String financialRemarks) {
        this.financialRemarks = financialRemarks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOtherContractSubjectIds() {
        return otherContractSubjectIds;
    }

    public void setOtherContractSubjectIds(String otherContractSubjectIds) {
        this.otherContractSubjectIds = otherContractSubjectIds;
    }

    public Integer getOurContractSubjectId() {
        return ourContractSubjectId;
    }

    public void setOurContractSubjectId(Integer ourContractSubjectId) {
        this.ourContractSubjectId = ourContractSubjectId;
    }

    public Date getPreEffectiveDate() {
        return preEffectiveDate;
    }

    public void setPreEffectiveDate(Date preEffectiveDate) {
        this.preEffectiveDate = preEffectiveDate;
    }

    public Integer getEndDateType() {
        return endDateType;
    }

    public void setEndDateType(Integer endDateType) {
        this.endDateType = endDateType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getConstractTermsRemarks() {
        return constractTermsRemarks;
    }

    public void setConstractTermsRemarks(String constractTermsRemarks) {
        this.constractTermsRemarks = constractTermsRemarks;
    }

    public String getMatters() {
        return matters;
    }

    public void setMatters(String matters) {
        this.matters = matters;
    }

    public String getAppendicesUrl() {
        return appendicesUrl;
    }

    public void setAppendicesUrl(String appendicesUrl) {
        this.appendicesUrl = appendicesUrl;
    }

    public String getPricelistUrl() {
        return pricelistUrl;
    }

    public void setPricelistUrl(String pricelistUrl) {
        this.pricelistUrl = pricelistUrl;
    }

    public String getSealUrl() {
        return sealUrl;
    }

    public void setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
    }

    public BigDecimal getTaxUnitPrice() {
        return taxUnitPrice;
    }

    public void setTaxUnitPrice(BigDecimal taxUnitPrice) {
        this.taxUnitPrice = taxUnitPrice;
    }

    public BigDecimal getUntaxUnitPrice() {
        return untaxUnitPrice;
    }

    public void setUntaxUnitPrice(BigDecimal untaxUnitPrice) {
        this.untaxUnitPrice = untaxUnitPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getTaxAllPrice() {
        return taxAllPrice;
    }

    public void setTaxAllPrice(BigDecimal taxAllPrice) {
        this.taxAllPrice = taxAllPrice;
    }

    public BigDecimal getPreLastPrice() {
        return preLastPrice;
    }

    public void setPreLastPrice(BigDecimal preLastPrice) {
        this.preLastPrice = preLastPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Integer invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public Integer getInstallPayType() {
        return installPayType;
    }

    public void setInstallPayType(Integer installPayType) {
        this.installPayType = installPayType;
    }

    public String getAmountRemarks() {
        return amountRemarks;
    }

    public void setAmountRemarks(String amountRemarks) {
        this.amountRemarks = amountRemarks;
    }

    public Integer getApprovalProgress() {
        return approvalProgress;
    }

    public void setApprovalProgress(Integer approvalProgress) {
        this.approvalProgress = approvalProgress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getForegift() {
        return foregift;
    }

    public void setForegift(BigDecimal foregift) {
        this.foregift = foregift;
    }

    public Date getForegiftDate() {
        return foregiftDate;
    }

    public void setForegiftDate(Date foregiftDate) {
        this.foregiftDate = foregiftDate;
    }

    public Date getFreeStartDate() {
        return freeStartDate;
    }

    public void setFreeStartDate(Date freeStartDate) {
        this.freeStartDate = freeStartDate;
    }

    public Date getFreeEndDate() {
        return freeEndDate;
    }

    public void setFreeEndDate(Date freeEndDate) {
        this.freeEndDate = freeEndDate;
    }

    public Integer getWarrantyMoneyType() {
        return warrantyMoneyType;
    }

    public void setWarrantyMoneyType(Integer warrantyMoneyType) {
        this.warrantyMoneyType = warrantyMoneyType;
    }

    public BigDecimal getBeforePayPrice() {
        return beforePayPrice;
    }

    public void setBeforePayPrice(BigDecimal beforePayPrice) {
        this.beforePayPrice = beforePayPrice;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Date getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    public void setContractEffectiveDate(Date contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
    }

    public Integer getIsshare() {
        return isshare;
    }

    public void setIsshare(Integer isshare) {
        this.isshare = isshare;
    }

    public Integer getIschange() {
        return ischange;
    }

    public void setIschange(Integer ischange) {
        this.ischange = ischange;
    }

    public Integer getContractIssue() {
        return contractIssue;
    }

    public void setContractIssue(Integer contractIssue) {
        this.contractIssue = contractIssue;
    }

    public Date getChangeEffictDate() {
        return changeEffictDate;
    }

    public void setChangeEffictDate(Date changeEffictDate) {
        this.changeEffictDate = changeEffictDate;
    }

    public BigDecimal getLeaseTotalMoney() {
        return leaseTotalMoney;
    }

    public void setLeaseTotalMoney(BigDecimal leaseTotalMoney) {
        this.leaseTotalMoney = leaseTotalMoney;
    }

    public BigDecimal getPropertyTotalMoney() {
        return propertyTotalMoney;
    }

    public void setPropertyTotalMoney(BigDecimal propertyTotalMoney) {
        this.propertyTotalMoney = propertyTotalMoney;
    }

    public String getApplicationIds() {
        return applicationIds;
    }

    public void setApplicationIds(String applicationIds) {
        this.applicationIds = applicationIds;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getPayCycle() {
        return payCycle;
    }

    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(BigDecimal currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public Integer getOnlineMend() {
        return onlineMend;
    }

    public void setOnlineMend(Integer onlineMend) {
        this.onlineMend = onlineMend;
    }
}
