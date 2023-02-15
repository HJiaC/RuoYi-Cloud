<template lang="">
        <div id="vol" style="width: 500px;height: 500px;"></div>
</template>
<script>
    //引入Echarts
    import * as echarts from 'echarts';
    export default {
        name: 'VolunteerChange',
        data() {
            return {

            }
        },
        methods: {
            showChart() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('vol'));
                
                // 绘制图表
                myChart.setOption({
                    xAxis: {
                        data: ['A', 'B', 'C', 'D', 'E']
                    },
                    yAxis: {},
                    series: [
                        {
                            data: [10, 22, 28, 23, 19],
                            type: 'line',
                            smooth: true
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
<style lang="">

</style>