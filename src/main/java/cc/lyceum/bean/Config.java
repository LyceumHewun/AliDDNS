package cc.lyceum.bean;

/**
 * @author Lyceum Hewun
 * @date 2020-03-27 3:08
 */
public class Config {

    /**
     * 多长时间(毫秒)刷新一次DNS, 该时间建议不要低于TTL
     */
    private long refreshTime;
    /**
     * 阿里云用户 AccessKey ID
     * 设置地址https://usercenter.console.aliyun.com/
     */
    private String aliyunAccessKeyId;
    /**
     * 阿里云用户 AccessKey Secret
     */
    private String aliyunAccessKeySecret;
    /**
     * 域名
     */
    private String domain;
    /**
     * 域名主机记录(小写)
     */
    private String RR;
    /**
     * 解析生效时间(秒, 600-86400)
     */
    private long TTL;

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getAliyunAccessKeyId() {
        return aliyunAccessKeyId;
    }

    public void setAliyunAccessKeyId(String aliyunAccessKeyId) {
        this.aliyunAccessKeyId = aliyunAccessKeyId;
    }

    public String getAliyunAccessKeySecret() {
        return aliyunAccessKeySecret;
    }

    public void setAliyunAccessKeySecret(String aliyunAccessKeySecret) {
        this.aliyunAccessKeySecret = aliyunAccessKeySecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain.toLowerCase();
    }

    public String getRR() {
        return RR;
    }

    public void setRR(String RR) {
        this.RR = RR;
    }

    public long getTTL() {
        return TTL;
    }

    public void setTTL(long TTL) {
        this.TTL = TTL;
    }
}
