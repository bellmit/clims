$(function () {

    //获取name=bgColor 的所有的div
    var bColor = new Array("#fdf5f5", "#fefaf2", "#f5f9ff", "#f3fbfa");
    var color = new Array("#d23838", "#e59700", "#3c88ff", "#00b39b");
    var boxColor = new Array("#fae8e8", "#fcf2dd", "#ddf0fd", "#dff4f1");

    $("div[name='bgColor']").each(function (i) {
        $(this).css({
            "background": bColor[i],
            "color": color[i],
            "box-shadow": '5px 5px 5px ' + boxColor[i] + ''
        });
    });

    obtainAllData("");//首次加载当前年数据
    layui.use('form', function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
           var year=data.value;
            obtainAllData(year);
        })
    });



    //获取所有数据  年 季度 月
    function obtainAllData(obj) {
        var monthData=[];
        var yearData = []; //年统计
        var year = [];//年数
        var quarterData = []; //季度统计数据
        $.ajax({
            url: baseurl+"/home/getObtainAllData.html?year=" + obj,
            type: "get",
            dataType: "json",
            success: function (data) {
                /*月统计*/
               // var json =JSON.stringify(data.month);
                $.each(data.month,function (i,val) {
                    monthData.push(val)
                });
                var month = echarts.init(document.getElementById('month'));
                monthOption = {
                    title: {
                        text: '月统计',
                        left: 'left',
                        top: 'top',
                        textStyle: {
                            color: '#999999',
                            fontSize: 15,
                        },
                        backgroundColor: '#f2f2f2',
                        width: 100,
                        padding: [10, 30],
                        borderRadius: [0, 0, 10, 0],
                    },
                    color: ['#3db7ff'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'line'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        gridIndex: 0,
                        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        axisTick: {
                            alignWithLabel: true
                        },
                    }, {
                        type: 'category',
                        data: monthData,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: "#000",
                            },
                            formatter: function (value) {
                                return '{Sunny|' + value + '}';
                            },
                            rich: {
                                value: {
                                    lineHeight: 30,
                                },
                                Sunny: {
                                    width: 50,
                                    height: 15,
                                    padding: 5,
                                    borderRadius: 40,
                                    align: 'center',
                                    backgroundColor: "#f2f2f2"
                                }
                            }
                        },
                    }],
                    yAxis: [{
                        type: 'value',
                        show: false,
                    }],
                    series: [{
                        name: '111',
                        type: 'bar',
                        barWidth: '60%',
                        tooltip: {
                            show: false
                        },
                        data: monthData
                    }],
                };
                month.setOption(monthOption);
                /*年统计*/
                $.each(data.year,function (i,val) {
                    year.push(i);
                    yearData.push(val);
                });
                var yearsEcharts = echarts.init(document.getElementById('year'));
                yearOption = {
                    title: {
                        text: '年度统计',
                        left: 'left',
                        top: 'top',
                        textStyle: {
                            color: '#999999',
                            fontSize: 15,
                        },
                        backgroundColor: '#f2f2f2',
                        width: 100,
                        padding: [10, 30],
                        borderRadius: [0, 0, 10, 0],
                    },
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
                        data: ['年统计'],
                        bottom: '2%',
                        selectedMode: 'single',
                    },
                    toolbox: {
                        show: true,
                        right: '3%',
                        feature: {
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        boundaryGap: false,
                        data: year
                    }],
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        name: '年统计',
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
                                    color: 'RGBA(0,179,155,.8)'
                                }, {
                                    offset: 1,
                                    color: 'RGBA(0,179,155,.2)'
                                }], false)
                            }
                        },
                        smooth: true,
                        itemStyle: {
                            normal: {
                                areaStyle: {
                                    type: 'default'
                                },
                                color: '#49c6b6',
                                borderColor: '#49c6b6',
                                borderWidth: 1
                            }
                        },
                        data: yearData
                    }]
                };
                yearsEcharts.setOption(yearOption);
                /*季度统计*/
                $.each(data.period,function (i,val) {
                    quarterData.push(val);
                });
                var quarter1 = echarts.init(document.getElementById('quarter1'));
                var quarter2 = echarts.init(document.getElementById('quarter2'));
                var quarter3 = echarts.init(document.getElementById('quarter3'));
                var quarter4 = echarts.init(document.getElementById('quarter4'));
                quarterOption1 = {
                    title: {
                        text: '季度统计',
                        left: 'left',
                        top: 'top',
                        textStyle: {
                            color: '#999999',
                            fontSize: 15,
                        },
                        backgroundColor: '#f2f2f2',
                        width: 100,
                        padding: [10, 30],
                        borderRadius: [0, 0, 10, 0],
                    },
                    series: [{
                        type: 'liquidFill',
                        name: 'Liquid Fill',
                        radius: '70%',
                        data: [0.68, 0.5, 0.4, 0.3],
                        color: ['RGBA(255,184,2,.5)', 'RGBA(255,230,176,.5)', 'RGBA(255,184,2,.1)'],
                        outline: {
                            show: false
                        },
                        backgroundStyle: {
                            borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset:0.42 ,
                                color: '#ededed'
                            }, {
                                offset: 0.42,
                                color: '#ffb802'
                            }]),
                            borderWidth: 15,
                            shadowColor: "#fff"
                        },
                        label: {
                            normal: {
                                formatter: quarterData[0] + '件',
                                color: '#a86f00',
                                insideColor: '#a86f00',
                                fontSize: 50,
                            }
                        }
                    }]
                };
                quarterOption2 = {
                    series: [{
                        type: 'liquidFill',
                        name: 'Liquid Fill',
                        radius: '70%',
                        data: [0.65, 0.5, 0.4],
                        color: ['RGBA(118,194,243,.5)', 'RGBA(166,216,248,.5)', 'RGBA(194,228,250,.5)'],
                        outline: {
                            show: false
                        },
                        backgroundStyle: {
                            borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0.45,
                                color: '#ededed'
                            }, {
                                offset: 0.45,
                                color: '#0091e9'
                            }]),
                            borderWidth: 10,
                            shadowColor: "#fff"
                        },
                        label: {
                            normal: {
                                formatter: quarterData[1] + '件',
                                color: '#0065aa',
                                insideColor: '#0065aa',
                                fontSize: 50,
                            }
                        }
                    }]
                };
                quarterOption3 = {
                    series: [{
                        type: 'liquidFill',
                        name: 'Liquid Fill',
                        radius: '70%',
                        data: [0.7, 0.5, 0.4,],
                        color: ['RGBA(255,162,156,.5)', 'RGBA(255,177,174,.5)', 'RGBA(255,214,212,.5)'],
                        outline: {
                            show: false
                        },
                        backgroundStyle: {
                            borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0.40,
                                color: '#ededed'
                            }, {
                                offset: 0.40,
                                color: '#ff5951'
                            }]),
                            borderWidth: 10,
                            shadowColor: "#fff"
                        },
                        label: {
                            normal: {
                                formatter: quarterData[2] + '件',
                                color: '#cf0000',
                                insideColor: '#cf0000',
                                fontSize: 50,
                            }
                        }
                    }]
                };
                quarterOption4 = {
                    series: [{
                        type: 'liquidFill',
                        name: 'Liquid Fill',
                        radius: '70%',
                        data: [0.6, 0.5, 0.4, 0.3],
                        color: ['RGBA(125,212,200,.5)', 'RGBA(143,218,207,.5)', 'RGBA(197,236,230,.5)'],
                        outline: {
                            show: false
                        },
                        backgroundStyle: {
                            borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0.51,
                                color: '#ededed'
                            }, {
                                offset: 0.51,
                                color: '#00b39b'
                            }]),
                            borderWidth: 10,
                            shadowColor: "#fff"
                        },
                        label: {
                            normal: {
                                formatter: quarterData[3] + '件',
                                color: '#008771',
                                insideColor: '#008771',
                                fontSize: 50,
                            }
                        }
                    }]
                };
                quarter1.setOption(quarterOption1);
                quarter2.setOption(quarterOption2);
                quarter3.setOption(quarterOption3);
                quarter4.setOption(quarterOption4);

            }
        });

    };

});