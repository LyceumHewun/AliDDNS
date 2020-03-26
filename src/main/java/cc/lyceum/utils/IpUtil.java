package cc.lyceum.utils;

import cn.hutool.log.StaticLog;
import com.google.gson.JsonParser;
import org.jsoup.helper.StringUtil;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:34
 */
public class IpUtil {

    /**
     * 通过访问 https://www.wtfismyip.com/json 获取当前网络环境的外网IP地址
     *
     * @return 外网IP地址
     */
    public static String getExtranetsIP() {
        String json = HttpUtil.getBody("https://www.wtfismyip.com/json");

        if (StringUtil.isBlank(json)) {
            StaticLog.error("获取外网IP失败");
            throw new RuntimeException("获取外网IP失败");
        }

        return JsonParser.parseString(json)
                .getAsJsonObject()
                .get("YourFuckingIPAddress").getAsString();
    }
}
