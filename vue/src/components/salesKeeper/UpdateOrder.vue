<template>
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" :style="{height:'100%'}">
        <Form-item label="订单id" prop="id">
            <Input style="width: 300px" v-model="formValidate.id" :disabled="true"></Input>
        </Form-item>
        <Form-item label="邮箱" prop="clientEmail">
            <Input style="width: 300px" v-model="formValidate.clientEmail" placeholder="请输入客户邮箱"></Input>
        </Form-item>
        <Form-item label="销售员id" prop="salesmanId">
            <Input style="width: 300px" v-model="formValidate.salesmanId" placeholder="请输入销售员ID"></Input>
        </Form-item>
        <Form-item label="地址" prop="address">
            <Input style="width: 300px" v-model="formValidate.address" placeholder="请输入客户地址"></Input>
        </Form-item>
        <Form-item label="日期">
            <Row>
                <Col span="11">
                    <Form-item prop="date">
                        <Date-picker type="date" placeholder="选择日期" v-model="formValidate.date"></Date-picker>
                    </Form-item>
                </Col>
            </Row>
        </Form-item>
        <Form-item label="合同状态" prop="state">
            <Radio-group v-model="formValidate.state">
                <Radio label="UNPAID">UNPAID</Radio>
                <Radio label="RUN">RUN</Radio>
                <Radio label="CLOSE">CLOSE</Radio>
            </Radio-group>
        </Form-item>
        <Form-item prop="goodsList">
              
            <el-table
                :data="this.formValidate.goodsList"
                height="150"
                border
                style="width: 100%">

            <template>

                <el-table-column
                prop="goodsId"
                label="商品id"
                width="180">
                </el-table-column>
                <el-table-column
                prop="goodsName"
                label="商品名"
                width="180">
                </el-table-column>
                <el-table-column
                prop="inventory"
                label="库存">
                </el-table-column>
                <el-table-column
                prop="price"
                label="价格">
                </el-table-column>
                <el-table-column
                prop="total"
                label="购买数量">
                <template slot-scope="{row}">
                    <el-input-number :min="0" size="mini" v-model="row.total"></el-input-number>
                </template>
                </el-table-column>

            </template>

            </el-table>
  
        </Form-item> 
        <Form-item label="总价" prop="sum" >
                    <Input style="width: 100px" v-model="formValidate.sum" placeholder="0" 
                    :disabled="true">
                    </Input>
        </Form-item>  
        
        <Form-item>
            <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
        </Form-item>
        
    </Form>
</template>
<script>
    export default{
        created() {
                this.formValidate = this.$route.query.row;
                var v = this;
                this.$http.post('/goods/selectAll')
                .then(function(respond){
                    v.formValidate.goodsList = respond.data;
                })
        },
        data () {
            return {
                formValidate: {
                    id: '',
                    clientEmail: '',
                    salesmanId: '',
                    state: '',
                    date: '',
                    address: '',
                    sum: 0,
                    goodsList: []
                },
                ruleValidate: {
                    address: [
                        { required: true, message: '地址不能为空', trigger: 'blur' }
                    ],
                    clientEmail: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                     salesmanId: [
                        { required: true, message: '销售员ID不能为空', trigger: 'blur' },
                        { type: 'integer', message: 'ID应全为数字', trigger: 'blur' , transform: value => +value}
                    ],
                    state: [
                        { required: true, message: '请选择订单状态', trigger: 'change' }
                    ],
                    date: [
                        { required: true, type:"date" , message: '请选择日期', trigger: 'change' }
                    ],
                }
            }
        },
        watch: {
            'formValidate.goodsList':{
             handler () {
                this.formValidate.sum = 0;
                for(var item in this.formValidate.goodsList){
                    this.formValidate.sum += this.formValidate.goodsList[item].price * this.formValidate.goodsList[item].total; 
                }
            },
            immediate: true,
            deep: true

          }
        },  
        methods: {
            handleSubmit (name) {
                var v = this;
                this.$refs[name].validate((valid) =>  {
                if (valid) {
                    this.$http.post('/order/update',
                        {
                            id: this.formValidate.id,
                            clientEmail: this.formValidate.clientEmail,
                            salesmanId: this.formValidate.salesmanId,
                            state: this.formValidate.state,
                            date: this.formValidate.date,
                            address: this.formValidate.address,
                            sum: this.formValidate.sum,
                            goodsList: this.formValidate.goodsList
                        }
                    )
                    .then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '订单修改成功成功'
                            });
                            v.$router.push("/main/salesKeeper/allOrder");
                       }
                       else if(respond.data == -1){
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '该客户Email未注册'
                        });
                       }
                        else if(respond.data == -2){
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '无该销售员'
                        }
                        );
                       }
                       else if(respond.data == -3){
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '请至少购买一件商品'
                        }
                        );
                       }
                       else{
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '订单添加失败'
                        }
                        );
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
            })
            }
        }
    }
</script>