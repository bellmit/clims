<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/12/19
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="linkCss.jsp" %>
    <style>
        body > .row > div {
            background: #fff;
            border-radius: 5px;
            margin-top: 20px;
            padding: 15px 0;
            box-shadow: 0px 0px 10px 5px #f3f3f3;
        }
        body > .row > div:nth-child(1) .col-md-2 {
            width: 20%;
        }
        body > .row > div:nth-child(1) .col-md-2 p {
            height: 95px;
            line-height: 95px;
            font-size: 14px;
            text-align: center;
            margin: 0px;
        }

        body > .row > div:nth-child(1) .col-md-2 p span {
            font-weight: 600;
            font-size: 28px;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(1) p {
            color: #ff5a56;
            background: #fff5f5;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(2) p {
            color: #ffb72c;
            background: #fffbf3;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(3) p {
            color: #3c88ff;
            background: #f3f8ff;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(4) p {
            color: #1bb29b;
            background: #f2faf9;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(5) p {
            color: #ff5a56;
            background: #fff5f5;
        }

        body > .row > div:nth-child(1) .col-md-2:nth-child(6) p {
            color: #1bb29b;
            background: #f2faf9;
        }

        body > .row > div:nth-child(2) {
            height: 300px;
            padding-right: 10px;
            background: none;
        }

        body > .row > div:nth-child(3) {
            height: 300px;
            padding-left: 10px;
            background: none;
        }

        body > .row > div:nth-child(2) div {
            height: 100%;
        }

        body > .row > div:nth-child(2) > div,
        body > .row > div:nth-child(3) > div {
            background: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 5px #f3f3f3;
        }

        body > .row > div:nth-child(3) div {
            height: 100%;
        }

        body > .row > div:nth-child(3) > div p {
            position: absolute;
            position: absolute;
            bottom: 20px;
            left: 50%;
            margin-left: -27px;
            font-weight: 500;
        }

        body > .row > div:nth-child(3) > div p span {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 50%;
            margin-right: 5px;
        }

        #caseClassification,
        #sampleClassification {
            height: 330px;
        }

    </style>

</head>

<body>
<div class="row">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2">
                <p>未受理<span>${cnt1}</span>件</p>
            </div>
            <div class="col-md-2">
                <p>待检验<span>${cnt2}</span>件</p>
            </div>
            <div class="col-md-2">
                <p>未出鉴定书<span>0</span>件</p>
            </div>
            <div class="col-md-2">
                <p>待签发鉴定书<span>0</span>件</p>
            </div>
            <div class="col-md-2">
                <p>待领取鉴定书<span>0</span>件</p>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="col-md-6 nopadding" id="caseNum">
            </div>
            <div class="col-md-6" id="sampleNum">
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="row">
            <div class="col-md-12 nopadding">
                <div class="col-md-12 nopadding" id="detectionRate">
                </div>
                <p><span style="background: #ff5a56;"></span>检出率</p>
            </div>
            <%--<div class="col-md-6 nopadding">
                <div class="col-md-12" id="effectRate">
                </div>
                <p><span style="background: #1bb29b;"></span>作用率</p>
            </div>--%>
        </div>
    </div>
    <div class="col-md-12 nopadding" id="caseClassification"></div>
    <div class="col-md-12 nopadding" id="sampleClassification"></div>
</div>
<%@ include file="linkJs.jsp" %>
<script>
    $(function () {
        var caseNum = echarts.init(document.getElementById('caseNum'));
        var caseNumOption = {
            title: [{
                text: '案件/检材数',
                left: 'left',
                top: 'top',
                textStyle: {
                    color: '#1681F5',
                    fontSize: 15,
                },
                backgroundColor: '#f3f9ff',
                padding: [10, 30],
                borderRadius: [0, 0, 10, 0],
            }, {
                text: ${cnt9},
                x: 'center',
                y: 'center',
                textStyle: {
                    fontWeight: 'normal',
                    color: '#9bbe0f',
                    fontSize: '30'
                }
            }],
            legend: {
                selectedMode:false,
                itemWidth: 10,
                itemHeight: 10,
                itemGap: 10,
                show: true,
                bottom: 30,
                icon: "circle",
                borderRadius: '100',
                data: ['案件数'],
                textStyle: {
                    fontSize: 14,
                    color: '#000'
                }
            },
            color: ['#d9d9d9'],
            series: [{
                name: 'Line 1',
                type: 'pie',
                clockWise: true,
                radius: ['45%', '55%'],
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                },
                hoverAnimation: false,
                data: [{
                    value: 70,
                    name: '案件数',
                    itemStyle: {
                        normal: {
                            color: { // 完成的圆环的颜色
                                colorStops: [{
                                    offset: 0,
                                    color: '#9ec017' // 0% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#c7e93f' // 100% 处的颜色
                                }]
                            },
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    }
                }, {
                    name: '02',
                    value: 30
                }]
            }]
        };
        caseNum.setOption(caseNumOption);

        var sampleNum = echarts.init(document.getElementById('sampleNum'));
        var sampleNumOption = {
            title: {
                text: ${snt1},
                x: 'center',
                y: 'center',
                textStyle: {
                    fontWeight: 'normal',
                    color: '#346fec',
                    fontSize: '30'
                }
            },
            color: ['#d9d9d9'],
            legend: {
                selectedMode:false,
                itemWidth: 10,
                itemHeight: 10,
                itemGap: 10,
                show: true,
                bottom: 30,
                icon: "circle",
                borderRadius: '100',
                data: ['检材数'],
                textStyle: {
                    fontSize: 14,
                    color: '#000'
                }
            },

            series: [{
                name: 'Line 1',
                type: 'pie',
                clockWise: true,
                radius: ['45%', '55%'],
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                },
                hoverAnimation: false,
                data: [{
                    value: 70,
                    name: '检材数',
                    itemStyle: {
                        normal: {
                            color: {
                                colorStops: [{
                                    offset: 0,
                                    color: '#5d61fe'
                                }, {
                                    offset: 1,
                                    color: '#077fd8'
                                }]
                            },
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    }
                }, {
                    name: '02',
                    value: 30
                }]
            }]
        };
        sampleNum.setOption(sampleNumOption);

        var detectionRate = echarts.init(document.getElementById('detectionRate'));
        var detectionRateOption = {
            title: {
                text: '检出率',
                left: 'left',
                top: 'top',
                textStyle: {
                    color: '#1681F5',
                    fontSize: 15,
                },
                backgroundColor: '#f3f9ff',
                padding: [10, 30],
                borderRadius: [0, 0, 10, 0],
            },
            series: [{
                type: 'liquidFill',
                radius: '50%',
                center: ['50%', '50%'],
                data: [{
                    name: '检出率',
                    value: 0.6
                }, 0.5, 0.4],
                color: ['#ffd5d4', '#ffafae ', '#fea19d '],
                itemStyle: {
                    normal: {
                        shadowBlur: 0,
                        opacity: 0.6
                    }
                },
                backgroundStyle: {
                    color: '#fff'
                },
                outline: {
                    borderDistance: 0,
                    itemStyle: {
                        borderWidth: 8,
                        borderColor: '#ff5a56',
                        shadowBlur: 20,
                    }
                },
                label: {
                    normal: {
                        /*formatter: [
                            '{textBorder|' + <%--${snt2}--%> + '%}',
                        ].join('\n'),*/
                        formatter: '${snt3}',
                        rich: {
                            textBorder: {
                                fontSize: 28,
                                color: '#cd0500',
                                insideColor: '#a86f00',
                                fontWeight: "bold"
                            },
                        }
                    }
                }
            }]
        };
        detectionRate.setOption(detectionRateOption);

        /*var effectRate = echarts.init(document.getElementById('effectRate'));
        var effectRateOption = {
            series: [{
                type: 'liquidFill',
                center: ['50%', '50%'],
                data: [0.6, 0.5, 0.4],
                radius: '50%',
                color: ['#c5ece6', '#91dacf ', '#7fd4c7 '],
                itemStyle: {
                    normal: {
                        shadowBlur: 0,
                        opacity: 0.6
                    }
                },
                backgroundStyle: {
                    color: '#fff'
                },
                outline: {
                    borderDistance: 0,
                    itemStyle: {
                        borderWidth: 8,
                        borderColor: "#1bb29b",
                        shadowBlur: 20,
                    }
                },
                label: {
                    normal: {
                        formatter: [
                            '{textBorder|' + 0 + '%}',
                        ].join('\n'),
                        rich: {
                            textBorder: {
                                fontSize: 28,
                                color: '#008671',
                                insideColor: '#a86f00',
                                fontWeight: "bold"
                            },
                        }
                    }
                }
            }]
        };
        effectRate.setOption(effectRateOption);*/

        var caseClassificationData = ${caseJson}
        var caseClassificationDataX = [],
                caseClassificationDataY = [];
        caseClassificationData.map(function (a, b) {
            if (b == 0) {
                caseClassificationDataX.push("");
                caseClassificationDataY.push({
                    value: '',
                    textStyle: {
                        padding: [0, 0, 0, 0],
                    }
                });
            }
            caseClassificationDataX.push(a.NAME);
            caseClassificationDataY.push(a.VALUE);
        });

        var caseClassification = echarts.init(document.getElementById('caseClassification'));
        var caseClassificationOption = {
            title: {
                text: '案件分类',
                left: 'left',
                top: 'top',
                textStyle: {
                    color: '#1681F5',
                    fontSize: 15,
                },
                backgroundColor: '#f3f9ff',
                padding: [10, 30],
                borderRadius: [0, 0, 10, 0],
            },
            grid: {
                left: '5%',
                right: '5%',
                bottom: '5%',
                top: '10%',
                height: '85%',
                containLabel: true,
                z: 22
            },
            xAxis: [{
                type: 'category',
                gridIndex: 0,
                data: caseClassificationDataX,
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                axisLabel:{
                    interval:0,//0：全部显示，1：间隔为1显示对应类目，2：依次类推，（简单试一下就明白了，这样说是不是有点抽象）
                    rotate:-30,//倾斜显示，-：顺时针旋转，+或不写：逆时针旋转
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999', //x轴辅助线颜色
                        type: 'dashed'
                    }
                },
                boundaryGap: false,
            }, {
                type: 'category',
                data: caseClassificationDataY,
                axisLabel: {
                    show: true,
                    backgroundColor:'#f6fafe',
                    textStyle: {
                        color: "#1681F5",
                        padding: [4, 10, 4, 10],
                        borderRadius:10,
                    },
//                    formatter: function (value) {
//                        if (value !== "") {
//                            return '{Sunny|' + value + '}';
//                        } else {
//                            return '{no|' + value + '}';
//                        }
//                    },
//                    rich: {
//                        value: {
//                            lineHeight: 30,
//                        },
//                        Sunny: {
//                            width: 20,
//                            height: 10,
//                            padding: 5,
//                            borderRadius: 40,
//                            align: 'center',
//                            backgroundColor: "#f6fafe",
//                            color: "#1681F5"
//                        },
//                        no: {
//                            width: 0,
//                            height: 0,
//                            padding: 0,
//                            borderRadius: 40,
//                            align: 'center',
//                            backgroundColor: "#000",
//                            color: "#FF5C45"
//                        }
//                    }
                },
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: 'transparent'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999', //x轴辅助线颜色
                        type: 'dashed'
                    }
                },
                boundaryGap: false,
            }],
            yAxis: [{
                type: 'value',
                gridIndex: 0,
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                axisLabel: {
                    color: '#999999'
                }
            },],
            series: [{
                name: '案件占比',
                type: 'bar',
                barWidth: '30%',
                xAxisIndex: 0,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        barBorderRadius: [30, 30, 0, 0],
                        color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#00feff'
                                },
                                    {
                                        offset: 0.5,
                                        color: '#027eff'
                                    },
                                    {
                                        offset: 1,
                                        color: '#0286ff'
                                    }
                                ]
                        )
                    }
                },
                data: caseClassificationDataY,
            }]
        };
        caseClassification.setOption(caseClassificationOption);

        console.log(${sampleJson})
        var sampleClassificationData = ${sampleJson}

        var sampleClassificationDataX = [],
                sampleClassificationDataY = [];
        sampleClassificationData.map(function (a, b) {
            if (b == 0) {
                sampleClassificationDataX.push("");
                sampleClassificationDataY.push("");
            }
            sampleClassificationDataX.push(a.NAME);
            sampleClassificationDataY.push(a.VALUE);
        });

        var sampleClassification = echarts.init(document.getElementById('sampleClassification'));
        var sampleClassificationOption = {
            title: {
                text: '检材分类',
                left: 'left',
                top: 'top',
                textStyle: {
                    color: '#FF5A56',
                    fontSize: 15,
                },
                backgroundColor: '#fff6f6',
                padding: [10, 30],
                borderRadius: [0, 0, 10, 0],
            },
            grid: {
                left: '5%',
                right: '5%',
                bottom: '5%',
                top: '10%',
                height: '85%',
                containLabel: true,
                z: 22
            },
            xAxis: [{
                type: 'category',
                gridIndex: 0,
                data: sampleClassificationDataX,
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999', //x轴辅助线颜色
                        type: 'dashed'
                    }
                },
                boundaryGap: false,
            }, {
                type: 'category',
                data: sampleClassificationDataY,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: "#000",
                    },
                    formatter: function (value) {
                        if (value !== "") {
                            return '{Sunny|' + value + '}';
                        } else {
                            return '{no|' + value + '}';
                        }
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
                            backgroundColor: "#fff9f8",
                            color: "#FF5C45"
                        },
                        no: {
                            width: 0,
                            height: 0,
                            padding: 0,
                            borderRadius: 40,
                            align: 'center',
                            backgroundColor: "#000",
                            color: "#FF5C45"
                        }
                    }
                },
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: 'transparent'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#999', //x轴辅助线颜色
                        type: 'dashed'
                    }
                },
                boundaryGap: false,
            }],
            yAxis: [{
                type: 'value',
                gridIndex: 0,
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                axisLabel: {
                    color: '#999999'
                }
            },],
            series: [{
                name: '案件占比',
                type: 'bar',
                barWidth: '30%',
                xAxisIndex: 0,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        barBorderRadius: [30, 30, 0, 0],
                        color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1, [
                                    {
                                        offset: 0,
                                        color: '#FFDB45'
                                    }, {
                                        offset: 1,
                                        color: '#FF5A45'
                                    }
                                ]
                        )
                    }
                },
                data: sampleClassificationDataY,
            }]
        };
        sampleClassification.setOption(sampleClassificationOption);
    })
</script>
</body>

</html>
