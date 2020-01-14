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
                </el-table>

                <el-table
                    style="width: 100%"
                    :data="props.row.numberList"
                    max-height="250">
                    
                    <el-table-column
                    fixed
                    prop="0"
                    label="总数量"
                    width="150">
                    </el-table-column>
                    <el-table-column
                    prop="1"
                    label="已交易数量"
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
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
      created() {
        var v = this;
        this.$http.post('/order/selectOrderListBySalesmanId')
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
    }
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