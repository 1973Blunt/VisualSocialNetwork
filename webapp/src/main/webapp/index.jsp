<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <script type='text/javascript' src='springy-master/jquery.min.js'></script>
    <script type='text/javascript' src="springy-master/springy.js"></script>
    <script type='text/javascript' src="springy-master/springyui.js"></script>
    <script type='text/javascript' src="js/index.js"></script>
    <style>
        body {
            text-align: center
        }
        div {
            margin: 0 auto
        }
    </style>
</head>
<body>
    <div>
        <h2>微博粉丝关系图</h2>
        <input type="text" id="name">
        <input type="button" id="button" value="查询"><br/>
    </div>
    <div>
        <canvas id="springydemo" width="1280" height="960"/>
    </div>

</body>

</html>
