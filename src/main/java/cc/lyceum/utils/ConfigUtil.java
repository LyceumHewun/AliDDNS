package cc.lyceum.utils;

import cc.lyceum.bean.Config;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.setting.Setting;

import java.io.File;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:47
 */
public class ConfigUtil {

    /**
     * 配置文件路径
     */
    private static final String CONFIG_FILE_PATH = "./config.setting";

    private Setting setting;

    private Config config;

    ConfigUtil() {
        StaticLog.info("正在读取(config.setting)配置文件");

        File configFile = new File(CONFIG_FILE_PATH);
        if (FileUtil.exist(configFile)) {
            StaticLog.error("配置文件不存在");
        }

        setting = new Setting(configFile, CharsetUtil.CHARSET_UTF_8, true);
        // 配置文件变更时自动加载
        setting.autoLoad(true, isLoadSuccess -> {
            if (isLoadSuccess) {
                config = setting.toBean(Config.class);
            }
        });

        try {
            config = setting.toBean(Config.class);
        } catch (Exception e) {
            StaticLog.error("读取配置文件失败");
            throw new RuntimeException(e);
        }
    }

    private static ConfigUtil instance;

    static synchronized ConfigUtil getInstance() {
        if (null == instance) {
            instance = new ConfigUtil();
        }
        return instance;
    }

    public static Config getConfig() {
        return getInstance().config;
    }
}
