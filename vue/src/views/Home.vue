<template>
  <el-container style="min-height: 100vh;">
    <el-aside width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246);box-shadow: 2px 0 6px rgb(0,21,41)">
      <el-menu :default-openeds="['1', '3']" style="min-height:100%; overflow-x: hidden"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse">
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative;">
          <b style="color: white">Admin Panel</b>
        </div>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>
            <span slot="title">Navigator One</span>
          </template>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="1-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">Option4</template>
            <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>
            <span slot="title">Navigator Two</span>
          </template>
          <el-submenu index="2-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>
            <span slot="title">Navigator Three</span></template>


          <el-submenu index="3-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px;display:flex">
        <div style="flex: 1;font-size: 20px">
          <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
        </div>
        <div style="text-align: right; font-size: 12px; width: 200px">
          <el-dropdown style="width: 70px; cursor: pointer">
            <span>佳怡</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>View</el-dropdown-item>
              <el-dropdown-item>Add</el-dropdown-item>
              <el-dropdown-item>Delete</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <el-main>
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">promotion management</a></el-breadcrumb-item>
          <el-breadcrumb-item>promotion list</el-breadcrumb-item>
          <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="padding: 10px 0">
          <el-input style="width:200px" placeholder="please enter a name" suffix-icon="el-icon-search" v-model="username"></el-input>
          <el-input style="width:200px" placeholder="please enter an email" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>
          <el-input style="width:200px" placeholder="please enter an address" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
          <el-button class="ml-5" type="primary" @click="load">search</el-button>
          <el-button type="warning" @click="load">reset</el-button>
        </div>
        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">New <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-button type="danger">Bulk Delete <i class="el-icon-remove-outline"></i></el-button>
          <el-button type="primary">Import<i class="el-icon-bottom"></i></el-button>
          <el-button type="primary">Export<i class="el-icon-top"></i></el-button>
        </div>
        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="ID" width="80">
          </el-table-column>
          <el-table-column prop="username" label="Username" width="140">
          </el-table-column>
          <el-table-column prop="nickname" label="Nickname" width="120">
          </el-table-column>

          <el-table-column prop="email" label="Email">
          </el-table-column>

          <el-table-column prop="phone" label="Phone">
          </el-table-column>
          <el-table-column prop="address" label="Address">
          </el-table-column>
          <el-table-column label="Operation">
            <template slot-scope="scope">
              <el-button type="success">Edit <i class="el-icon-edit"></i></el-button>
              <el-button type="danger">Delete <i class="el-icon-remove-outline"></i></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage2"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="2"
              layout="total, sizes, prev, pager, next"
          :total="total">
          </el-pagination>
        </div>

        <el-dialog title="user infomation" :visible.sync="dialogFormVisible">
          <el-form label-width="80px" size="small">
            <el-form-item label="username">
              <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="nickname">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="email">
              <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="phone">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="address">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">Cancel</el-button>
    <el-button type="primary" @click="save">Confirm</el-button>
  </span>
        </el-dialog>


      </el-main>
    </el-container>
  </el-container>


</template>
<script>
import request from '../utils/request.js'

export default {
  name: 'Home',
  data() {
    return {
      tableData: [],
      total: 0,
      currentPage2: 1,
      pageSize: 2,
      username: "",
      email:"",
      address:"",
      form:{},
      dialogFormVisible:false,
      msg: "hello 毛毛",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg',
      loading: false
    }
  },
  created() {
    this.load(); // 页面创建时加载数据
  },
  methods: {
    load() {
      this.loading = true;
      request.get("/user/page", {
        params: {
          pageNum: this.currentPage2,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address,
        }
      }).then(res => {
        console.log('数据响应：', res);
        this.tableData = res.data;
        this.total = res.total;
      }).catch(err => {
        console.error('错误：', err);
        this.$message.error('数据加载失败');
      }).finally(() => {
        this.loading = false;
      });
    },
    save(){
      request.post()
    },
    handleAdd(){
      this.dialogFormVisible = true;
      this.form = {}
    },
    reset(){
      this.username=""
      this.email=""
      this.address=""
      this.load()
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.load();
    },
    handleCurrentChange(newPage) {
      this.currentPage2 = newPage;
      this.load();
    },
    collapse() {
      this.isCollapse = !this.isCollapse;
      this.sideWidth = this.isCollapse ? 64 : 200;
      this.collapseBtnClass = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold';
    }
  }
}
</script>
<style>
.headerBg{
  background: #ccc !important ;
}
</style>


