<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${siteName!""}</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/want_list.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/layui.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/layer.css" type="text/css" rel="stylesheet">
</head>
<body>
<#include "../common/top_header.ftl"/>
<#include "../common/left_menu.ftl"/>
<div class="container">
    <div class="main center clearfix">
        <div class="want-title"></div>
        <div class="wrap-want-list">
            <ul class="want-list" id="want-list">
                <#if pageBean.content?size gt 0>
                    <#list pageBean.content as news>
                        <li class="want-item">
                            <div class="want-li clearfix">
                                <div>
                                    <#if news.status == 1>
                                        <h4 class="want-name">【系统公告】
                                        </h4>
                                        <p class="want-detail">${news.content}</p>
                                    </#if>
                                    <#if news.status == 0>
                                        <h4 class="want-name">【订单消息】
                                        </h4>
                                        <p class="want-detail">${news.content}</p>
                                    </#if>
                                    <#if news.status == 2>
                                        <h4 class="want-name">【评论消息】
                                        </h4>
                                        <p class="want-detail">${news.content}</p>
                                    </#if>
                                    <p class="want-attr">
                                        <span>${news.createTime}</span>
                                    </p>
                                </div>
                            </div>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
        <#if pageBean.total gt 0>
            <!-- 分页 开始 -->
            <div class="pages">
                <#if pageBean.currentPage == 1>
                    <a class="page-arrow arrow-left" href="javascript:void(0)">首页</a>
                <#else>
                    <a class="page-arrow arrow-left" href="list?currentPage=1">首页</a>
                </#if>
                <#list pageBean.currentShowPage as showPage>
                    <#if pageBean.currentPage == showPage>
                        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
                    <#else>
                        <a class="page-num " href="list?currentPage=${showPage}">${showPage}</a>
                    </#if>
                </#list>
                <#if pageBean.currentPage == pageBean.totalPage>
                    <a class="page-arrow arrow-right" href="javascript:void(0)">尾页</a>
                <#else>
                    <a class="page-arrow arrow-right" href="list?currentPage=${pageBean.totalPage}">尾页</a>
                </#if>
            </div>
            <!-- 分页 结束 -->
        </#if>
    </div>
</div>
<div class="return-to-top"><a href="#"></a></div><!--返回顶部-->
<#include "../common/right_menu.ftl"/>
<#include "../common/bottom_footer.ftl"/>
<script src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/index.js"></script>
<script src="/home/js/layui.all.js"></script>
<script src="/home/js/layer.js"></script>
<script type="text/javascript">

</script>
</body>
</html>