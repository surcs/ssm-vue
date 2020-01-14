<template>
  <el-table
    :data="tableData.filter(data => !search || data.id == search)"
    style="width: 100%" height="800">
     
    </el-table-column>
    <el-table-column
      label="Id"
      prop="id">
    </el-table-column>
    <el-table-column
      label="姓名"
      prop="name">
    </el-table-column>
        <el-table-column
      label="电话"
      prop="phone">
    </el-table-column>
    <el-table-column
      label="邮箱"
      prop="email">
    </el-table-column>
    <el-table-column
      label="性别"
      prop="sex">
    </el-table-column>
    <el-table-column
      label="出生日期"
      prop="birth">
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
        this.$http.post('/salesman/selectAll')
         .then(function(respond){
           for(var item in respond.data){
             respond.data[item].birth = v.$dateFormat(respond.data[item].birth);
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
          this.$router.push('/main/salesKeeper/insertSalesman');
      },
      handleEdit(index, row) {
         this.$router.push({
             path: '/main/salesKeeper/updateSalesman',
              query: {row}
         })
      },
      handleDelete(index, row) {
        var v = this;
        this.$confirm('是否确认删除该销售员', '警告', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(() => {
            const params = new URLSearchParams();
            params.append('id', row.id);
            this.$http.post('/salesman/delete',
                        params
                    ).then(function(respond){
                       if(respond.data == 1){
                            v.$Notice.success({
                                title: 'SUCCESS',
                                desc: '删除成功'
                            });
                    v.$router.replace({path: '/refresh'});
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