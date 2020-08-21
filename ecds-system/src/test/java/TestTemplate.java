import com.bosssoft.ecds.system.entity.dto.AlarmMessageDto;
import com.bosssoft.ecds.system.util.FreeMarkerUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 模板测试类
 * @author: lin.wanning
 * @date: Created in 2020/8/21 13:16
 * @modified By:
 */
public class TestTemplate {
    public static void main(String[] args) {
        List<AlarmMessageDto> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AlarmMessageDto alarmMessageDto = new AlarmMessageDto();
            alarmMessageDto.setAlarmMessage("test_" + i);
            alarmMessageDto.setName("name_" + i);
            alarmMessageDto.setStartTime(new Date(1597974864510L));
            list.add(alarmMessageDto);
        }
        Map map = new HashMap();
        map.put("alarmMessageList", list);
        String template = FreeMarkerUtil.getTemplate("systemWarnTemplate.ftl");
        String content = FreeMarkerUtil.generateTemplate(template, map);
        System.out.println(content);

    }
}
