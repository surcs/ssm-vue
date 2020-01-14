<template>
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" :style="{height:'100%'}">
        <Form-item label="ID" prop="id">
            <Input style="width: 300px" v-model="formValidate.id" placeholder="请输入员工ID" 
            :disabled="true"></Input>
        </Form-item>
        <FormItem label="密码" prop="password">
            <Input style="width: 300px" type="password" v-model="formValidate.password" placeholder="请输入密码">
            </Input>
        </FormItem>
        <Form-item label="邮箱" prop="email">
            <Input style="width: 300px" v-model="formValidate.email" placeholder="请输入邮箱"></Input>
        </Form-item>
        <Form-item label="姓名" prop="name">
            <Input style="width: 300px" v-model="formValidate.name" placeholder="请输入姓名"></Input>
        </Form-item>
        <Form-item label="电话" prop="phone">
            <Input style="width: 300px" v-model="formValidate.phone" placeholder="请输入电话号码"></Input>
        </Form-item>
        <Form-item label="出生日期">
            <Row>
                <Col span="11">
                    <Form-item prop="birth">
                        <Date-picker type="date" placeholder="选择日期" v-model="formValidate.birth"></Date-picker>
                    </Form-item>
                </Col>
            </Row>
        </Form-item>
        <Form-item label="性别" prop="sex">
            <Radio-group v-model="formValidate.sex">
                <Radio label="MALE">男</Radio>
                <Radio label="FEMALE">女</Radio>
            </Radio-group>
        </Form-item>
        <Form-item label="职位" prop="position">
            <Radio-group v-model="formValidate.position">
                <Radio label="SALESMAN">销售员</Radio>
                <Radio label="SALES_KEEPER">销售管理员</Radio>
                <Radio label="WAREHOUSE_KEEPER">仓库管理员</Radio>
            </Radio-group>
        </Form-item>
        </Form-item>
        <Form-item>
            <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
        </Form-item>
    </Form>
</template>
<script>
    export default {
        created() {
                this.formValidate = this.$route.query.row;
        },
        data () {
            return {
                formValidate: {
                    id:'',
                    name: '',
                    email: '',
                    sex: '',
                    birth: '',
                    phone: '',
                    position:'',
                    password: ''
                },
                ruleValidate: {
                    password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码最少六位', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '姓名不能为空', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                     phone: [
                        { required: true, message: '电话号码不能为空', trigger: 'blur' },
                        { type: 'integer', message: '电话号码不正确', trigger: 'blur' , transform: value => +value}
                    ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    birth: [
                        { required: true, type: 'date', message: '请选择日期', trigger: 'change' }
                    ],
                }
            }
        },
        methods: {
            handleSubmit (name) {
                var v = this;
                this.$refs[name].validate((valid) =>  {
                if (valid) {
                    this.$http.post('/salesman/update',
                        {
                            id: this.formValidate.id,
                            position: this.formValidate.position,
                            email: this.formValidate.email,
                            name: this.formValidate.name,
                            sex: this.formValidate.sex,
                            birth: this.formValidate.birth,
                            password: this.formValidate.password,
                            phone: this.formValidate.phone
                        }
                    )
                    .then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '销售人员信息修改成功'
                            });
                            v.$router.push("/main/salesKeeper/allSalesman");
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