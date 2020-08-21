<!DOCTYPE html>
<html style="text-align: center;height: 100%;" lang="en">
<head>
    <meta charset="UTF-8">
    <title>开票通知模版</title>
</head>
<body style="height: 100%;">
<div style="height: 100%;width: 40%;text-align: left;padding-top: 7%;padding-left: 9%;">
    <div>您开具了财政电子票据，票据信息如下：</div>
    <br>
    <div>开票日期：${fCreateTime}</div>
    <div>开票单位：${fplaceName}</div>
    <div>票据类型：${fbillType}</div>
    <div>票据代码：${fbillId}</div>
    <div>票据号码：${fbillNo}</div>
    <div>校验码：${fcheckCode}</div>
    <div>交款人：${fpayerName}</div>
    <div>总金额：${ftotalAmt} 元</div>
    <img  src="${fbillImgUrl}"/>
</div>
</body>
</html>