package com.fish.community.demo.model;

public class Articles {
    private Long id;

    private String title;

    private String content;

    private String gmtCreateTime;

    private String gmtModifiedTime;

    private Long author;

    private Integer viewCount;

    private Integer likeCount;

    private Integer tag;

    private String articleImg;

    private Integer dislikeCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(String gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

    public String getGmtModifiedTime() {
        return gmtModifiedTime;
    }

    public void setGmtModifiedTime(String gmtModifiedTime) {
        this.gmtModifiedTime = gmtModifiedTime;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", gmtCreateTime=").append(gmtCreateTime);
        sb.append(", gmtModifiedTime=").append(gmtModifiedTime);
        sb.append(", author=").append(author);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", tag=").append(tag);
        sb.append(", articleImg=").append(articleImg);
        sb.append(", dislikeCount=").append(dislikeCount);
        sb.append("]");
        return sb.toString();
    }
}