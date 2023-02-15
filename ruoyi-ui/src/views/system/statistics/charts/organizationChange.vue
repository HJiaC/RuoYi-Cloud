<template lang="">
        <div id="org" style="width: 500px;height: 500px;"></div>
</template>
<script>
    //引入Echarts
    import * as echarts from 'echarts';
    export default {
        name: 'OrganizationChange',
        data() {
            return {

            }
        },
        methods: {
            showChart() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('org'));
                // 绘制图表
                myChart.setOption({
                    xAxis: {
                        type: 'category',
                        data: ['A', 'B', 'C','D','E']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [120, 200, 150, 250, 350, 500],
                            type: 'line',
                            lineStyle: {
                                normal: {
                                    color: 'green',
                                    width: 4,
                                    type: 'dashed'
                                }
                            }
                        }
                    ]
                });
            }
        },
        mounted() {
            this.showChart()
        },
    }
</script>
<style >
    
</style>