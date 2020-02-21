<template>
  <div class="main-container">
    <h4>将API jar包上传生成Excel文档</h4>
    <div>
      <div>
        <el-form ref="form" :rules="rules" :model="form" label-width="90px" class="form-content-box form">
          <el-upload
            ref="upload"
            name="jarFile"
            action=""
            class="upload-box"
            :limit="1"
            :auto-upload="false"
            :http-request="doAnalyse"
            :show-file-list="false"
            :data="paramData"
            :on-exceed="handleExceed"
            :before-upload="handlePreview"
            :on-change="handleFileChange"
            :on-success="handleSuccess"
            :on-error="handleError">
            <span class="filename-show">{{filename || form.attachmentName}}</span>
            <el-button size="small" type="primary">浏览</el-button>
          </el-upload>
          <el-button size="small" type="primary" @click="doUpload">上传</el-button>
          <label class="tip">请上传以下格式的附件:XXXX-SNAPSHOT.jar</label>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
    import {analyseJar} from '@/api/user.js'

    export default {
        name: "index",
        data() {
            return {
                isUploadFileFlag: false,
                fileValide: false,
                paramData: {},
                filename: '',
                form: {
                    attachmentName: ''
                },
                fileTip: '',
                formData: null,


            }
        },
        methods: {
            handleExceed(files) {
                // 防止使用拖拉方式时，拖拉多个文件文件
                if (files.length > 1) {
                    this.$message({
                        showClose: true,
                        type: 'warning',
                        message: '只能单文件上传!请重新选择'
                    })
                }
                // 拖拉多个文件时，不做处理
                if (files.length > 1) {
                    return
                }

                for (var i = 0, len = files.length; i < len; i++) {
                    this.$refs.upload.handleRemove(files[i])
                }
                this.$refs.upload.$children[0].uploadFiles(files)
            },
            handlePreview(file) {
                var result = false
                this.fileValide = result
                var fileSize = file.size
                var fileName = file.name

                var suffix = fileName.split('.')[fileName.split('.').length - 1] // 后缀
                var fileRealName = fileName.substring(fileName.lastIndexOf('\\') + 1, fileName.lastIndexOf('.'))// 文件名
                suffix = suffix.toLowerCase()

                var fmt = ["jar"]

                if (!fileName) {
                    this.fileTip = '上传文件不能为空,请选择后上传！'
                    this.isUploadFileFlag = false
                } else if (!fmt.includes(suffix)) {
                    this.fileTip = '附件格式不正确'
                    this.isUploadFileFlag = false
                } else if (fileRealName.replace('/[^\x00-\xff]/g', '**').length > 10000) { // 检验文件名的长度
                    this.fileTip = '上传文件名过长,请修改后上传'
                    this.isUploadFileFlag = false
                } else if (fileSize > 50 * 1024 * 1024) {
                    this.fileTip = '附件过大，压缩到50M以下'
                    this.isUploadFileFlag = false
                } else {
                    result = true
                    this.fileValide = result
                }
                if (!result) {
                    this.$message.error(this.fileTip);
                }
                // this.$refs['form'].validateField('upload')
                return result
            },
            handleFileChange(file) {
                if (file && file.status === 'ready') {
                    this.isUploadFileFlag = true
                    this.paramData.fileName = file.name
                    this.filename = file.name
                }
            },
            handleSuccess(re) {
                // console.log(re);
                // const content = re;
                // const blog = new Blob([content], {type: 'application/ynd.ms-excel;charset=UTF-8'});
                // let elink = document.createElement('a');
                // elink.download = "接口实体表格.xlsx";
                // elink.href = URL.createObjectURL(blog);
                // elink.click();
            },
            handleError() {

            },
            doAnalyse(file) {
                this.formData = new FormData();
                this.formData.append("jarFile", file.file);
            },
            doUpload() {
                this.$refs.upload.submit();
                analyseJar(this.formData).then(re => {
                    console.log(re);
                    const content = re.data;
                    const blog = new Blob([content], {type: 'application/ynd.ms-excel;charset=UTF-8'});
                    let elink = document.createElement('a');
                    elink.download = "接口实体表格.xlsx";
                    elink.href = URL.createObjectURL(blog);
                    elink.click();
                    this.formData = new FormData();
                }).catch(re => {
                    this.formData = new FormData();
                })
            }
        }
    }

</script>

<style scoped>

  .main-container {
    margin: 0 auto;
  }

  .tip {
    position: relative;
    color: #797979;
    font-size: 14px;
    margin-left: 90px;
    display: inline-block;
  }

  .filename-show {
    height: 32px;
    line-height: 32px;
    width: 300px;
    margin-right: 10px;
    -webkit-appearance: none;
    /* background-color: #fff; */
    background-image: none;
    border-radius: 4px;
    border: 1px solid #DCDFE6;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    outline: none;
    padding: 0 10px;
    transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }


</style>
