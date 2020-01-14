<template>
    <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80" :style="{height:'100%'}">
        <Form-item label="邮箱" prop="mail">
            <Input style="width: 300px" v-model="formValidate.mail" placeholder="请输入邮箱"></Input>
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
                    <Form-item prop="date">
                        <Date-picker type="date" placeholder="选择日期" v-model="formValidate.date"></Date-picker>
                    </Form-item>
                </Col>
            </Row>
        </Form-item>
        <Form-item label="性别" prop="gender">
            <Radio-group v-model="formValidate.gender">
                <Radio label="MALE">男</Radio>
                <Radio label="FEMALE">女</Radio>
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
        data () {
            return {
                formValidate: {
                    name: '',
                    mail: '',
                    gender: '',
                    date: '',
                    phone: ''
                },
                ruleValidate: {
                    name: [
                        { required: true, message: '姓名不能为空', trigger: 'blur' }
                    ],
                    mail: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                     phone: [
                        { required: true, message: '电话号码不能为空', trigger: 'blur' },
                        { type: 'integer', message: '电话号码不正确', trigger: 'blur' , transform: value => +value}
                    ],
                    gender: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    date: [
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
                    this.$http.post('/client/insert',
                        {
                            email: this.formValidate.mail,
                            name: this.formValidate.name,
                            sex: this.formValidate.gender,
                            birth: this.formValidate.date,
                            phone: this.formValidate.phone
                        }
                    )
                    .then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '新增客户成功'
                            });
                            v.$router.push("/main/salesKeeper/allClient");
                       }
                       else{
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '邮箱已存在'
                        });
                       }
                    })
                    .catch(function(error){
                        v.$Notice.error({
                            title: 'ERROR',
                            desc: '邮箱已存在'
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