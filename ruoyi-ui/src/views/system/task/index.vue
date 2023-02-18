<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属组织" prop="organization">
        <el-input
          v-model="queryParams.organization"
          placeholder="请输入任务所属组织"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="任务状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_task_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查找</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:task:add']"
        >创建任务</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-check"
          size="mini"
          :disabled="multiple"
          @click="handleFinish"
          v-hasPermi="['system:task:remove']"
        >批量完成</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-caret-right"
          size="mini"
          :disabled="multiple"
          @click="handleStart"
          v-hasPermi="['system:task:edit']"
        >批量开始</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-switch-button"
          size="mini"
          :disabled="multiple"
          @click="handleStop"
          v-hasPermi="['system:task:export']"
        >批量暂停</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:task:remove']"
        >批量删除</el-button>
      </el-col>
      
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务号" align="center" prop="taskId" />
      <el-table-column label="任务名称" align="center" prop="taskName" />
      <el-table-column label="所属组织" align="center" prop="organization" />
      <el-table-column label="创建人员" align="center" prop="createBy" />
      <el-table-column label="任务状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-caret-right"
            @click="handleStart(scope.row)"
            v-hasPermi="['system:task:remove']"
          >开始
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-switch-button"
            @click="handleStop(scope.row)"
            v-hasPermi="['system:task:remove']"
          >暂停
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:task:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改Web端任务中心任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="所属组织" prop="organization">
          <el-input v-model="form.organization" placeholder="请输入所属组织" />
        </el-form-item>
        <!-- <el-form-item label="结束时间" prop="organization">
          <el-input v-model="form.organization" placeholder="请输入所属组织" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask, chageTaskStatus } from "@/api/system/task";

export default {
  name: "Task",
  dicts:['sys_task_status'],
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
      // Web端任务中心任务表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        organization: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskName: [
          { required: true, message: "任务名称不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人员不能为空", trigger: "blur" }
        ],
        organization: [
          { required: true, message: "所属组织不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "任务状态不能为空", trigger: "change" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询Web端任务中心任务列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
        //把从后端接收到的任务状态码转换为任务实际展示状态
        for (let item of this.taskList){
            switch(item.status)
            {
              case 0:
                item.status = "运行";
                break;
              case 1:
                item.status = "暂停";
                break;
              case 2:
                item.status = "完成";
                break;
              case 3:
                item.status = "创建";
                break;
            }
        }
        this.total = response.total;
        this.loading = false;
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
        taskId: null,
        taskName: null,
        createBy: null,
        organization: null,
        status: null,
        createTime: null,
        updateTime: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getTask(taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务";
      });
    },
    /** 开始按钮操作 */
    handleStart(row){
      const taskId = row.taskId || this.ids;
      const status = 0; //0为任务运行状态
      this.$modal.confirm('是否确认开始任务？').then(function() {
        return chageTaskStatus(taskId,status);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("任务已开启");
      }).catch(() => {});
    },

    /** 暂停按钮操作 */
    handleStop(row){
      const taskId = row.taskId || this.ids;
      const status = 1; //1为任务暂停状态
      this.$modal.confirm('是否确认暂停任务？').then(function() {
        return chageTaskStatus(taskId,status);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("任务已暂停");
      }).catch(() => {});
    },
    handleFinish(row){
      const taskId = row.taskId || this.ids;
      const status = 2; //2为任务完成状态
      this.$modal.confirm('是否将任务标记为完成状态？').then(function() {
        return chageTaskStatus(taskId,status);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("提交成功！");
      }).catch(() => {});
    },
    
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            //创建任务时添加创建人员和创建时间，预防sql报错
            this.form.createBy = "黄新建";
            this.form.createTime = "2023-02-17 15:10:29";
            addTask(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除Web端任务中心任务编号为"' + taskIds + '"的数据项？').then(function() {
        return delTask(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
