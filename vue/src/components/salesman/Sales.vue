<template>
<el-form ref="formInline" :inline="true" :model="formInline" :rules="ruleValidate" class="demo-form-inline">
    <el-card class="box-card" shadow="hover">

      <el-form-item label="起始日期"  prop="start">
          <el-date-picker
            v-model="formInline.start"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="截止日期"  prop="end">
          <el-date-picker
            v-model="formInline.end"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
        <el-form-item label="销售额">
            <el-input
            placeholder="0" 
            v-model="formInline.sum"
            style="width: 100px"
            :disabled="true">
            </el-input>

        </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('formInline')">查询</el-button>
        </el-form-item>   

    </el-card>
                                                                                    
</el-form>
</template>

<script>
  export default {
    data() {
      return {
        formInline: {
          start: '',
          end: '',
          sum: 0
        },
        ruleValidate: {
            start: [
                { required: true, message: '请选择日期', trigger: 'change' }
            ],
            end: [
                { required: true, message: '请选择日期', trigger: 'change' }
            ]
        }
      }
    },
    methods: {
      onSubmit(name) {
          var v = this;
          this.$refs[name].validate((valid) =>  {
                if (valid) {
                    const params = new URLSearchParams();
                    params.append('start', this.formInline.start);
                    params.append('end', this.formInline.end);
                    this.$http.post('/clientPurchaseOrder/countSalesBySalesmanId',
                        params
                    )
                    .then(function(respond){
                       if(respond.data >= 0){
                            v.formInline.sum = respond.data;
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '计算成功'
                            });
                       }
                       else{
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '您不属于销售人员'
                        });
                       }
                    })
                    .catch(function(error){
                        v.$Notice.error({
                            title: 'ERROR',
                            desc: error
                        });
                    });
                } 
                else {
                     v.$Notice.error({
                            title: 'ERROR',
                            desc: '验证失败'
                        });
                }
          }
      );
    }
  }
}
</script>

<style>
    
</style>
