<template lang="">
        <div id="on" style="width: 500px;height: 500px;"></div>
</template>
<script>
    //引入Echarts
    import * as echarts from 'echarts';
    export default {
        name: 'OnlineChange',
        data() {
            return {

            }
        },
        methods: {
            showChart() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('on'));
                // 绘制图表
                myChart.setOption({
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        top: '5%',
                        left: 'center'
                    },
                    series: [
                        {
                            name: 'Access From',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: 40,
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                { value: 1048, name: 'Search Engine' },
                                { value: 735, name: 'Direct' },
                                { value: 580, name: 'Email' },
                                { value: 484, name: 'Union Ads' },
                                { value: 300, name: 'Video Ads' }
                            ]
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