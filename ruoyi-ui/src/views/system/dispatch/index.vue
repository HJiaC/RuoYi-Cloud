<template>
  <div class="app-container">
    <!--块类统计-->
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card class="box-card">
          <h3>在线人数</h3>
          <div>
            <i class="el-icon-s-shop" style="color: purple"></i>
            <span>10</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <h3>工作人数</h3>
          <div>
            <i class="el-icon-user-solid" style="color: green"></i>
            <span>5</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <h3>空闲人数</h3>
          <div>
            <i class="el-icon-s-flag" style="color: red"></i>
            <span>5</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="taskId">
        <el-input v-model="queryParams.taskId" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="组织" prop="member">
        <el-input v-model="queryParams.member" placeholder="请输入组织名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="当前状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="选择状态" clearable style="width: 240px">
          <el-option v-for="dict in dict.type.sys_dispatch_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      <el-button type="success" icon="el-icon-guide" size="mini" @click="getDispatchRecordList">调度记录</el-button>
      </el-form-item>
    </el-form>

    <el-drawer title="调度记录" :visible.sync="record" direction="rtl" size="65%">
      <el-table :data="dispatchRecordList">
        <el-table-column property="createTime" label="时间" width="200"></el-table-column>
        <el-table-column property="member" label="姓名" ></el-table-column>
        <el-table-column property="to" label="调度目的地"></el-table-column>
        <el-table-column property="reason" label="调度原因"></el-table-column>
        <el-table-column property="remark" label="备注"></el-table-column>
      </el-table>
    </el-drawer>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="人员图像" align="center">
        <el-avatar :size="50" shape="square" :src="circleUrl"></el-avatar>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="member" />
      <el-table-column label="组织" align="center" />
      <el-table-column label="任务名称" align="center" prop="taskName" />
      <el-table-column label="当前状态" align="center" prop="status" />
      <el-table-column label="所在区域" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-location-information" @click="viewDetail(scope.row)"
            v-hasPermi="['system:record:edit']">调度</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 修改记录状态操作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属人员" prop="member">
          <el-input v-model="form.member" disabled />
        </el-form-item>
        <el-form-item label="调度任务">
          <el-input v-model="form.taskId" />
        </el-form-item>
        <el-form-item label="调度区域">
          <el-input v-model="form.to" />
        </el-form-item>
        <el-form-item label="操作备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="调度原因" prop="reason">
          <el-select v-model="form.reason" placeholder="选择要执行的操作" style="width: 240px">
            <el-option v-for="dict in dict.type.sys_dispatch_reason" :key="dict.value" :label="dict.label"
              :value="dict.value" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import { listRecord, getRecord, delRecord, addRecord, updateRecord, getTaskRecordStatistic } from "@/api/system/taskRecord";
  import { addDispatchRecord, listDispatchRecord } from '@/api/system/dispatchRecord';
  export default {
    name: "Record",
    dicts: ['sys_task_record_status', 'sys_task_record_operation', 'sys_dispatch_reason','sys_dispatch_status'],
    data() {
      return {
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
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
        // 调度记录控制
        record: false,
        // 总条数
        total: 0,
        // 任务记录表格数据
        recordList: [],
        //调度记录表哥数据
        dispatchRecordList: [],
        // 弹出层标题
        title: "",
        // 是否显示操作弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          taskId: null,
          status: null,
          member: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          taskId: [
            { required: true, message: "任务ID不能为空", trigger: "blur" }
          ],
          status: [
            { required: true, message: "记录的情况0：任务尚未开始1：任务被终止2：任务已完成3：任务进行中4：任务存在异常不能为空", trigger: "change" }
          ],
        },
        //统计数据
        statistic: {}
      };
    },
    created() {
      this.getList();
      this.getTaskRecordStatistic();
    },
    methods: {
      //查询调度记录
      getDispatchRecordList() {
        this.record = true;
        listDispatchRecord().then(response => {
            this.dispatchRecordList = response.rows
        })
      },
      /**查询统计数据*/
      getTaskRecordStatistic() {
        getTaskRecordStatistic().then(response => {
          this.statistic = response.data;
        })
      },
      /** 查询任务记录列表 */
      getList() {
        this.loading = true;
        listRecord(this.queryParams).then(response => {
          this.recordList = response.rows;
          //把从后端接收到的任务状态码转换为任务实际展示状态
          for (let item of this.recordList) {
            switch (item.status) {
              case 1:
                item.status = "进行";
                break;
              case 2:
                item.status = "完成";
                break;
              case 3:
                item.status = "异常";
                break;
              case 4:
                item.status = "已处理";
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
        this.openi = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          recordId: null,
          taskId: null,
          status: null,
          member: null,
          remark: null
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
        this.ids = selection.map(item => item.recordId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const recordId = row.recordId || this.ids
        getRecord(recordId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改记录状态";
        });
      },
      /** 记录详情按钮 */
      viewDetail(row) {
        this.reset();
        const recordId = row.recordId;
        getRecord(recordId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看记录详情";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.operation != 1) {
              //更改记录状态
              addDispatchRecord(this.form).then(response => {
                this.$modal.msgSuccess("调度成功");
                this.open = false;
                this.getList();
                this.getTaskRecordStatistic();
              });
            }

          }
        });
      },
    }
  };
</script>