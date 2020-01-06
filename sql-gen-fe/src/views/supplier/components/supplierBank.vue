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
        <el-form :model="formData" label-width="80px">
          <el-form-item label="表id">
            <el-col span="7">
              <el-input placeholder="请输入表id" v-model="formData.id"></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="银行账号:" v-if="selectType===2">
            <el-col span="7">
              <el-input placeholder="输入银行账号" v-model="formData.bankAccount"></el-input>
            </el-col>
          </el-form-item>

          <el-row style="margin-top: 20px;margin-left:10%">
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
        name: "supplierBank",
        data() {
            return {
                result: '',
                selectType: '',
                bankAccount: '',
                formData: {
                    id: '',
                    code: 2,
                    type: '',
                },
                options: [
                    {
                        value: 1,
                        label: '删除关联银行'
                    },
                    {
                        value :2 ,
                        label:'根据id修改账号'
                    }

                ]
            }

        },
        watch: {
            selectType() {
                this.formData.type = this.selectType;
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
    margin-top: 30px;
  }

</style>
