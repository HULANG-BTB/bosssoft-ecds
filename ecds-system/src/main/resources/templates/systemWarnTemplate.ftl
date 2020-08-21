<!DOCTYPE html>
<html style="text-align: center;height: 100%;" lang="en">
<head>
    <meta charset="UTF-8">
    <title>微服务链路监控</title>
</head>
<body style="height: 100%;">
<div style="height: 100%;width: 40%;text-align: left;padding-top: 7%;padding-left: 9%;">
    <div>微服务链路监控异常信息如下：</div>
    <br>
    <#list  alarmMessageList  as alarm >
        <div>
            <h4>告警${alarm_index}</h4>
            <div>时间：${alarm.startTime ?string('yyyy-mm-dd HH:mm:ss')}</div>
            <div>服务名称：${alarm.name}</div>
            <div>告警信息：${alarm.alarmMessage}</div>
        </div>
    </#list>


</div>
</body>
</html>