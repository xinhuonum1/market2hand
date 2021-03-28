<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>二手商城找回密码</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/layui.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/layer.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
<div style="width: 100%;height: 60px;background-color: #1ABC9C;position: relative">
    <span style="padding-left: 10%;position: absolute;top:20px;font-size: 24px;color: whitesmoke;font-family: 华光行楷_CNKI">二手交易商城|<span style="font-size: 20px">修改密码</span></span>
</div>
   <div class="container">
        <div class="main center clearfix">
            <form class="layui-form" action="/home/index/updateUserPassword" style="padding-left: 20%">
                <div class="layui-form-item">
                    <label class="layui-form-label">注册邮箱：</label>
                    <div class="layui-input-inline" >
                        <input type="text" class="layui-input" name="stuemail" required lay-verify="required" autocomplete="off">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ><span style="color: #ff342f">请填写您注册时的电子邮件</span></div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码：</label>
                    <div class="layui-input-inline" >
                        <input type="password" class="layui-input" name="password" required lay-verify="required" autocomplete="off">
                    </div>
                    <div class="layui-form-mid layui-word-aux"><span style="color: #ff342f">8-20位，必须包含字母和数字，区分大小写</span></div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码：</label>
                    <div class="layui-input-inline" >
                        <input type="password" class="layui-input" name="truePassword" required lay-verify="required" autocomplete="off">
                    </div>
                    <div class="layui-form-mid layui-word-aux" ><span style="color: #ff342f">重复输入密码</span></div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码：</label>
                    <div class="layui-input-inline" >
                        <input type="text" class="layui-input" name="cpata" required lay-verify="required" autocomplete="off">
                    </div>
                    <div class="layui-form-mid layui-word-aux" >
                        <img src="/cpacha/generate_cpacha?vl=4&fs=25&w=128&h=40&method=admin_login" id="captcha" onclick="this.src=this.src+'&d='+Math.random();" style="cursor: pointer;"  title="点击刷新" alt="captcha">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">确定</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
  <script  src="/home/js/jquery-3.1.1.min.js"></script>
  <script src="/home/js/common.js"></script>
  <script src="/home/js/index.js"></script>
  <script src="/admin/js/bootstrap.min.js"></script>
  <script src="/home/js/layui.all.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   
});

</script>
</body>
</html>