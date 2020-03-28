package cc.lyceum.utils;

import cc.lyceum.bean.Config;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

import java.util.List;
import java.util.Objects;

/**
 * 阿里云 DNS 服务器接口工具
 *
 * @author Lyceum Hewun
 * @date 2020-03-27 3:03
 */
public class AliDNSUtil {

    /**
     * 固定值
     */
    private static final String REGION_ID = "cn-hangzhou";

    private IAcsClient client;

    AliDNSUtil() {
        Config config = ConfigUtil.getConfig();
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, config.getAliyunAccessKeyId(), config.getAliyunAccessKeySecret());
        client = new DefaultAcsClient(profile);
    }

    private static AliDNSUtil instance;

    static synchronized AliDNSUtil getInstance() {
        if (null == instance) {
            instance = new AliDNSUtil();
        }
        return instance;
    }

    /**
     * 根据域名和主机记录获取解析记录(一条)
     * https://help.aliyun.com/document_detail/29776.html
     *
     * @param domain 域名
     * @param rR     主机记录
     * @return 解析记录
     */
    public static DescribeDomainRecordsResponse.Record getDescribeDomainRecords(String domain, String rR) {
        IAcsClient client = getInstance().client;

        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName(domain);
        request.setPageSize(500L);
        request.setRRKeyWord(rR);
        request.setSearchMode("EXACT");

        try {
            DescribeDomainRecordsResponse response = client.getAcsResponse(request);
            // 删除所有主机记录不等于RR的
            List<DescribeDomainRecordsResponse.Record> records = response.getDomainRecords();
            records.removeIf(record -> !rR.equals(record.getRR()));
            return records.size() == 1 ? records.get(0) : null;
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加解析记录
     * https://help.aliyun.com/document_detail/29772.html
     *
     * @param domain 域名
     * @param rR     主机记录
     * @param type   解析记录类型
     * @param value  记录值
     * @param TTL    解析生效时间(秒)
     * @return 成功与否
     */
    public static boolean addDomainRecord(String domain, String rR, String type, String value, long TTL) {
        IAcsClient client = getInstance().client;

        AddDomainRecordRequest request = new AddDomainRecordRequest();
        request.setDomainName(domain);
        request.setRR(rR);
        request.setType(type);
        request.setValue(value);
        request.setTTL(TTL);

        try {
            AddDomainRecordResponse response = client.getAcsResponse(request);
            // 勉强判断
            return null != response;
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改解析记录
     * https://help.aliyun.com/document_detail/29774.html
     *
     * @param recordId 解析记录ID
     * @param rR       主机记录
     * @param type     解析记录类型(https://help.aliyun.com/document_detail/29805.html)
     * @param value    记录值
     * @param TTL      解析生效时间(秒, 阿里云默认600)
     * @return 成功与否
     */
    public static boolean updateDomainRecord(String recordId, String rR, String type, String value, long TTL) {
        IAcsClient client = getInstance().client;

        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        request.setRecordId(recordId);
        request.setRR(rR);
        request.setType(type);
        request.setValue(value);
        request.setTTL(TTL);

        try {
            UpdateDomainRecordResponse response = client.getAcsResponse(request);
            return null != response;
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }
}
