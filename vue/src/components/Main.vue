<style scoped>
.layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    height: 100%;
}
.layout-logo{
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}
.layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
}
.layout-footer-center{
    text-align: center;
}
</style>
<template>
    <div class="layout">
        <Layout :style="{height:'100%'}">
            <Header>
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <MenuItem name="1">
                            <span @click="logout()">登出</span>
                        </MenuItem>
                    </div>
                </Menu>
            </Header>
            <Layout :style="{padding: '0 50px'}">
                <Breadcrumb :style="{margin: '16px 0'}">
                    <BreadcrumbItem>Home</BreadcrumbItem>
                    <BreadcrumbItem>Components</BreadcrumbItem>
                    <BreadcrumbItem>Layout</BreadcrumbItem>
                </Breadcrumb>
                <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
                    <Layout>
                        <Sider hide-trigger :style="{background: '#fff'}">
                            <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                                <Submenu name="1">
                                    <template slot="title">
                                        <Icon type="ios-navigate"></Icon>
                                        销售管理员
                                    </template>
                                    <MenuItem name="1-1" to="/main/salesKeeper/allOrder">合同管理</MenuItem>
                                    <MenuItem name="1-2" to="/main/salesKeeper/allClient">客户管理</MenuItem>
                                    <MenuItem name="1-3" to="/main/salesKeeper/dispatchOrder">确认发货单</MenuItem>
                                    <MenuItem name="1-4" to="/main/salesKeeper/sales">统计销售额</MenuItem>
                                    <MenuItem name="1-5" to="/main/salesKeeper/allSalesman">销售人员管理</MenuItem>
                                </Submenu>
                                <Submenu name="2">
                                    <template slot="title">
                                        <Icon type="ios-keypad"></Icon>
                                        仓库管理员
                                    </template>
                                    <MenuItem name="2-1" to="/main/warehouseKeeper/dispatchOrder">确认发货单</MenuItem>
                                    <MenuItem name="2-2" to="/main/warehouseKeeper/purchaseOrder">确认进货单</MenuItem>
                                    <MenuItem name="2-3" to="/main/warehouseKeeper/PurchaseOrderClose">确认进货</MenuItem>
                                </Submenu>
                                <Submenu name="3">
                                    <template slot="title">
                                        <Icon type="ios-analytics"></Icon>
                                        销售人员
                                    </template>
                                    <MenuItem name="3-1" to="/main/salesman/order">查看签订合同</MenuItem>
                                    <MenuItem name="3-2" to="/main/salesman/sales">查看业绩</MenuItem>
                                </Submenu>
                            </Menu>
                        </Sider>
                        <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                            <router-view></router-view>
                        </Content>
                    </Layout>
                </Content>
            </Layout>
            <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
        </Layout>
    </div>
</div>
</template>
<script>
    export default {
        methods: {
            logout(){
                var v = this;
                this.$http.post('/user/logout')
                .then(function(){
                    v.$router.push('/');
                })
                .catch(function(error){
                    console.log(error);
            })
            }
        }
    }
</script>
