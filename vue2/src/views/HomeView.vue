<template>
  <div style="height: 100%">
    <el-container style="height: 100vh;">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246) ;height:100%;overflow-x:hidden;box-shadow: 2px 0 6px rgba(0,21,41,.35);" >
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x:hidden"
                  background-color="rgb(48,65,86)"
                 text-color="#fff"
                 overflow-x="hidden"
                 active-text-color="#ffd04b"
                 :collapse-transition="false"
                 :collapse="isCollapse"
        >
          <div style="height: 60px;line-height: 60px;text-align: center">
              <img src="../assets/logo.png" alt="" style="width:20px;position:relative;margin-left:5px;margin-right:5px;top:5px ">
            <b style="color: aliceblue" v-show=LogoTextShow>后台管理系统</b>

          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i><span>导航二</span></template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i><span>导航三</span></template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style=" font-size: 12px;border-bottom: 1px solid #ccc;line-height: 60px;display: flex">
          <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer;font-size: 20px" @click="collapse"></span>
          </div>
          <el-dropdown style="width: 70px ;cursor: pointer" >
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 7px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>
          <el-breadcrumb separator="/" class="ml" >
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>

          </el-breadcrumb>
          <div class="ml" >
            <el-input  style="width: 200px"
                       placeholder="请输入名称"
                       suffix-icon="el-icon-search"
                       v-model="username"

            >
            </el-input>
            <el-input  style="width: 200px"
                       placeholder="请输入邮箱"
                       suffix-icon="el-icon-message"
                       class="ml-5"
                       v-model="email"
            >
            </el-input>
            <el-input  style="width: 200px"
                       placeholder="请输入地址"
                       suffix-icon="el-icon-position"
                       class="ml-5"
                       v-model="address"
            >
            </el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
          </div>
          <!-- margin是盒子与盒子间距离 padding是盒子与内部元素之间距离-->
          <div class="ml">
            <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='不用了'
                icon="el-icon-info"
                icon-color="red"
                title="确定批量删除吗？"
                @confirm="delBatch"
            >
            <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
            <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
          </div>
          <el-table :data="tableData" border stripe header-cell-class-name=HeaderBg @selection-change="handleSelectionChange">
            //header-cell-style="{background:'#409EFF',color:'#409EFF'}" 或者直接改表头颜色
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="username" label="用户名" width="140"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="手机号码"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope" >
                <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="=el-icon-edit"></i> </el-button>
                <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='不用了'
                    icon="el-icon-info"
                    icon-color="red"
                    title="确定删除吗？"
                    @confirm="handleDelete(scope.row.id)"
                >
                <el-button type="danger" slot="reference">删除<i class="=el-icon-edit"></i> </el-button>
                </el-popconfirm>

              </template>
            </el-table-column>
          </el-table>
          <div class="pd-10">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page=pageNum
                :page-sizes="[2, 5, 10, 20]"
                :page-size=pageSize
                layout="total, sizes, prev, pager, next, jumper"
                :total=total>
            </el-pagination>
          </div>

          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" size="small">
              <el-form-item label="用户名" >
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
<!--              <el-form-item label="密码" >-->
<!--                <el-input v-model="form.password" autocomplete="off"></el-input>-->
<!--              </el-form-item>-->
              <el-form-item label="昵称" >
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" >
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话" >
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" >
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import request from "@/utils/request";


export default {
  name:"Home",
  data(){
  return {
    tableData: [],
    total:0,
    pageNum:1,
    pageSize:2,
    username:"",
    email:"",
    address:"",
    collapseBtnClass:'el-icon-s-fold',
    isCollapse:false,
    sideWidth:200,
    LogoTextShow:true,
    dialogFormVisible:false,
    form:{},
    multipleSelection:""
  }
  },
  created(){
    this.load()
  },
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass="el-icon-s-unfold"
        this.LogoTextShow=false
      }else { //展开
        this.sideWidth=200
        this.collapseBtnClass="el-icon-s-fold"
        this.LogoTextShow=true
      }
    },
    load() {
      request.get("user/page",{
        params:{
          pageNum: this.pageNum,
          pageSize:this.pageSize,
          username:this.username,
          email:this.email,
          address:this.address,
        }})
      .then(res => {
        console.log(res);
        this.tableData=res.records
        this.total=res.total
      })
      //请求分页查询数据
      // fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username).then(res=>res.json()).then(res=>{
      //   console.log(res);
      //   this.tableData=res.data;
      //   this.total=res.total;
      // })
    },
    save(){
      request.post("user",this.form).then(res=>{
        if (res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })

    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    handleDelete(id){
      request.delete("user/"+id).then(res=>{
        if (res){
          this.$message.success("删除成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch(){
      let ids=this.multipleSelection.map(v=>v.id) //把对象数组变成[1,2,3]
      request.post("user/del/batch",ids).then(res=>{
        if (res){
          this.$message.success("批量删除成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val;
    },
    reset(){
      this.username=""
      this.email=""
      this.address=""
      this.load()
    },
    handleSizeChange(pageSize) {
      //console.log(`每页 ${val} 条`);
      this.pageSize=pageSize;
      this.load()
    },
    handleCurrentChange(pageNum) {
      //console.log(`当前页: ${val}`);
      this.pageNum=pageNum;
      this.load()
    },

    change(e) {
      this.$forceUpdate()
    }
  }
}
</script>

<style>
.HeaderBg{
  background: #f5faf1 !important;
}
</style>
