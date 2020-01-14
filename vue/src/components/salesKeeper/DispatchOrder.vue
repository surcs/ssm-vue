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
          <el-form-item label="订单id">
            <span>{{ props.row.orderId }}</span>
          </el-form-item>
          <el-form-item label="商品id">
            <span>{{ props.row.goodsId }}</span>
          </el-form-item>
          <el-form-item label="日期">
            <span>{{ props.row.date }}</span>
          </el-form-item>
          <el-form-item label="地址">
            <span>{{ props.row.address }}</span>
          </el-form-item>
          <el-form-item label="采购单号">
            <span>{{ props.row.clientPurchaseOrderId }}</span>
          </el-form-item>
          <el-form-item label="状态">
            <span>{{ props.row.state }}</span>
          </el-form-item>
          <el-form-item label="数量">
            <span>{{ props.row.number }}</span>
          </el-form-item>
          <el-form-item label="总价">
            <span>{{ props.row.sum }}</span>
          </el-form-item>

        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="Id"
      prop="id">
    </el-table-column>
    <el-table-column
      label="商品id"
      prop="goodsId">
    </el-table-column>
    <el-table-column
      label="订单id"
      prop="orderId">
    </el-table-column>
    <el-table-column
      label="日期"
      prop="date">
    </el-table-column>
    <el-table-column
      align="left">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          style="width:200px"
          placeholder="输入ID搜索"/>
      </template>
    </el-table-column>
    <el-table-column
      align="right">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleConfirm(scope.$index, scope.row)">Confirm</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
      created() {
        var v = this;
        this.$http.post('/dispatchOrder/selectSalesKeeperUncheck')
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
      handleConfirm(index, row) {
        var v = this;
        this.$confirm('是否确认', '警告', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(() => {
            this.$http.post('/dispatchOrder/updateDispatchOrderSalesKeeper',
                        {
                            id: row.id,
                            goodsId: row.goodsId,
                            salesmanId: row.salesmanId,
                            state: row.state,
                            date: row.date,
                            address: row.address,
                            sum: row.sum,
                            number: row.number,
                            salesKeeperId:'',
                            warehouseKeeperId:'',
                            logisticsId: '',
                            orderId: row.orderId
                        }
                    ).then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '操作执行成功'
                            });
                    v.$router.replace({path: '/refresh'});
                }
                else if(respond.data == 0){
                            v.$Notice.error({
                                title: 'ERROR',
                                desc: '商品库存不足 正在进货...'
                            });
                }
                else{
                  v.$Notice.error({
                            title: 'ERROR',
                            desc: '操作执行失败'
                });
                }
            });
          })
          .catch(function(error){
                        v.$Notice.info({
                            title: 'INFO',
                            desc: '取消操作'
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