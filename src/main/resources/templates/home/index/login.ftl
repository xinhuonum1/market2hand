<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>欢迎登录${siteName!""}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="/home/css/login2.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/home/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/home/js/login.js"></script>
    <link media="all" href="/home/css/layer.css" rel="stylesheet">
    <link media="all" href="/home/css/layui.css" rel="stylesheet">

</head>
<body>
<h1 style="color: #4BC0A5">${siteName!""}<sup>2020</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;">
            </div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <!--登录-->
        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <div id="loginCue" class="cue">欢迎登陆</div>

                    <form action="login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm"
                          method="post">
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="id">账号</label>
                            <div class="inputOuter" id="uArea">
                                <input type="text" id="login-sn" name="account" class="inputstyle"
                                       placeholder="请输入邮箱地址或用户名"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="pwd">密码</label>
                            <div class="inputOuter" id="pArea">

                                <input type="password" id="pwd" name="password" class="inputstyle" placeholder="请输入密码"/>
                            </div>
                        </div>

                        <div class="forgetPwdArea">
                            <a href="#" class="forgetPwd" onclick="forgetPwd()">忘记密码?</a>
                        </div>

                        <div style="padding-left:50px;margin-top:10px;">
                            <button id="login_button" type="button" value="登 录" style="width:150px;" class="button_blue"
                                    href="javascript::"/>
                            登录</button>
                            <!-- <input id="login_button"type="submit" value="登 录" style="width:150px;" class="button_blue"/> -->
                        </div>
                    </form>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">

        <div class="web_login">
            <form name="form2" id="regForm" accept-charset="utf-8" action="register" method="post">
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li>

                        <label for="user" class="input-tips2">学号：</label>
                        <div class="inputOuter2">
                            <input type="text" id="user-sn" name="sn" maxlength="12" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="password" name="password" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="password2" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="qq" class="input-tips2">QQ：</label>
                        <div class="inputOuter2">

                            <input type="text" id="qq" name="qq" maxlength="10" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <div class="inputArea">
                            <input type="button" id="reg_button" style="margin-top:10px;margin-left:85px;"
                                   class="button_blue" value="注册成为会员"/>
                        </div>

                    </li>
                    <div class="cl"></div>
                </ul>
            </form>


        </div>


    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>


<script src="/home/js/layer.js"></script>
<script src="/home/js/layui.all.js"></script>
<script>
    function forgetPwd() {
        layer.open({
            title: '忘记密码',
            btn: ['确认', '取消']
            , area: ['460px', '260px']
            , content: '<div class="layui-inline" style="margin-left: 110px">' +
                '<span id=mySpan style="text-size:18pt;color:red;"></span></div>' +
                '<div class="layui-inline" style="margin-bottom: 15px">' +
                            '<label class="layui-form-label">邮箱</label>' +
                '               <div class="layui-input-inline">' +
                '                   <input type="text" name="email" id="emailVal" ' +
                '                           placeholder="请输入注册时的邮箱" ' +
                                            'autocomplete="off" ' +
                                            'class="layui-input" style="width: 290px"><div id="alertMsg1"></div>' +
                '                   </div></div>' +
                '           <div class="layui-inline"><label class="layui-form-label">验证码</label>' +
                                '      <div class="layui-input-inline">' +
                                '<input type="text" name="verifyCode" id="verifyCode" ' +
                                'placeholder="请输入验证码" ' +
                                'autocomplete="off" ' +
                                'class="layui-input" style="width: 160px;float: left"></div>' +
                '<div class="col-xs-5" style="float: right">' +
                '            <img src="/cpacha/generate_cpacha?vl=4&fs=25&w=128&h=40&method=admin_login" id="captcha" onclick="this.src=this.src+\'&d=\'+Math.random();" style="cursor: pointer;"  title="点击刷新" alt="captcha">' +
                '          </div></div>'
            , yes: function (index) {
                let emailVal = $("#emailVal").val();
                let verifyCode = $("#verifyCode").val();
                if(emailVal.left != 0 && verifyCode != 0){
                let data=[];
                    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
                data[0] = emailVal;
                    data[1]=verifyCode
                    if(emailVal.match(myReg)){
                        $.ajax({
                            type:'post',
                            url:'forgetPwd'
                            ,data:{forgetPwd:data}
                            , traditional:true
                            ,success:function (resp) {
                                //查看数据库是否有该邮箱地址 如果有 则提交 如果无 则提示未注册
                                if(null != resp){
                                    if(resp.code == -10006){
                                        alert(resp.msg);
                                        layer.close(index);
                                        return;
                                    }
                                    alert(resp.msg);
                                }

                            }
                        });

                    }else{
                       document.getElementById("mySpan").innerText = "请输入正确格式的邮箱地址"
                    }
                }else{
                    document.getElementById("mySpan").innerText = "邮箱或验证码不能为空"
                }

            },
            btn2:function (index,layero) {
                layer.close(index);
            }
        });
    };


</script>

</body>
</html>