<template>
  <div class="app-container">
    <!--块类统计-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <h3>组织人数</h3>
          <div>
            <i class="el-icon-s-shop" style="color: purple"></i>
            <span>1</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>打卡记录</h3>
          <div>
            <i class="el-icon-user-solid" style="color: green"></i>
            <span>2</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>出勤率</h3>
          <div>
            <i class="el-icon-s-flag" style="color: red"></i>
            <span>90%</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>异常情况</h3>
          <div>
            <i class="el-icon-star-on" style="color: blue"></i>
            <span>4</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="taskId">
        <el-input v-model="queryParams.taskId" placeholder="请输入任务名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="所属人员" prop="member">
        <el-input v-model="queryParams.member" placeholder="请输入参与人员" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="记录状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="记录状态" clearable style="width: 240px">
          <el-option v-for="dict in dict.type.sys_task_record_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="任务名称" align="center" prop="taskId" />
      <el-table-column label="记录状态" align="center" prop="status" />
      <el-table-column label="所属人员" align="center" prop="member" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-info" @click="viewDetail(scope.row)"
            v-hasPermi="['system:record:edit']">详情</el-button>
          <el-button size="mini" type="text" icon="el-icon-coordinate" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:record:remove']">操作</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 查看记录详情对话框 -->
    <el-dialog :title="title" :visible.sync="openi" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="记录ID" prop="recordId">
          <el-input v-model="form.taskId" disabled />
        </el-form-item>
        <el-form-item label="任务名称" prop="taskId">
          <el-input v-model="form.taskId" disabled />
        </el-form-item>
        <el-form-item label="所属人员" prop="member">
          <el-input v-model="form.member" disabled />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" disabled />
        </el-form-item>
        
      </el-form>
      <!-- <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div> -->
    </el-dialog>

    <!-- 修改记录状态操作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="记录ID" prop="taskId">
          <el-input v-model="form.taskId" disabled />
        </el-form-item>
        <el-form-item label="所属人员" prop="member">
          <el-input v-model="form.member" disabled />
        </el-form-item>
        <el-form-item label="操作备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="操作" prop="operation">
          <el-select v-model="form.operation" placeholder="选择要执行的操作" clearable style="width: 240px">
            <el-option v-for="dict in dict.type.sys_task_record_operation" :key="dict.value" :label="dict.label"
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
  import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/system/record";

  export default {
    name: "Record",
    dicts: ['sys_task_record_status', 'sys_task_record_operation'],
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
        // 任务记录表格数据
        recordList: [],
        // 弹出层标题
        title: "",
        // 是否显示操作弹出层
        open: false,
        // 是否显示详情弹出层
        openi: false,
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
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
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
      viewDetail(row){
        this.reset();
        const recordId = row.recordId;
        getRecord(recordId).then(response => {
          this.form = response.data;
          this.openi = true;
          this.title = "查看记录详情";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.operation != 1) {
              //更改记录状态
              updateRecord(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            }
             else {
              delRecord(this.form.recordId).then(response => {
                this.$modal.msgSuccess("删除成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      // handleDelete(row) {
      //   const recordIds = row.recordId || this.ids;
      //   this.$modal.confirm('是否确认删除编号为"' + recordIds + '"的记录？').then(function () {
      //     return delRecord(recordIds);
      //   }).then(() => {
      //     this.getList();
      //     this.$modal.msgSuccess("删除成功");
      //   }).catch(() => { });
      // },
      /** 导出按钮操作 */
      // handleExport() {
      //   this.download('system/record/export', {
      //     ...this.queryParams
      //   }, `record_${new Date().getTime()}.xlsx`)
      // }
    }
  };
</script>