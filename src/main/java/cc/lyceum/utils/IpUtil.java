package cc.lyceum.utils;

import cn.hutool.log.StaticLog;
import org.jsoup.helper.StringUtil;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:34
 */
public class IpUtil {

    /**
     * 通过访问 https://www.wtfismyip.com/text 获取当前网络环境的外网IP地址
     *
     * @return 外网IP地址
     */
    public static String getExtranetsIP() {
        String ip = HttpUtil.getBody("https://www.wtfismyip.com/text");

        if (StringUtil.isBlank(ip)) {
            StaticLog.error("获取外网IP失败");
            throw new RuntimeException("获取外网IP失败");
        }

        return ip;
    }
}
