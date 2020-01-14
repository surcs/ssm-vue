<template>
  <el-table
    :data="tableData.filter(data => !search || data.id == search)"
    style="width: 100%" height="800">
      <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
          <el-form-item label="日期">
            <span>{{ props.row.date }}</span>
          </el-form-item>
          <el-form-item label="地址">
            <span>{{ props.row.address }}</span>
          </el-form-item>
          <el-form-item label="状态">
            <span>{{ props.row.state }}</span>
          </el-form-item>
          <el-form-item label="总价">
            <span>{{ props.row.sum }}</span>
          </el-form-item>
          <el-form-item label="客户email">
            <span>{{ props.row.clientEmail }}</span>
          </el-form-item>
          <el-form-item label="销售员ID">
            <span>{{ props.row.salesmanId }}</span>
          </el-form-item>

          <el-form-item label="商品列表">
                <el-table
                    style="width: 100%"
                    :data="props.row.goodsList"
                    max-height="250">
                    
                    <el-table-column
                    fixed
                    prop="goodsId"
                    label="商品Id"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="goodsName"
                    label="商品名称"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    prop="price"
                    label="价格"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    prop="total"
                    label="总量"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    prop="paid"
                    label="已付款数量"
                    width="120">
                    </el-table-column>
                    <el-table-column
                    prop="send"
                    label="已发货数量"
                    width="120">
                    </el-table-column>
                </el-table>

          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="Id"
      prop="id">
    </el-table-column>
    <el-table-column
      label="日期"
      prop="date">
    </el-table-column>
        <el-table-column
      label="状态"
      prop="state">
    </el-table-column>
    <el-table-column
      label="客户"
      prop="clientEmail">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          style="width:100px"
          placeholder="输入ID搜索"/>
          &nbsp
        <el-button
          size="mini"
          type="success"
          round
          @click="insert()">New</el-button>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
      created() {
        var v = this;
        this.$http.post('/order/selectAll')
         .then(function(respond){
           for(var item in respond.data){
             respond.data[item].date = v.$dateFormat(respond.data[item].date);
           }
            v.tableData = respond.data;
        })
      },
    data() {
      return {
        tableData: [], 
        search: ''
      }
    },
    methods: {
      
      insert(){
          this.$router.push('/main/salesKeeper/insertOrder');
      },
      handleEdit(index, row) {
        if(row.state == "CLOSE" || row.state == "RUN"){
             this.$Notice.error({
                            title: 'ERROR',
                            desc: '该订单已无法更改'
                });
        }
        else{
            this.$router.push({
             path: '/main/salesKeeper/updateOrder',
              query: {row}
         })
        }
      },
      handleDelete(index, row) {
        var v = this;
        this.$confirm('是否确认删除该合同', '警告', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(() => {
            const params = new URLSearchParams();
            params.append('id', row.id);
            this.$http.post('/order/delete',
                        params
                    ).then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '删除成功'
                            });
                    v.$router.replace({path: '/refresh'});
                }
                else{
                  v.$Notice.error({
                            title: 'ERROR',
                            desc: '该订单已无法更改'
                });
                }
            });
          })
          .catch(function(error){
                        v.$Notice.info({
                            title: 'INFO',
                            desc: '取消删除'
                });
            });
      }
    },
  }
</script>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>