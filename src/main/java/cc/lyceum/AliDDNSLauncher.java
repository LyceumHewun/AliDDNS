package cc.lyceum;

import cc.lyceum.bean.Config;
import cc.lyceum.utils.AliDNSUtil;
import cc.lyceum.utils.ConfigUtil;
import cc.lyceum.utils.IpUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.StaticLog;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;

import java.math.BigDecimal;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 2:30
 */
public class AliDDNSLauncher {

    public static void main(String[] args) {
        // 入参
//        args
        ThreadUtil.execute(AliDDNSLauncher::mainThread);
    }

    private static void mainThread() {
        while (true) {
            Config config = ConfigUtil.getConfig();

            try {
                String extranetsIP = IpUtil.getExtranetsIP();
                StaticLog.info("当前外网IP: {}", extranetsIP);

                DescribeDomainRecordsResponse.Record record = AliDNSUtil.getDescribeDomainRecords(config.getDomain(), config.getRR());
                if (null == record) {
                    boolean result = AliDNSUtil.addDomainRecord(config.getDomain(), config.getRR(), "A", extranetsIP, config.getTTL());
                    StaticLog.info("添加解析记录: {}", result ? "成功" : "失败");
                } else if (isNonEquals(record.getValue(), extranetsIP)) {
                    boolean result = AliDNSUtil.updateDomainRecord(record.getRecordId(), config.getRR(), "A", extranetsIP, config.getTTL());
                    StaticLog.info("更新解析记录: {}", result ? "成功" : "失败");
                }

            } catch (Exception e) {
                StaticLog.error("抛出异常: {}", e);
            } finally {
                StaticLog.info("等待{}秒...", BigDecimal.valueOf(config.getRefreshTime())
                        .divide(BigDecimal.valueOf(1000), 3, BigDecimal.ROUND_HALF_UP)
                        .toPlainString());
                ThreadUtil.sleep(config.getRefreshTime());
            }
        }
    }

    private static boolean isNonEquals(String str0, String str1) {
        if (str0.equals(str1)) {
            StaticLog.info("外网IP与解析记录相同");
            return false;
        }
        return true;
    }
}
