package com.fish.community.demo.exception;

public enum BusinessExceptionCode {

    QQ_VERIFICATION_TOKEN_WRONG("验证码token错误", 2000),
    QQ_VERIFICATION_EXPIRED("验证码过期", 2001),
    EMAIL_HAVEN_REGISTERED("邮箱已被注册",2002),
    QQ_VERIFICATION_WRONG("验证码错误",2003),
    EMAIL_FORMAT_WRONG("邮箱格式错误", 2004),
    VERIFICATION_SEND_FAILED("验证码发送失败", 2005),
    USER_NOT_REGISTER("用户未注册", 2006),
    ARTICLE_NOT_EXIst("文章不存在", 2007),
    FILE_SAVE_FILED("文件上传失败，文件大小不能超过400KB", 2008),
    UPLOAD_FILE_NOT_EMPTY("上传文件不能为空",2009),
    FILE_PATH_NOT_EXIST("文件路径不存在", 2010),
    COMMENTOR_NOT_EXISTS("评论人不存在", 2011),
    COMMENT_ARTICLE_NOT_EXISTS("评论文章不存在", 2012),
    COMMENT_NOT_EXIST("此评论不存在", 2013),
    FILE_KIND_NOT_EXIST("此文件分类不存在",2014),
    USERID_OF_MOIDFY_INFO("密码不存在", 2015),
    PASSWORD_WRONG_OF_MODIFY_USEINFO("修改用户信息--原密码输入错误",2016),
    USER_IMAGE_URL_NOT_EXIST("用户头像图片路径不存在", 2017),
    USERID_OF_GET_INFO_NOT_EXIST("获取用户信息--用户id不存在",2018),
    FOCUS_ON_USER_NOT_EXIST("关注者不存在", 2019),
    FOLLOWED_USER_NOT_EXIST("被关注者不存在", 2020),
    NOTFOCUS_AND_BEFORE_NOTFOCUS("已是未关注状态", 2021),
    FOCUSED_AND_FOCUSING("已是关注状态", 2022),
    PARENT_COMMENT_NOT_EXIST("父级评论不存在", 2023),
    TODAY_HAS_LIKEN_THE_ARTICLE("今天已经和这篇文章互动过了~", 2024),
    MODIFY_USERINFO_NEW_PASSOWRD_EMPTY("新密码不可为空",2025),
    ARTICLE_TAG_NOT_EXIST("该文章标签不存在", 2026),
    CHAT_MESSAGE_FORMAT_WRONG("聊天消息格式错误",2027),
    JOIN_A_GROUP_BEFORE_CHAT("聊天前请先加入一个组", 2028)
    ;

    private String desc;

    private int statuCode;

    BusinessExceptionCode(String desc, int statuCode) {
        this.desc = desc;
        this.statuCode = statuCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(int statuCode) {
        this.statuCode = statuCode;
    }
}
