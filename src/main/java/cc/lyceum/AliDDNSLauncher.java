package cc.lyceum;

import cc.lyceum.bean.Config;
import cc.lyceum.utils.AliDNSUtil;
import cc.lyceum.utils.ConfigUtil;
import cc.lyceum.utils.IpUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import cn.hutool.log.level.Level;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:30
 */
public class AliDDNSLauncher {

    public static void main(String[] args) {
        // 入参
//        args
        // 设置日志级别
        LogFactory.get().isEnabled(Level.INFO);
        ThreadUtil.execute(AliDDNSLauncher::mainThread);
    }

    private static void mainThread() {
        Config config = ConfigUtil.getConfig();

        ThreadUtil.sleep(config.getRefreshTime());

        String extranetsIP = IpUtil.getExtranetsIP();
        StaticLog.info("当前外网IP: {}", extranetsIP);

        DescribeDomainRecordsResponse.Record record = AliDNSUtil.getDescribeDomainRecords(config.getDomain(), config.getRR());
        if (null == record) {
            boolean result = AliDNSUtil.addDomainRecord(config.getDomain(), config.getRR(), "A", extranetsIP, config.getTTL());
            StaticLog.info("添加解析记录: {}", result ? "成功" : "失败");
        } else {
            boolean result = AliDNSUtil.updateDomainRecord(record.getRecordId(), config.getRR(), "A", extranetsIP, config.getTTL());
            StaticLog.info("更新解析记录: {}", result ? "成功" : "失败");
        }
    }
}
