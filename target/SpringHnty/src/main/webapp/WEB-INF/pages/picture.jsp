<%--
  Created by IntelliJ IDEA.
  User: 24709
  Date: 2021/5/6
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>今日校园图片生成</title>
    <meta name="keywords" content="今日校园图片生成">
    <meta name="description" content="今日校园图片生成。">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
            text-decoration: none;
            font-family: 微软雅黑;
            box-sizing: border-box;
        }
        body{
            min-height: 100vh;
            background-image: linear-gradient(120deg,#7ed6df,#4834d4);
        }
        .login-form{
            width:540px;
            background: #f1f1f1;
            padding: 80px 40px;
            height: 500px;
            border-radius: 10px;
            position: absolute;
            left:50%;
            top: 50%;
            transform: translate(-50%,-50%);
        }
        .login-form h2{
            text-align: center;
            margin-bottom: 60px;
        }

        .txtb{
            border-bottom: 2px solid #adadad;
            position: relative;
            margin: 30px 0;
        }

        .txtb input{
            font-size: 15px;
            color: #333;
            border: none;
            width: 100%;
            outline: none;
            background: none;
            padding: 0 5px;
            height: 40px;
        }
        .txtb span::before{
            content: attr(data-placeholder);
            position: absolute;
            top: 50%;
            left:5px;
            color: #adadad;
            transform: translateY(-50%);
            z-index: -1;
            transition: .5s;
        }

        .txtb span::after{
            content: '';
            position: absolute;
            width: 100%;
            height: 2px;
            background-image: linear-gradient(120deg,#7ed6df,#4834d4);
            left:0;
            bottom: -2px;
            transition: .5s;
        }
        .focus + span::before{
            top: -5px;
        }
        .focus + span::after{
            width: 100%;
            bottom: -2px;
            left:0;
        }
        .logbtn{

            display: block;
            width: 100%;
            height: 50px;
            border: none;
            background-image: linear-gradient(120deg,#4834d4,#7ed6df,#4834d4);
            background-size: 200%;
            color: #fff;
            transition: .5s;
            cursor: pointer;
        }

        .logbtn:hover{
            background-position: right;
        }

        .bottom-text{
            margin-top: 30px;
            text-align: center;
            font-size: 13px;
        }
        .txt_QA{
            float:left;
            margin-top: 15px;
        }
        .txt_register{
            float: right;
            margin-top: 15px;
        }
        a{
            color: #747d8c;
        }
    </style>
</head>
<body>
<form action="api/user.php?s=login" class="login-form" method="post">
    <h2>今日校园图片生成</h2>
    <div class="txtb">
        <input type="text" name="name" id="name">
        <span data-placeholder="账号"></span>
    </div>
    <div class="txtb">
        <input type="password" name="password" id="password">
        <span data-placeholder="密码"></span>
    </div>
    <input type="submit" class="logbtn" value="登录">
    <div class="bottom-text">
        <p style="color:blue">面朝大海，春暖花开</p>
        </br>
        <p style="color:green;text-align:center"></p>
        <p></p><div class="txt_QA">
        <a href="javascript:void(0);" target="_black">使用必看</a>
    </div>
        <div class="txt_register">
            <a href="javascript:void(0);" target="_black">使用必看</a>
        </div>

    </div>
</form>
<script>
    $(".txtb input").on("focus",function(){
        $(this).addClass("focus");
    });
    $(".txtb input").on("blur",function(){
        if($(this).val() == "")
            $(this).removeClass("focus");
    });
</script>

</body>
</html>