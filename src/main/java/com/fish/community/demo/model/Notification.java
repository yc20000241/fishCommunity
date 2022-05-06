package com.fish.community.demo.model;

public class Notification {
    private Long id;

    private Long affectId;

    private Long actionId;

    private Integer sign;

    private String gmtCreate;

    private Integer kind;

    private String content;

    private Long articleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffectId() {
        return affectId;
    }

    public void setAffectId(Long affectId) {
        this.affectId = affectId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", affectId=").append(affectId);
        sb.append(", actionId=").append(actionId);
        sb.append(", sign=").append(sign);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", kind=").append(kind);
        sb.append(", content=").append(content);
        sb.append(", articleId=").append(articleId);
        sb.append("]");
        return sb.toString();
    }
}