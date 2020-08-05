/**
 * Created by 90977 on 2018/5/8.
 */
var noAcceptCount = document.getElementById("noAcceptCount").value;
var top_chart1Num = noAcceptCount;//未受理条数

var acceptCount = document.getElementById("acceptCount").value;
var top_chart2Num = acceptCount;//已受理条数

var top_chart3Num = 0;//退案条数

var geneCaseCount = document.getElementById("geneCaseCount").value;
var top_chart4Num = geneCaseCount;//已导入条数
var caseContrastNum = [75, 60, 70, 50, 65, 45, 40, 45, 30, 20, 25, 10]//案件比对数
var sampleContrastNum = [10, 25, 20, 30, 45, 40, 45, 65, 50, 70, 60, 75]//检材比对数
var chart6Total = [32, 22, 14, 9]//数量统计
var detectionNum = 0.5//检出率
var rateActionNum = 0.3//检材率
var caseNum = 30;//案件数
var sampleNum = 98;//检材数
var caseclassify= [
    {value: 335, name: '亲子鉴定'},
    {value: 310, name: '个体识别'},
    {value: 234, name: '种族属认定'},
    {value: 135, name: '动植物DNA检验'},
    {value: 1548, name: '其他'}
]//案件分类
var sampleclassify= [
    {value: 335, name: '烟蒂'},
    {value: 310, name: '唾液'},
    {value: 234, name: '毛发'},
    {value: 135, name: '精斑'},
    {value: 1548, name: '血液'}
]//检材分类

var caseTotalCount = document.getElementById("caseTotalCount").value;
var top_chartTotalNum = caseTotalCount; //总条数

var top_chart1 = echarts.init(document.getElementById('top_chart1'));
var top_chart1Percentage = parseInt(top_chart1Num / top_chartTotalNum * 100);
var top_chart1Option = {
    backgroundColor: '#fff',
    title: {
        text: top_chart1Num + '条',
        textStyle: {
            fontSize: 32,
            color: '#333',
            fontWeight: 'normal',
        },
        subtext: '未受理',
        subtextStyle: {
            fontSize: 18,
            color: '#333',
            fontWeight: 'normal',
        },
    },
    grid: {
        left: '0%',
        top: '50%',
        right: '16%',
        bottom: '8%',
        containLabel: false
    },
    xAxis: [{
        show: false,
    }],
    yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
            textStyle: {
                color: '#ffffff',
                fontSize: '16',
            }
        },
        data: ['0']
    }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
            textStyle: {
                color: '#c4c4c4',
                fontSize: '13',
            }
        },
        data: [top_chartTotalNum]
    }, {
        axisLine: {
            lineStyle: {
                color: '#fff',
            }
        },
        data: [],
    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: [top_chart1Percentage],
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 15,
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#51ae54'
                }, {
                    offset: 1,
                    color: '#82c65b'
                }]),
            }
        },
        z: 2
    }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [99.5],
        barWidth: 10,
        itemStyle: {
            normal: {
                color: '#fff',
                barBorderRadius: 15,
            }
        },
        z: 1
    }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        data: [100],
        barWidth: 14,
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#51ae54'
                }, {
                    offset: 1,
                    color: '#82c65b'
                }]),
                barBorderRadius: 15,
            }
        },
        z: 0
    },]

};
top_chart1.setOption(top_chart1Option);

var top_chart2 = echarts.init(document.getElementById('top_chart2'));
var top_chart2Percentage = parseInt(top_chart2Num / top_chartTotalNum * 100)
var top_chart2Option = {
    backgroundColor: '#fff',
    title: {
        text: top_chart2Num + '条',
        textStyle: {
            fontSize: 32,
            color: '#333',
            fontWeight: 'normal',
        },
        subtext: '已受理',
        subtextStyle: {
            fontSize: 18,
            color: '#333',
            fontWeight: 'normal',
        },
    },
    grid: {
        left: '0%',
        top: '50%',
        right: '16%',
        bottom: '8%',
        containLabel: false
    },
    xAxis: [{
        show: false,
    }],
    yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
            textStyle: {
                color: '#ffffff',
                fontSize: '16',
            }
        },
        data: ['0']
    }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
            textStyle: {
                color: '#c4c4c4',
                fontSize: '13',
            }
        },
        data: [top_chartTotalNum]
    }, {
        axisLine: {
            lineStyle: {
                color: '#fff',
            }
        },
        data: [],
    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: [top_chart2Percentage],
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 15,
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#fc9664'
                }, {
                    offset: 1,
                    color: '#fdbf30'
                }]),
            }
        },
        z: 2
    }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [99.5],
        barWidth: 10,
        itemStyle: {
            normal: {
                color: '#fff',
                barBorderRadius: 15,
            }
        },
        z: 1
    }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        data: [100],
        barWidth: 14,
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#fc9664'
                }, {
                    offset: 1,
                    color: '#fdbf30'
                }]),
                barBorderRadius: 15,
            }
        },
        z: 0
    },]
};
top_chart2.setOption(top_chart2Option);

var top_chart3 = echarts.init(document.getElementById('top_chart3'));
var top_chart3Percentage = parseInt(top_chart3Num / top_chartTotalNum * 100)
var top_chart3Option = {
    backgroundColor: '#fff',
    title: {
        text: top_chart3Num + '条',
        textStyle: {
            fontSize: 32,
            color: '#333',
            fontWeight: 'normal',
        },
        subtext: '退案',
        subtextStyle: {
            fontSize: 18,
            color: '#333',
            fontWeight: 'normal',
        },
    },
    grid: {
        left: '0%',
        top: '50%',
        right: '16%',
        bottom: '8%',
        containLabel: false
    },
    xAxis: [{
        show: false,
    }],
    yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
            textStyle: {
                color: '#ffffff',
                fontSize: '16',
            }
        },
        data: ['0']
    }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
            textStyle: {
                color: '#c4c4c4',
                fontSize: '13',
            }
        },
        data: [top_chartTotalNum]
    }, {
        axisLine: {
            lineStyle: {
                color: '#fff',
            }
        },
        data: [],
    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: [top_chart3Percentage],
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 15,
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#4a87ff'
                }, {
                    offset: 1,
                    color: '#66cbff'
                }]),
            }
        },
        z: 2
    }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [99.5],
        barWidth: 10,
        itemStyle: {
            normal: {
                color: '#fff',
                barBorderRadius: 15,
            }
        },
        z: 1
    }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        data: [100],
        barWidth: 14,
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#4a87ff'
                }, {
                    offset: 1,
                    color: '#66cbff'
                }]),
                barBorderRadius: 15,
            }
        },
        z: 0
    },]
};
top_chart3.setOption(top_chart3Option);

var top_chart4 = echarts.init(document.getElementById('top_chart4'));
var top_chart4Percentage = parseInt(top_chart4Num / top_chartTotalNum * 100)
var top_chart4Option = {
    backgroundColor: '#fff',
    title: {
        text: top_chart4Num + '条',
        textStyle: {
            fontSize: 32,
            color: '#333',
            fontWeight: 'normal',
        },
        subtext: '已导入结果',
        subtextStyle: {
            fontSize: 18,
            color: '#333',
            fontWeight: 'normal',
        },
    },
    grid: {
        left: '0%',
        top: '50%',
        right: '16%',
        bottom: '8%',
        containLabel: false
    },
    xAxis: [{
        show: false,
    }],
    yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
            textStyle: {
                color: '#ffffff',
                fontSize: '16',
            }
        },
        data: ['0']
    }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
            textStyle: {
                color: '#c4c4c4',
                fontSize: '13',
            }
        },
        data: [top_chartTotalNum]
    }, {
        axisLine: {
            lineStyle: {
                color: '#fff',
            }
        },
        data: [],
    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: [top_chart4Percentage],
        barWidth: 10,
        itemStyle: {
            normal: {
                barBorderRadius: 15,
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#f2463e'
                }, {
                    offset: 1,
                    color: '#ff847e'
                }]),
            }
        },
        z: 2
    }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [99.5],
        barWidth: 10,
        itemStyle: {
            normal: {
                color: '#fff',
                barBorderRadius: 15,
            }
        },
        z: 1
    }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        data: [100],
        barWidth: 14,
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#f2463e'
                }, {
                    offset: 1,
                    color: '#ff847e'
                }]),
                barBorderRadius: 15,
            }
        },
        z: 0
    },]
};
top_chart4.setOption(top_chart4Option);

var chart5 = echarts.init(document.getElementById('chart5'));
var chart5Option = {
    backgroundColor: '#fff',
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['案件对比数', '检材对比数'],
        left: '3%',
        right: '3%',
        bottom: '2%',
    },
    toolbox: {
        show: true,
        right: '3%',
        feature: {
            magicType: {show: true, type: ['line', 'bar']},
            saveAsImage: {
                show: true
            }
        }
    },
    calculable: true,
    grid: {
        left: '0%',
        right: '4%',
        top: '8%',
        containLabel: true
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    }],
    yAxis: [{
        type: 'value',
        axisLabel: {
            formatter: '{value} '
        }
    }],
    series: [{
        name: '案件对比数',
        type: 'line',
        smooth: true,
        showAllSymbol: true,
        symbol: 'emptyCircle',
        symbolSize: 10,
        itemStyle: {
            normal: {
                color: '#FC9664',
            }
        },
        data: caseContrastNum,
    },
        {
            name: '检材对比数',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 10,
            itemStyle: {
                normal: {
                    color: '#48C5B8',
                }
            },
            data: sampleContrastNum,
        }
    ]
};
chart5.setOption(chart5Option);

var chart6 = echarts.init(document.getElementById('chart6'));
var chart6Percentage = []
for (var i = 0; i < chart6Total.length; i++) {
    chart6Percentage.push(parseInt(chart6Total[i] / 40 * 100))
}
var chart6Option = {
    backgroundColor: '#fff',
    grid: {
        left: '11%',
        top: '12%',
        right: '0%',
        bottom: '8%',
        containLabel: true
    },
    xAxis: [{
        show: false,
    }],
    yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
            textStyle: {
                color: '#ffffff',
                fontSize: '16',
            }
        },
        data: ['1', '2', '3', '4']
    }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
            textStyle: {
                color: '#000',
                fontSize: '16',
            }
        },
        data: chart6Total
    }, {
        axisLine: {
            lineStyle: {
                color: 'rgba(0,0,0,0)'
            }
        },
        data: [],
    }],
    series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: chart6Percentage,
        label: {
            normal: {
                show: true,
                position: [0, -30],
                formatter: function (param) {
                    switch (param.dataIndex) {
                        case 0:
                            return '物证'
                            break;
                        case 1:
                            return '毒化'
                            break;
                        case 2:
                            return '病理'
                            break;
                        case 3:
                            return '临床'
                            break;
                    }
                },
                textStyle: {
                    color: '#000',
                    fontSize: '16',
                }
            }
        },
        barWidth: 21,
        itemStyle: {
            normal: {
                barBorderRadius: 15,
                color: function (params) {
                    switch (params.dataIndex) {
                        case 0:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#f2463e'
                            }, {
                                offset: 1,
                                color: '#ff847e'
                            }])

                            break;
                        case 1:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#4a87ff'
                            }, {
                                offset: 1,
                                color: '#66cbff'
                            }])

                            break;
                        case 2:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#fc9664'
                            }, {
                                offset: 1,
                                color: '#fdbf30'
                            }])
                            break;
                        case 3:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#51ae54'
                            }, {
                                offset: 1,
                                color: '#82c65b'
                            }])
                            break;
                    }
                },
            }
        },
        z: 2
    }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        data: [99.8, 99.8, 99.8, 99.8],
        barWidth: 20,
        itemStyle: {
            normal: {
                color: '#fff',
                barBorderRadius: 15,
            }
        },
        z: 1
    }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        data: [100, 100, 100, 100],
        barWidth: 24,
        itemStyle: {
            normal: {
                color: function (params) {
                    switch (params.dataIndex) {
                        case 0:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#f2463e'
                            }, {
                                offset: 1,
                                color: '#ff847e'
                            }])

                            break;
                        case 1:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#4a87ff'
                            }, {
                                offset: 1,
                                color: '#66cbff'
                            }])

                            break;
                        case 2:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#fc9664'
                            }, {
                                offset: 1,
                                color: '#fdbf30'
                            }])
                            break;
                        case 3:
                            return new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                                offset: 0,
                                color: '#51ae54'
                            }, {
                                offset: 1,
                                color: '#82c65b'
                            }])
                            break;
                    }
                },
                barBorderRadius: 15,
            }
        },
        z: 0
    },]
};
chart6.setOption(chart6Option);

var detectionRate = echarts.init(document.getElementById('chart7'));
var option = {
    backgroundColor: '#fff',
    series: [
        {
            type: 'liquidFill',
            center: ['30%', '50%'],
            radius: '70%',
            data: [detectionNum, 0.5, 0.4, 0.3],
            color: ['rgba(253,170,75,.4)', 'rgba(253,170,75,.3)', 'rgba(253,170,75,.2)'],
            outline: {
                show: false
            },
            backgroundStyle: {
                borderColor: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#fd9a5f'
                }, {
                    offset: 1,
                    color: '#fdb43e'
                }]),
                borderWidth: 8,
                color: 'transparent'
            },
            label: {
                normal: {
                    color: '#a76e00',
                    insideColor: '#fff',
                    fontSize: 40
                }
            },
        },
        {
            type: 'liquidFill',
            radius: '70%',
            center: ['70%', '50%'],
            data: [rateActionNum, 0.4, 0.3, 0.2],
            color: ['rgba(85,164,255,.2)', 'rgba(85,164,255,.3)', 'rgba(85,164,255,.4)'],
            outline: {
                show: false
            },
            backgroundStyle: {
                borderColor: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#4a87ff'
                }, {
                    offset: 1,
                    color: '#66cbff'
                }]),
                borderWidth: 8,
                color: 'transparent'
            },
            label: {
                normal: {
                    color: '#1c60d4',
                    insideColor: '#fff',
                    fontSize: 40
                }
            }
        },
        {
            type: 'liquidFill',
            center: ['30%', '50%'],
            radius: '70%',
            data: [detectionNum, 0.5, 0.4, 0.3],
            color: ['rgba(253,170,75,.4)', 'rgba(253,170,75,.3)', 'rgba(253,170,75,.2)'],
            outline: {
                show: false
            },
            backgroundStyle: {
                borderColor: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#fd9a5f'
                }, {
                    offset: 1,
                    color: '#fdb43e'
                }]),
                borderWidth: 8,
                color: 'transparent'
            },
            label: {
                normal: {
                    color: '#a76e00',
                    insideColor: '#fff',
                    fontSize: 40
                }
            },
        },
        {
            type: 'liquidFill',
            radius: '70%',
            center: ['70%', '50%'],
            data: [rateActionNum, 0.4, 0.3, 0.2],
            color: ['rgba(85,164,255,.2)', 'rgba(85,164,255,.3)', 'rgba(85,164,255,.4)'],
            outline: {
                show: false
            },
            backgroundStyle: {
                borderColor: new echarts.graphic.LinearGradient(0, 0, 1, 1, [{
                    offset: 0,
                    color: '#4a87ff'
                }, {
                    offset: 1,
                    color: '#66cbff'
                }]),
                borderWidth: 8,
                color: 'transparent'
            },
            label: {
                normal: {
                    color: '#1c60d4',
                    insideColor: '#fff',
                    fontSize: 40
                }
            }
        },
    ]
};
detectionRate.setOption(option);

var chart8 = echarts.init(document.getElementById('chart8'));
var echartData = [{
    value: caseNum,
}, {
    value: 200,
}];
var chart8Option = {
    backgroundColor: '#fff',
    series: [
        //内圈圆环
        {
            name: 'Line 0',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['50%', '50%'],
            itemStyle: {
                normal: {
                    color: '#000'
                }
            },
            data: [{
                value: 10,
                name: '',
            }],
            label: {
                normal: {
                    formatter: function (params) {
                        var time = echartData[0].value;
                        return '{time|' + time + '件}';
                    },
                    position: 'center',
                    textStyle: {
                        fontSize: 38,
                        fontWeight: 'bold',
                        color: '#d36726'
                    },
                    rich: {
                        time: {
                            color: '#d36726',
                            fontSize: 32,
                            padding: [0, 0],
                            fontWeight: 'bold'
                        },
                    }
                }
            }
        },
        //中间圆环
        {
            name: 'Line 1',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['75%', '60%'],
            color: [{
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                    offset: 0,
                    color: '#fc9664'// 0% 处的颜色
                }, {
                    offset: 1,
                    color: '#fdbf30' // 100% 处的颜色
                }],
                globalCoord: false // 缺省为 false
            }, 'none'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    }
                    ,
                    labelLine: {
                        show: false
                    }
                    ,
                }
            }
            ,
            data: echartData,
        },
        //外层圆环
        {
            name: 'Line 2',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['75%', '75%'],
            itemStyle: {
                normal: {
                    borderWidth: 2,
                    borderColor: '#fda453',
                    label: {
                        show: false
                    }
                    ,
                    labelLine: {
                        show: false
                    }
                    ,
                }
            }
            ,
            data: [{
                value: 10,
                name: '',

            }]
        },
    ],
};
chart8.setOption(chart8Option);

var chart9 = echarts.init(document.getElementById('chart9'));

var echartData = [{
    value: sampleNum,
}, {
    value: 200,
}];
var chart9Option = {
    backgroundColor: '#fff',
    series: [
        //内圈圆环
        {
            name: 'Line 1',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['50%', '50%'],
            itemStyle: {
                normal: {
                    color: '#000'
                }
            },
            data: [{
                value: 10,
                name: '',
            }],
            label: {
                normal: {
                    formatter: function (params) {
                        var time = echartData[0].value;
                        return '{time|' + time + '件}';
                    },
                    position: 'center',
                    textStyle: {
                        fontSize: 38,
                        fontWeight: 'bold',
                        color: '##1c60d4'
                    },
                    rich: {
                        time: {
                            color: '#1c60d4',
                            fontSize: 32,
                            padding: [0, 0],
                            fontWeight: 'bold'
                        },
                    }
                }
            }
        },
        //中间圆环
        {
            name: 'Line 1',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['75%', '60%'],
            color: [{
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                    offset: 0,
                    color: '#4a87ff'// 0% 处的颜色
                }, {
                    offset: 1,
                    color: '#66cbff' // 100% 处的颜色
                }],
                globalCoord: false // 缺省为 false
            }, 'none'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    }
                    ,
                    labelLine: {
                        show: false
                    }
                    ,
                }
            }
            ,
            data: echartData,
        },
        //外层圆环
        {
            name: 'Line 2',
            type: 'pie',
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ['50%', '50%'],
            radius: ['75%', '75%'],
            itemStyle: {
                normal: {
                    borderWidth: 2,
                    borderColor: '#1c60d4',
                    label: {
                        show: false
                    }
                    ,
                    labelLine: {
                        show: false
                    }
                    ,
                }
            }
            ,
            data: [{
                value: 10,
                name: '',

            }]
        },
    ],
};
chart9.setOption(chart9Option);

var chart10 = echarts.init(document.getElementById('chart10'));
var chart10Option = {
    backgroundColor: '#fff',
    tooltip: {
        trigger: 'axis'
    },
    grid: {
        left: '3%',
        right: '3%',
        top: '15%',
        containLabel: true
    },
    legend: {
        data: ['案件登记详情', '检材受理详情', 'DNA扩增循环数', '其他类别'],
        bottom: '2%',
        selectedMode: 'single',
    },
    toolbox: {
        show: true,
        right: '3%',
        feature: {
            magicType: {show: true, type: ['line', 'bar']},
            saveAsImage: {
                show: true
            }
        }
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
            data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
        }
    ],
    yAxis: {
        type: 'value'
    }
    ,
    series: [
        {
            name: '案件登记详情',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 5,
            areaStyle: {
                normal: {
                    type: 'default',
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(242,69,61,0.8)'
                    }, {
                        offset: 1,
                        color: 'rgba(242,69,61,0.2)'
                    }], false)
                }
            },
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    },
                    color: '#f2453d',
                    borderColor: '#4f5361',
                    borderWidth: 1
                }
            },
            data: [610, 312, 830, 610, 312, 312, 521, 354, 560, 910, 630, 310, 354, 560, 830, 310, 521, 310, 221, 654, 910, 630, 610, 310, 654, 910, 630, 221, 654, 521, 221,]
        },
        {
            name: '检材受理详情',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 5,
            areaStyle: {
                normal: {
                    type: 'default',
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(30,170,241,0.8)'
                    }, {
                        offset: 1,
                        color: 'rgba(30,170,241,0.2)'
                    }], false)
                }
            },
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    },
                    color: '#1eaaf1',
                    borderColor: '#4f5361',
                    borderWidth: 1
                }
            },
            data: [354, 630, 310, 312, 521, 610, 312, 560, 830, 221, 654, 521, 221, 610, 910, 354, 221, 654, 910, 630, 830, 521, 310, 610, 312, 310, 654, 910, 630, 310, 560,]
        },
        {
            name: 'DNA扩增循环数',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 5,
            areaStyle: {
                normal: {
                    type: 'default',
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(253,192,47,0.8)'
                    }, {
                        offset: 1,
                        color: 'rgba(253,192,47,0.2)'
                    }], false)
                }
            },
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    },
                    color: '#fdc02f',
                    borderColor: '#4f5361',
                    borderWidth: 1
                }
            },
            data: [136, 375, 380, 449, 114, 267, 142, 318, 357, 193, 421, 136, 375, 380, 449, 114, 267, 142, 318, 357, 193, 421, 136, 375, 380, 449, 114, 267, 142, 318, 391]
        },
        {
            name: '其他类别',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 5,
            areaStyle: {
                normal: {
                    type: 'default',
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(79,197,182,0.8)'
                    }, {
                        offset: 1,
                        color: 'rgba(79,197,182,0.2)'
                    }], false)
                }
            },
            smooth: true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    },
                    color: '#4fc5b6',
                    borderColor: '#4f5361',
                    borderWidth: 1
                }
            },
            data: [610, 312, 221, 654, 910, 630, 310, 521, 354, 560, 830, 610, 312, 221, 654, 910, 630, 310, 521, 354, 560, 830, 610, 312, 221, 654, 910, 630, 310, 521, 310]
        },
    ]
};
chart10.setOption(chart10Option);

var chart11 = echarts.init(document.getElementById('chart11'));

var chart11Option = {
    backgroundColor: '#fff',
    title: {
        text: '案件分类',
        subtext: '10000件',
        x: 'center',
        y: 'center',
        textStyle: {
            fontWeight: 'normal',
            fontSize: 25
        }
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: ['亲子鉴定', '个体识别', '种族属认定', '动植物DNA检验', '其他']
    },
    series: [
        {
            name: '案件分类',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            color: ['#f2453d', '#7bc9f0', '#fdc02f', '#50ae54', '#33d1bd'],
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: false,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:caseclassify
        }
    ]
};
chart11.setOption(chart11Option);

var chart12 = echarts.init(document.getElementById('chart12'));
var chart12Option = {
    backgroundColor: '#fff',
    title: {
        text: '检材分类',
        subtext: '10000件',
        x: 'center',
        y: 'center',
        textStyle: {
            fontWeight: 'normal',
            fontSize: 25
        }
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: ['烟蒂', '唾液', '毛发', '精斑', '血液']
    },
    series: [
        {
            name: '检材分类',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            color: ['#f2453d', '#7bc9f0', '#fdc02f', '#50ae54', '#33d1bd'],
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: false,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:sampleclassify
        }
    ]
};
chart12.setOption(chart12Option);