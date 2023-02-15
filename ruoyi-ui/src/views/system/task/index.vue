<template>
    <div class="app-container">
      <el-row :gutter="20">
        <!--组织数据-->
        <!-- <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input v-model="deptName" placeholder="请输入组织名称" clearable size="small" prefix-icon="el-icon-search"
              style="margin-bottom: 20px" />
          </div>
          <div class="head-container">
            <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false"
              :filter-node-method="filterNode" ref="tree" node-key="id" default-expand-all highlight-current
              @node-click="handleNodeClick" />
          </div>
        </el-col> -->
        <!--用户数据-->
        <el-col :span="20" :xs="24">
          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="68px">
            <el-form-item label="组织" prop="userName">
                <el-input v-model="queryParams.userName" placeholder="请输入组织名称" clearable style="width: 240px"
                  @keyup.enter.native="handleQuery" />
              </el-form-item>
            <el-form-item label="任务状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="任务状态" clearable style="width: 240px">
                <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label"
                  :value="dict.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
              <el-date-picker v-model="dateRange" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
  
          <!-- 用户操作按钮，在补贴发放功能中禁用 -->
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button type="success" plain icon="el-icon-money" size="mini" :disabled="multiple" @click="handleUpdate"
                v-hasPermi="['system:user:edit']">批量完成
              </el-button>
              <el-button type="danger" plain icon="el-icon-close" size="mini" :disabled="multiple" @click="handleFreeze"
                v-hasPermi="['system:user:add']">批量删除
              </el-button>
              <el-button type="danger" plain icon="el-icon-close" size="mini" :disabled="multiple" @click="handleFreeze"
                v-hasPermi="['system:user:add']">批量终止
              </el-button>
            </el-col>
  
            <!-- <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar> -->
          </el-row>
  
          <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="50" align="center" />
            <el-table-column label="任务编号" align="center" key="userId" prop="userId" v-if="columns[0].visible" />
            <el-table-column label="任务名称" align="center" key="userName" prop="userName" v-if="columns[1].visible"
              :show-overflow-tooltip="true" />
            <el-table-column label="创建者" align="center" key="nickName" prop="nickName" v-if="columns[2].visible"
              :show-overflow-tooltip="true" />
            <el-table-column label="所属组织" align="center" key="deptName" prop="dept.deptName" v-if="columns[3].visible"
              :show-overflow-tooltip="true" />
            <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="160" class-name="small-padding fixed-width">
              <template slot-scope="scope" v-if="scope.row.userId !== 1">
                <el-button size="mini" type="text" icon="el-icon-money" @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:user:edit']">编辑</el-button>
                <el-button size="mini" type="text" icon="el-icon-close" @click="handleFreeze (scope.row)"
                  v-hasPermi="['system:user:remove']">终止</el-button>
                <el-button size="mini" type="text" icon="el-icon-check" @click="handleFreeze (scope.row)"
                  v-hasPermi="['system:user:remove']">完成</el-button>
              </template>
            </el-table-column>
          </el-table>
  
          <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
        </el-col>
      </el-row>
  
      <!-- 发放补贴操作对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="补贴金额" prop="nickName">
                <el-input v-model="form.nickName" placeholder="请输入金额" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="补贴说明" prop="deptId">
                <el-input v-model="form.deptId" placeholder="请选择说明" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
  
    </div>
  </template>
  
  <script>
    import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
    import { getToken } from "@/utils/auth";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  
    export default {
      name: "CheckIn",
      dicts: ['sys_normal_disable', 'sys_user_sex'],
      components: { Treeselect },
      data() {
        return {
          // 遮罩层
          loading: true,
          // 选中数组
          ids: [],
          // 非单个禁用
          single: true,
          // 非多个禁用
          multiple: true,
          // 显示搜索条件
          showSearch: true,
          // 总条数
          total: 0,
          // 用户表格数据
          userList: null,
          // 弹出层标题
          title: "",
          // 组织树选项
          deptOptions: undefined,
          // 是否显示弹出层
          open: false,
          // 组织名称
          deptName: undefined,
          // 默认密码
          initPassword: undefined,
          // 日期范围
          dateRange: [],
          // 岗位选项
          postOptions: [],
          // 角色选项
          roleOptions: [],
          // 表单参数
          form: {},
          defaultProps: {
            children: "children",
            label: "label"
          },
          // 查询参数
          queryParams: {
            pageNum: 1,
            pageSize: 10,
            userName: undefined,
            phonenumber: undefined,
            status: undefined,
            deptId: undefined
          },
          // 列信息
          columns: [
            { key: 0, label: `用户编号`, visible: true },
            { key: 1, label: `用户名称`, visible: true },
            { key: 2, label: `用户昵称`, visible: true },
            { key: 3, label: `组织`, visible: true },
            { key: 4, label: `手机号码`, visible: true },
            { key: 5, label: `状态`, visible: true },
            { key: 6, label: `创建时间`, visible: true }
          ],
          // 表单校验
          rules: {
            userName: [
              { required: true, message: "用户名称不能为空", trigger: "blur" },
              { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
            ],
            nickName: [
              { required: true, message: "用户昵称不能为空", trigger: "blur" }
            ],
            password: [
              { required: true, message: "用户密码不能为空", trigger: "blur" },
              { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
            ],
            email: [
              {
                type: "email",
                message: "请输入正确的邮箱地址",
                trigger: ["blur", "change"]
              }
            ],
            phonenumber: [
              {
                pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                message: "请输入正确的手机号码",
                trigger: "blur"
              }
            ]
          }
        };
      },
      watch: {
        // 根据名称筛选组织树
        deptName(val) {
          this.$refs.tree.filter(val);
        }
      },
      created() {
        this.getList();
        this.getDeptTree();
        this.getConfigKey("sys.user.initPassword").then(response => {
          this.initPassword = response.msg;
        });
      },
      methods: {
        /** 查询用户列表 */
        getList() {
          this.loading = true;
          listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.userList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
          );
        },
        /** 查询组织下拉树结构 */
        getDeptTree() {
          deptTreeSelect().then(response => {
            this.deptOptions = response.data;
          });
        },
        // 筛选节点
        filterNode(value, data) {
          if (!value) return true;
          return data.label.indexOf(value) !== -1;
        },
        // 节点单击事件
        handleNodeClick(data) {
          this.queryParams.deptId = data.id;
          this.handleQuery();
        },
        // 用户状态修改
        handleStatusChange(row) {
          let text = row.status === "0" ? "启用" : "停用";
          this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function () {
            return changeUserStatus(row.userId, row.status);
          }).then(() => {
            this.$modal.msgSuccess(text + "成功");
          }).catch(function () {
            row.status = row.status === "0" ? "1" : "0";
          });
        },
        // 取消按钮
        cancel() {
          this.open = false;
          this.reset();
        },
        // 表单重置
        reset() {
          this.form = {
            userId: undefined,
            deptId: undefined,
            userName: undefined,
            nickName: undefined,
            password: undefined,
            phonenumber: undefined,
            email: undefined,
            sex: undefined,
            status: "0",
            remark: undefined,
            postIds: [],
            roleIds: []
          };
          this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
          this.queryParams.pageNum = 1;
          this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
          this.dateRange = [];
          this.resetForm("queryForm");
          this.queryParams.deptId = undefined;
          this.$refs.tree.setCurrentKey(null);
          this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
          this.ids = selection.map(item => item.userId);
          this.single = selection.length != 1;
          this.multiple = !selection.length;
        },
        // 更多操作触发
        handleCommand(command, row) {
          switch (command) {
            case "handleResetPwd":
              this.handleResetPwd(row);
              break;
            case "handleAuthRole":
              this.handleAuthRole(row);
              break;
            default:
              break;
          }
        },
  
        /** 发放补贴操作 */
        handleUpdate(row) {
          this.reset();
          const userId = row.userId || this.ids;
          getUser(userId).then(response => {
            this.form = response.data;
            this.postOptions = response.posts;
            this.roleOptions = response.roles;
            this.$set(this.form, "postIds", response.postIds);
            this.$set(this.form, "roleIds", response.roleIds);
            this.open = true;
            this.title = "修改用户";
            this.form.password = "";
          });
        },
  
        /** 修改按钮操作 */
        handleFreeze(row) {
          this.$confirm('此操作将冻结志愿者的账户余额, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '冻结成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消余额冻结操作！'
            });
          });
        },
  
        /** 提交按钮 */
        submitForm: function () {
          this.$refs["form"].validate(valid => {
            if (valid) {
              if (this.form.userId != undefined) {
                updateUser(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else {
                addUser(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }
            }
          });
        }
      }
    };
  </script>