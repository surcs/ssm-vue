<template>
    <div id="body">
        <Card id="box" style="width:320px">
        <div style="text-align:center">
            <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
                <FormItem prop="user">
                    <Input type="text" v-model="formInline.user" placeholder="Username">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                    </Input>
                </FormItem>
                <FormItem prop="password">
                    <Input type="password" v-model="formInline.password" placeholder="Password">
                        <Icon type="ios-lock-outline" slot="prepend"></Icon>
                    </Input>
                </FormItem>
                <FormItem>
                    <Button id="button" type="primary" @click="handleSubmit('formInline')">Sign in</Button>
                </FormItem>
            </Form>
        </div>
    </Card>
    </div>
    
</template>

<script>
export default {
    data () {
        return {
            formInline: {
                user: '',
                password: ''
            },
            ruleInline: {
                user: [
                    { required: true, message: '员工ID不能为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' },
                    { type: 'string', min: 6, message: '密码最少六位', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        handleSubmit(name) {
            var v = this;
            this.$refs[name].validate((valid) => {
                if (valid) {
                    this.$http.post('/user/login',
                        {
                            username: this.formInline.user,
                            password: this.formInline.password
                        }
                    )
                    .then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '登录成功'
                            });
                            v.$router.push("/main");
                       }
                       else{
                           v.$Notice.error({
                            title: 'ERROR',
                            desc: '密码或用户名错误'
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
            })
        }
    }
}
</script>

<style scoped>
#box{
    text-align: center;
    background-color: #fff;
    border-radius: 20px;
    width: 300px;
    height: 170px;
    position: absolute;
    left: 50%;
    top: 40%;
    transform: translate(-50%,-50%);
}
#button{
    background: #44A08D;
}
#body{
    background: #44A08D;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #093637, #44A08D);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #093637, #44A08D); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    width: 100%;
    height: 100%;    
    position: absolute;
}
</style>
