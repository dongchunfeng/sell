<html>
<#include "../common/header.ftl">

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
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().message == "在架">
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
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
                        <#if productInfoPage.getTotalPages() lte 5>
                            <#list 1..productInfoPage.getTotalPages() as index>
                                <#if currPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                        </#if>

                        <#if productInfoPage.getTotalPages() gt 5>
                            <#if currPage lte 3>
                                <#list 1..5 as index>
                                    <#if currPage == index>
                                        <li class="disabled"><a href="#">${index}</a></li>
                                    <#else>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                            </#if>

                            <#if currPage+2 gte productInfoPage.getTotalPages()>
                                <#list productInfoPage.getTotalPages()-4..productInfoPage.getTotalPages() as index>
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

                        <#if currPage gte productInfoPage.getTotalPages()>
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
</body>
</html>