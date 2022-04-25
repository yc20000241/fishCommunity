package com.fish.community.demo.model;

public class Userinfo {
    private Long id;

    private Long userId;

    private Integer sex;

    private Integer pageViews;

    private Integer focusOnCount;

    private String imgUrl;

    private Integer followedCount;

    private String sign;

    private String nick;

    private String gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Integer getFocusOnCount() {
        return focusOnCount;
    }

    public void setFocusOnCount(Integer focusOnCount) {
        this.focusOnCount = focusOnCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(Integer followedCount) {
        this.followedCount = followedCount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", sex=").append(sex);
        sb.append(", pageViews=").append(pageViews);
        sb.append(", focusOnCount=").append(focusOnCount);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", followedCount=").append(followedCount);
        sb.append(", sign=").append(sign);
        sb.append(", nick=").append(nick);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append("]");
        return sb.toString();
    }
}