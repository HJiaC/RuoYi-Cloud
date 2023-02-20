<template>
    <div>
        <!--块类统计-->
        <el-row :gutter="20">
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>组织数量</h3>
                    <div>
                        <i class="el-icon-s-shop" style="color: purple"></i>
                        <span>{{statistic.organizationNum}}</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>志愿者数量</h3>
                    <div>
                        <i class="el-icon-user-solid" style="color: green"></i>
                        <span>{{statistic.volunteerNum}}</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>补贴发放金额</h3>
                    <div>
                        <i class="el-icon-s-flag" style="color: red"></i>
                        <span>数据未填充</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>当前在线人数</h3>
                    <div>
                        <i class="el-icon-star-on" style="color: blue"></i>
                        <span>{{statistic.onlineNum}}</span>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row class="charts-group">
            <OrgannizationChange />
            <VolunteerChange />
            <MoneyChange />
            <OnlineChange />
        </el-row>
    </div>
</template>

<script>
    import {list} from '/src/api/monitor/online.js';
    import {getTaskRecordStatistic} from '/src/api/system/taskRecord.js';
    import OrgannizationChange from './charts/organizationChange';
    import VolunteerChange from './charts/volunteerChange';
    import MoneyChange from './charts/moneyChange';
    import OnlineChange from './charts/onlineChange';
    export default {
        name: 'Statistics',
        components:{OrgannizationChange,VolunteerChange,MoneyChange,OnlineChange},
        data() {
            return {
                //统计信息
                statistic: {
                    organizationNum: 0,
                    volunteerNum: 0,
                    moneyAmount: 0,
                    onlineNum: 0,
                },

            }
        },
        computed:{

        },
        created() {
            this.getStatisticInfo();
        },
        methods: {
            getStatisticInfo() {
                //请求在线人数数据
                list().then(response => {
                    this.statistic.onlineNum = response.total;
                })
                //请求组织数量和志愿者数量数据
                getTaskRecordStatistic().then( response => {
                    this.statistic.organizationNum = response.data.memberNum;
                    this.statistic.volunteerNum = response.data.memberNum;
                })
            }
            
        },
    }
</script>

<style>
    #charts-group {
        display: flex;
        justify-content: center;
    }
</style>