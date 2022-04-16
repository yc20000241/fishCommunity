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
    COMMENT_NOT_EXIST("此评论不存在", 2013)
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
