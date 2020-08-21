<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
</head>

<body style="font-family: SimSun">
    <table style="border-collapse:collapse;">
        <tr style="height: 0px;">
            <td colspan="1" rowspan="1" style="text-align: left; width: 110px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 130px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
            <td colspan="1" rowspan="1" style="text-align: left; width: 95px;"></td>
        </tr>
        <tr>
            <td colspan="9" rowspan="1" style="text-align: center;font-size: 20px;height: 35px;" valign="center">中央非税收入统一票据（电子）</td>
        </tr>
        <tr>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;" valign="center">票据代码：</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.billCode)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">校验码：</td>
            <td colspan="3" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.checkCode)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">票据号码：</td>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.serialCode)!' '}</td>
        </tr>
        <tr>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;" valign="center">缴款人：</td>
            <td colspan="5" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.payerName)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">开票日期：</td>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.date)!' '}</td>
        </tr>
        <tr>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;height: 35px;border:2px solid black;" valign="center">项目编码</td>
            <td colspan="3" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">项目名称</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">单位</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">数量</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">标准</td>
            <td colspan="2" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">金额（元）</td>
        </tr>
        <#list billDTO.items as item>
            <tr>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;height: 35px;border:2px solid black;" valign="center">${(item.itemCode)!' '}</td>
            <td colspan="3" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">${(item.itemName)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">${(item.units)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">${(item.quantity)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">${(item.standardName)!' '}</td>
            <td colspan="2" rowspan="1" style="text-align: center;font-size: 16px;border:2px solid black;" valign="center">${(item.amount)!' '}</td>
            </tr>
        </#list>
        <tr>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;border:2px solid black;" valign="center">金额合计（小写）</td>
            <td colspan="7" rowspan="1" style="text-align: left;font-size: 16px;border:2px solid black;" valign="center">${(billDTO.totalAmount)!' '}</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;border:2px solid black;" valign="center">金额合计（大写）</td>
            <td colspan="7" rowspan="1" style="text-align: left;font-size: 16px;border:2px solid black;" valign="center">${(billDTO.totalAmountCapital)!' '}</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;border:2px solid black;" valign="center">备注：</td>
            <td colspan="7" rowspan="1" style="text-align: left;font-size: 16px;border:2px solid black;" valign="center">${(billDTO.remark)!' '}</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;border:2px solid black;" valign="center">附加说明：</td>
            <td colspan="7" rowspan="1" style="text-align: left;font-size: 16px;border:2px solid black;" valign="center">${(billDTO.addition)!' '}</td>
        </tr>
        <tr>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;height: 35px;" valign="center">收款单位（公章）：</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.agenName)!' '}</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">复核人：</td>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.checker)!' '}</td>
            <td colspan="2" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">收款人（公章）：</td>
            <td colspan="1" rowspan="1" style="text-align: left;font-size: 16px;" valign="center">${(billDTO.payee)!' '}</td>
        </tr>
    </table>

</body>

</html>