<html>
    <#include "../common/header.ftl" >
<body>

<div id="wrapper" class="toggled">
    <#-- 边栏 -->
    <#include "../common/nav.ftl" >
    <#--  主要内容 -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>名字</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPages.content as orderDTO >
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>${orderDTO.updateTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <!--
                            始终保证当前页在中间，一共显示5页
                            1.总页码<=5时，显示所有页数
                            2.总页码>5时：
                                当前页码<=3时：显示1~5页
                                当前页码>3时：显示当前页在中间的5页,当前页为倒数前3页时，显示最后5页
                        -->
                        <#if orderDTOPages.getTotalPages() lte 5>
                            <#list 1..orderDTOPages.getTotalPages() as index>
                                <#if currPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                        </#if>

                        <#if orderDTOPages.getTotalPages() gt 5>
                            <#if currPage lte 3>
                                <#list 1..5 as index>
                                    <#if currPage == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                            </#if>
                            <!-- 如果当前页+2 >= 总页数  显示最后5页  否则>=3页的话就显示前后2页 -->
                            <#if currPage+2 gte orderDTOPages.getTotalPages()>
                                <#list orderDTOPages.getTotalPages()-4..orderDTOPages.getTotalPages() as index>
                                    <#if currPage == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                            <#else >
                                <#if currPage gt 3>
                                    <#list currPage-2..currPage+2 as index>
                                        <#if currPage == index>
                                            <li class="disabled"><a href="#">${index}</a></li>
                                        </#if>
                                        <#if currPage != index>
                                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                        </#if>
                                    </#list>
                                </#if>
                            </#if>

                        </#if>

                        <#if currPage gte orderDTOPages.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://sells.natappvip.cc/sell/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    };

    websocket.onclose = function (event) {
        console.log('连接关闭');
    };

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data);
        //弹窗提醒, 播放音乐
        $('#myModal').modal('show');
        document.getElementById('notice').play();
    };

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    };

    window.onbeforeunload = function () {
        websocket.close();
    }


</script>


</body>
</html>