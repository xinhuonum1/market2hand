<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>${siteName!""}|数据备份管理-${title!""}</title>
    <#include "../common/header.ftl"/>
    <style>
        td{
            vertical-align:middle;
        }
    </style>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">

            <!-- logo -->
            <div id="logo" class="sidebar-header">
                <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <#include "../common/left-menu.ftl"/>
            </div>

        </aside>
        <!--End 左侧导航-->

        <#include "../common/header-menu.ftl"/>

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">

                                <#include "../common/third-menu.ftl"/>
                            </div>
                            <div class="card-body">
                                <!-- 为ECharts准备一个具备大小（宽高）的Dom -->

                                <div id="charMain" style="width: 600px;height:400px;display: inline-block"></div>

                                <div id="lineMain" style="width: 600px;height:400px ;display: inline-block;margin-left: 50px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript" src="/admin/js/echarts.min.js"></script>
<script type="text/javascript" src="/admin/js/jquery.min.js"></script>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charMain'));

    //折线图
    var lineDom = document.getElementById('lineMain');
    var myLine = echarts.init(lineDom);

    function findCategory(url){
        // 异步加载数据
        $.get('/admin/statistics/getMyChartData').done(function (data) {
            myChart.setOption({
                title: {
                    text: '商品类别销售额统计'
                },
                tooltip: {},
                legend: {
                    data:['销售额']
                },
                xAxis: {
                    data: data.categories,
                    axisLabel:{
                        interval: 0,
                        rotate:40
                    }
                },
                yAxis: {},
                series: [{
                    name: '销售额',
                    type: 'bar',
                    data: data.data
                }],
            });
            myLine.setOption({
                xAxis: {
                    type: 'category',
                    axisLabel:{
                        interval: 0,
                        rotate:40
                    },
                    data: data.categories
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data.data,
                    type: 'line',
                    smooth: true
                }]
            })
        });

    }
        // 异步加载数据
    $.get('/admin/statistics/getMyChartData').done(function (data) {
        myChart.setOption({
            title: {
                text: '商品类别销售额统计'
            },
            tooltip: {},
            legend: {
                data:['销售额']
            },
            xAxis: {
                data: data.categories,
                axisLabel:{
                    interval: 0,
                    rotate:40
                }
            },
            yAxis: {},
            series: [{
                name: '销售额',
                type: 'bar',
                data: data.data
            }],
        });
        myLine.setOption({
            xAxis: {
                type: 'category',
                axisLabel:{
                    interval: 0,
                    rotate:40
                },
                data: data.categories
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: data.data,
                type: 'line',
                smooth: true
            }]
        })
    });

    function findTime(url){
        // 异步加载数据
        $.get('/admin/statistics/getMyChartDataByTime').done(function (data) {
            myChart.setOption({
                title: {
                    text: '商品年度销售额统计'
                },
                tooltip: {},
                legend: {
                    data:['销售额']
                },
                xAxis: {
                    data: data.categories,
                    axisLabel:{
                        interval: 0,
                        rotate:40
                    }
                },
                yAxis: {},
                series: [{
                    name: '销售额',
                    type: 'bar',
                    data: data.data
                }],
            });
            myLine.setOption({
                xAxis: {
                    type: 'category',
                    axisLabel:{
                        interval: 0,
                        rotate:40
                    },
                    data: data.categories
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data.data,
                    type: 'line',
                    smooth: true
                }]
            })
        });
    }



</script>
</body>
</html>