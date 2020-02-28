package cc.smcoco.community.model;

public class User {
    private Integer Id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
