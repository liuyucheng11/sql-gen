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
                <el-input placeholder="请输入合同编号，多个合同英文逗号隔开" v-model="formData.code"></el-input>
              </el-col>
            </el-row>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="6">
              <el-date-picker placeholder="选择结束时间" type="date" v-model="formData.endDate"
                              v-if="selectType===1"></el-date-picker>
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
        name: "index",
        id: 0,
        data() {
            return {
                result: '',
                isTrue: "是",
                isFalse: "否",
                selectType: null,
                options: [
                    {
                        value: 1,
                        label: '修改合同结束日期'
                    }
                ],
                supplierStatus: [],
                formData: {
                    sqlCode: 4,
                    sqlType: null,
                    code: '',
                    endDate: null,

                },

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
