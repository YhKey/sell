<html>
<#include "../common/head.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDto>
                            <tr>
                                <td>${orderDto.orderId}</td>
                                <td>${orderDto.buyerName}</td>
                                <td>${orderDto.buyerPhone}</td>
                                <td>${orderDto.buyerAddress}</td>
                                <td>${orderDto.orderAmount}</td>
                                <td>${orderDto.getOrderStatusEnum().getMessage()}</td>
                                <td>${orderDto.getPayStatusEnum().getMessage()}</td>
                                <td>${orderDto.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a></td>
                                <td>
                                    <#if orderDto.getOrderStatusEnum().message =="新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
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
                        <#--当前页小于等于1-->
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#--getTotalPages()返回数字，1到总数-->
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#--当前页大于等于总数-->
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>`
                            <li><a href="list?page=${currentPage + 1}&size=${size}">下一页</a></li>
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
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                您有新订单
            </div>
            <div class="modal-footer">
<#--                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>-->
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg"/>
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket=null;
    /*在window对象中有，说明浏览器支持webSocket*/
    if ('webSocket' in window){
        webSocket =new WebSocket('ws://localhost:9999/sell/websocket');
    }else {
        alert('浏览器不支持');
    }

    webSocket.onopen = function (event) {
        console.log('建立连接');
    }
    webSocket.onclose = function (event) {
        console.log('连接关闭');
    }
    webSocket.onmessage = function (event) {
        console.log('收到消息：'+ event.data);
        //弹窗
        $('#myModal').modal('show');
        //播放音乐
        // document.getElementById('notice').play();
    }
    webSocket.onerror = function () {
        alert('webSocket通信错误');
    }
    window.onbeforeunload = function () {
        webSocket.close();
    }

</script>

</body>
</html>
