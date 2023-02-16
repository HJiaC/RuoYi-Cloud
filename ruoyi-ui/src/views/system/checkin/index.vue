<template>
    <div>
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
                    <h3>任务数量</h3>
                    <div>
                        <i class="el-icon-user-solid" style="color: green"></i>
                        <span>2</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>任务完成率</h3>
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
        <!-- 考勤任务展示主体 -->
        <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="任务ID" align="center" prop="noticeId" width="100" />
            <el-table-column
              label="任务名称"
              align="center"
              prop="noticeTitle"
              :show-overflow-tooltip="true"
            />
            <el-table-column label="异常类型" align="center" prop="noticeType" width="100">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_notice_type" :value="scope.row.noticeType"/>
              </template>
            </el-table-column>
            <el-table-column label="异常人员" align="center" prop="noticeType" width="100">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_notice_type" :value="scope.row.noticeType"/>
              </template>
            </el-table-column>
            <el-table-column label="备注" align="center" prop="status" width="100">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_notice_status" :value="scope.row.status"/>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:notice:edit']"
                >处理</el-button>
              </template>
            </el-table-column>
          </el-table>
    </div>
</template>

<script>
    export default {
        name:"CheckIn",
        data() {
          return {
            noticeList:{

            }
          }
        },

    }
</script>

<style >
    
</style>