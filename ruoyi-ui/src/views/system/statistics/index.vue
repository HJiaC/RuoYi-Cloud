<template>
    <div>
        <!--块类统计-->
        <el-row :gutter="20">
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>组织数量</h3>
                    <div>
                        <i class="el-icon-s-shop" style="color: purple"></i>
                        <span>2424132</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>志愿者数量</h3>
                    <div>
                        <i class="el-icon-user-solid" style="color: green"></i>
                        <span>12763</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>补贴发放金额</h3>
                    <div>
                        <i class="el-icon-s-flag" style="color: red"></i>
                        <span>28%</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="box-card">
                    <h3>当前在线人数</h3>
                    <div>
                        <i class="el-icon-star-on" style="color: blue"></i>
                        <span>358</span>
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
    import OrgannizationChange from './charts/organizationChange';
    import VolunteerChange from './charts/volunteerChange';
    import MoneyChange from './charts/moneyChange';
    import OnlineChange from './charts/onlineChange';
    export default {
        name: 'Statistics',
        components:{OrgannizationChange,VolunteerChange,MoneyChange,OnlineChange},
        data() {
            return {
                
            }
        },
        computed:{

        },
        methods: {
            
        },
    }
</script>

<style>
    #charts-group {
        display: flex;
        justify-content: center;
    }
</style>