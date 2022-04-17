package com.fish.community.demo.model;

public class UserRelation {
    private Long id;

    private Long focusOnUser;

    private Long followedUser;

    private Integer isFocus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFocusOnUser() {
        return focusOnUser;
    }

    public void setFocusOnUser(Long focusOnUser) {
        this.focusOnUser = focusOnUser;
    }

    public Long getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(Long followedUser) {
        this.followedUser = followedUser;
    }

    public Integer getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(Integer isFocus) {
        this.isFocus = isFocus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", focusOnUser=").append(focusOnUser);
        sb.append(", followedUser=").append(followedUser);
        sb.append(", isFocus=").append(isFocus);
        sb.append("]");
        return sb.toString();
    }
}