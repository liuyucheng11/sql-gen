<template>
  <div>
    <div style="margin-left: 10%;margin-top: 5%">
      <el-select v-model=selectType
                 placeholder="请选择处理工单类型">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div class="order-input-select">
      <div>
        <el-form :model="formData">
          <el-row>
            <el-row>
              <el-col :span="6">
                <el-input placeholder="请输入供应商编号" v-model="formData.supplierNo"></el-input>
              </el-col>
            </el-row>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="6">
              <el-input placeholder="请输入修改后供应商名称" v-model="formData.supplierName" v-if="selectType===3"></el-input>
              <el-date-picker placeholder="选择有效时间" type="date" v-model="formData.effeTime"
                              v-else-if="selectType===2"></el-date-picker>
              <el-select v-else-if="selectType===1" placeholder="选择供应商认证状态" v-model="formData.authenticationStatus">
                <el-option v-for="item in status"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
              <el-select v-else-if="selectType===4" placeholder="是否所有门店" v-model="formData.allSupplyStore">
                <el-option :label="isTrue" :value="1"></el-option>
                <el-option :label="isFalse" :value="0"></el-option>
              </el-select>
            </el-col>

          </el-row>

          <el-row style="margin-top: 20px">
            <el-button @click="submit" type="primary">提交</el-button>
            <el-button @click="clearForm">重置</el-button>
          </el-row>

        </el-form>
      </div>
    </div>

    <div class="deal-result">
      <span>生成结果:</span>
      <el-row>
        <el-col span="18">
          <el-input type="textarea" autosize readOnly="true" v-model="result"></el-input>

        </el-col>

      </el-row>
    </div>
  </div>
</template>

<script>
    import {genSql} from '@/api/user';

    export default {
        name: "supplier",
        id: 0,
        data() {
            return {
                result: '',
                isTrue:"是",
                isFalse:"否",
                selectType: null,
                options: [
                    {
                        value: 1,
                        label: '修改供应商认证状态'
                    }, {
                        value: 2,
                        label: '修改供应商有效时间',
                    },
                    {
                        value: 3,
                        label: '修改供应商名称',
                    }, {
                        value: 4,
                        label: '修改是否所有门店',
                    }
                ],
                supplierStatus: [],
                formData: {
                    sqlCode: 1,
                    sqlType: null,
                    supplierNo: '',
                    effeTime: null,
                    supplierName: '',
                    authenticationStatus: '',
                    allSupplyStore: null
                },
                status: [
                    /**
                     认证状态 0 待认证 1 待补全  2 认证通过 3 永久驳回 4 停用 5 审批中 6 认证中
                     */
                    {
                        value: 0,
                        label: '待认证',

                    }, {
                        value: 1,
                        label: '待补全'
                    },
                    {
                        value: 2,
                        label: '认证通过'
                    }, {
                        value: 3,
                        label: '永久驳回',
                    }, {
                        value: 4,
                        label: '停用',
                    }, {
                        value: 5,
                        label: '审批中',
                    }, {
                        value: 6,
                        label: '认证中'
                    }
                ],
            }
        },
        watch: {
            selectType() {
                this.formData.sqlType = this.selectType;
            }
        },
        methods: {
            submit() {
                genSql(this.formData).then(result => {
                    this.result = result.re;
                })
            },
            clearForm() {

            }
        }
    }
</script>

<style scoped>
  .order-input-select {
    margin-top: 5%;
    padding-left: 10%;
    padding-top: 10px;
    padding-right: 20px;
  }

  .deal-result {
    margin-top: 60px;
    margin-left: 50px;
  }

</style>
