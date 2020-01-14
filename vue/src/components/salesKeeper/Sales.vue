<template>
<div>
<el-form ref="formInline" :inline="true" :model="formInline" :rules="ruleValidate" class="demo-form-inline">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>公司销售总额</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="countAllSales('formInline')">统计</el-button>
        </div>
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
    </el-card>                                                                    
</el-form>

<br>

<el-form ref="formInline1" :inline="true" :model="formInline1" :rules="ruleValidate" class="demo-form-inline">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>客户销售额</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="countSalesByClientId('formInline1')">统计</el-button>
        </div>
      
      <el-form-item label="email"  prop="email">
            <el-input
                placeholder="请输入客户email" 
                v-model="formInline1.email"
                style="width: 244px">
            </el-input>
      </el-form-item>
      <el-form-item label="起始日期"  prop="start">
          <el-date-picker
            v-model="formInline1.start"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="截止日期"  prop="end">
          <el-date-picker
            v-model="formInline1.end"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
        <el-form-item label="销售额">
            <el-input
            placeholder="0" 
            v-model="formInline1.sum"
            style="width: 100px"
            :disabled="true">
            </el-input>
        </el-form-item> 
    </el-card>                                                                    
</el-form>

<br>

<el-form ref="formInline2" :inline="true" :model="formInline2" :rules="ruleValidate" class="demo-form-inline">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>商品销售额</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="countSalesByGoodsId('formInline2')">统计</el-button>
        </div>
      
      <el-form-item label="id"  prop="id">
            <el-input
                placeholder="请输入商品ID" 
                v-model="formInline2.id"
                style="width: 244px">
            </el-input>
      </el-form-item>
      <el-form-item label="起始日期"  prop="start">
          <el-date-picker
            v-model="formInline2.start"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
      <el-form-item label="截止日期"  prop="end">
          <el-date-picker
            v-model="formInline2.end"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
      </el-form-item>
        <el-form-item label="销售额">
            <el-input
            placeholder="0" 
            v-model="formInline2.sum"
            style="width: 100px"
            :disabled="true">
            </el-input>
        </el-form-item> 
    </el-card>                                                                    
</el-form>
</div>
</template>

<script>
  export default {
    data() {
      return {
        formInline: {
          start: '',
          end: '',
          sum: 0},
        formInline1: {
          email:'',
          start: '',
          end: '',
          sum: 0},
        formInline2: {
          id:'',
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
            ],
            id: [
                        { required: true, message: 'id不能为空', trigger: 'blur' },
                        { type: 'integer', message: 'id应为数字', trigger: 'blur' , transform: value => +value}
                    ],
            email: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                ]
        }
      }
    },
    methods: {
      countAllSales(name) {
          var v = this;
          this.$refs[name].validate((valid) =>  {
                if (valid) {
                    const params = new URLSearchParams();
                    params.append('start', this.formInline.start);
                    params.append('end', this.formInline.end);
                    this.$http.post('/clientPurchaseOrder/countAllSales',
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
    },

    countSalesByClientId(name) {
          var v = this;
          this.$refs[name].validate((valid) =>  {
                if (valid) {
                    const params = new URLSearchParams();
                    params.append('id',this.formInline1.email);
                    params.append('start', this.formInline1.start);
                    params.append('end', this.formInline1.end);
                    this.$http.post('/clientPurchaseOrder/countSalesByClientId',
                        params
                    )
                    .then(function(respond){
                       if(respond.data >= 0){
                            v.formInline1.sum = respond.data;
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '计算成功'
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
    },

    countSalesByGoodsId(name) {
          var v = this;
          this.$refs[name].validate((valid) =>  {
                if (valid) {
                    const params = new URLSearchParams();
                    params.append('id',this.formInline2.id);
                    params.append('start', this.formInline2.start);
                    params.append('end', this.formInline2.end);
                    this.$http.post('/clientPurchaseOrder/countSalesByGoodsId',
                        params
                    )
                    .then(function(respond){
                       if(respond.data >= 0){
                            v.formInline2.sum = respond.data;
                            console.log(respond.data);
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '计算成功'
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
