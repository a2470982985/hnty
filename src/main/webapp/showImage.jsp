<%--
  Created by IntelliJ IDEA.
  User: 24709
  Date: 2021/5/6
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>今日校园图片保存页面</title>
</head>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<style>

    * {
        margin: 0;
        padding: 0;
        text-decoration: none;
        font-family: 微软雅黑;
        box-sizing: border-box;
    }

    body {
        min-height: 100vh;
        background-image: linear-gradient(120deg, #7ed6df, #4834d4);
    }

    .login-form {
        width: 540px;
        background: #f1f1f1;
        padding: 30px 40px;
        height: 500px;
        border-radius: 10px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .login-form h2 {
        text-align: center;
        margin-bottom: 40px;
    }

    .txtb {
        border-bottom: 2px solid #adadad;
        position: relative;
        margin: 30px 0;
    }

    .txtb input {
        font-size: 15px;
        color: #333;
        border: none;
        width: 100%;
        outline: none;
        background: none;
        padding: 0 5px;
        height: 40px;
    }

    .txtb span::before {
        content: attr(data-placeholder);
        position: absolute;
        top: 50%;
        left: 5px;
        color: #adadad;
        transform: translateY(-50%);
        z-index: -1;
        transition: .5s;
    }

    .txtb span::after {
        content: '';
        position: absolute;
        width: 100%;
        height: 2px;
        background-image: linear-gradient(120deg, #7ed6df, #4834d4);
        left: 0;
        bottom: -2px;
        transition: .5s;
    }

    .focus + span::before {
        top: -5px;
    }

    .focus + span::after {
        width: 100%;
        bottom: -2px;
        left: 0;
    }

    .logbtn {

        display: block;
        width: 100%;
        height: 50px;
        border: none;
        background-image: linear-gradient(120deg, #4834d4, #7ed6df, #4834d4);
        background-size: 200%;
        color: #fff;
        transition: .5s;
        cursor: pointer;
    }

    .logbtn:hover {
        background-position: right;
    }

    a {
        color: #747d8c;
    }

    img {

        width: 100%;
        height: 300px;
        border: 1px dashed #ccc;
        display: table-cell;
    / / 主要是这个属性 vertical-align: middle;
        text-align: center;
        margin-bottom: 20px;
    }
</style>
<body>

<div class="login-form">
    <h2>今日校园图片生成</h2>
    <img id="image" src="data:image/jpg;base64,<%=session.getAttribute("base64")%>">
    <input type="submit" onclick="download()" class="logbtn" value="保存">
</div>

</body>
<script>


    <%
        if (session.getAttribute("base64")==null || session.getAttribute("base64")==""){
            response.sendRedirect("/picture.html");
        }
    %>
    let base64Image = "data:image/jpg;base64,<%=session.getAttribute("base64")%>";


        $(".txtb input").on("focus", function () {
            $(this).addClass("focus");
        });
        $(".txtb input").on("blur", function () {
            if ($(this).val() == "")
                $(this).removeClass("focus");
        });

        function download() {
            this.downloadFile('image.jpg', base64Image);
        }

        //下载
        function downloadFile(fileName, content) {
            let aLink = document.createElement('a');
            let blob = this.base64ToBlob(content); //new Blob([content]);

            let evt = document.createEvent("HTMLEvents");
            evt.initEvent("click", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
            aLink.download = fileName;
            aLink.href = URL.createObjectURL(blob);

            // aLink.dispatchEvent(evt);
            aLink.click()
        }

        //base64转blob
        function base64ToBlob(code) {
            let parts = code.split(';base64,');
            let contentType = parts[0].split(':')[1];
            let raw = window.atob(parts[1]);
            let rawLength = raw.length;

            let uInt8Array = new Uint8Array(rawLength);

            for (let i = 0; i < rawLength; ++i) {
                uInt8Array[i] = raw.charCodeAt(i);
            }
            return new Blob([uInt8Array], {type: contentType});
        }

</script>

</html>