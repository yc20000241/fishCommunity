package com.fish.community.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ArticlesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticlesExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIsNull() {
            addCriterion("gmt_create_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIsNotNull() {
            addCriterion("gmt_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeEqualTo(String value) {
            addCriterion("gmt_create_time =", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotEqualTo(String value) {
            addCriterion("gmt_create_time <>", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeGreaterThan(String value) {
            addCriterion("gmt_create_time >", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_create_time >=", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeLessThan(String value) {
            addCriterion("gmt_create_time <", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("gmt_create_time <=", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeLike(String value) {
            addCriterion("gmt_create_time like", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotLike(String value) {
            addCriterion("gmt_create_time not like", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIn(List<String> values) {
            addCriterion("gmt_create_time in", values, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotIn(List<String> values) {
            addCriterion("gmt_create_time not in", values, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeBetween(String value1, String value2) {
            addCriterion("gmt_create_time between", value1, value2, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotBetween(String value1, String value2) {
            addCriterion("gmt_create_time not between", value1, value2, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeIsNull() {
            addCriterion("gmt_modified_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeIsNotNull() {
            addCriterion("gmt_modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeEqualTo(String value) {
            addCriterion("gmt_modified_time =", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeNotEqualTo(String value) {
            addCriterion("gmt_modified_time <>", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeGreaterThan(String value) {
            addCriterion("gmt_modified_time >", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_modified_time >=", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeLessThan(String value) {
            addCriterion("gmt_modified_time <", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeLessThanOrEqualTo(String value) {
            addCriterion("gmt_modified_time <=", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeLike(String value) {
            addCriterion("gmt_modified_time like", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeNotLike(String value) {
            addCriterion("gmt_modified_time not like", value, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeIn(List<String> values) {
            addCriterion("gmt_modified_time in", values, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeNotIn(List<String> values) {
            addCriterion("gmt_modified_time not in", values, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeBetween(String value1, String value2) {
            addCriterion("gmt_modified_time between", value1, value2, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedTimeNotBetween(String value1, String value2) {
            addCriterion("gmt_modified_time not between", value1, value2, "gmtModifiedTime");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(Long value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(Long value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(Long value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(Long value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(Long value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(Long value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<Long> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<Long> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(Long value1, Long value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(Long value1, Long value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNull() {
            addCriterion("like_count is null");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNotNull() {
            addCriterion("like_count is not null");
            return (Criteria) this;
        }

        public Criteria andLikeCountEqualTo(Integer value) {
            addCriterion("like_count =", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotEqualTo(Integer value) {
            addCriterion("like_count <>", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThan(Integer value) {
            addCriterion("like_count >", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_count >=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThan(Integer value) {
            addCriterion("like_count <", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThanOrEqualTo(Integer value) {
            addCriterion("like_count <=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIn(List<Integer> values) {
            addCriterion("like_count in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotIn(List<Integer> values) {
            addCriterion("like_count not in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountBetween(Integer value1, Integer value2) {
            addCriterion("like_count between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("like_count not between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(Integer value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(Integer value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(Integer value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(Integer value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(Integer value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(Integer value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<Integer> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<Integer> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(Integer value1, Integer value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(Integer value1, Integer value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andArticleImgIsNull() {
            addCriterion("article_img is null");
            return (Criteria) this;
        }

        public Criteria andArticleImgIsNotNull() {
            addCriterion("article_img is not null");
            return (Criteria) this;
        }

        public Criteria andArticleImgEqualTo(String value) {
            addCriterion("article_img =", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotEqualTo(String value) {
            addCriterion("article_img <>", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgGreaterThan(String value) {
            addCriterion("article_img >", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgGreaterThanOrEqualTo(String value) {
            addCriterion("article_img >=", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLessThan(String value) {
            addCriterion("article_img <", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLessThanOrEqualTo(String value) {
            addCriterion("article_img <=", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgLike(String value) {
            addCriterion("article_img like", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotLike(String value) {
            addCriterion("article_img not like", value, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgIn(List<String> values) {
            addCriterion("article_img in", values, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotIn(List<String> values) {
            addCriterion("article_img not in", values, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgBetween(String value1, String value2) {
            addCriterion("article_img between", value1, value2, "articleImg");
            return (Criteria) this;
        }

        public Criteria andArticleImgNotBetween(String value1, String value2) {
            addCriterion("article_img not between", value1, value2, "articleImg");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIsNull() {
            addCriterion("dislike_count is null");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIsNotNull() {
            addCriterion("dislike_count is not null");
            return (Criteria) this;
        }

        public Criteria andDislikeCountEqualTo(Integer value) {
            addCriterion("dislike_count =", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotEqualTo(Integer value) {
            addCriterion("dislike_count <>", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountGreaterThan(Integer value) {
            addCriterion("dislike_count >", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("dislike_count >=", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountLessThan(Integer value) {
            addCriterion("dislike_count <", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountLessThanOrEqualTo(Integer value) {
            addCriterion("dislike_count <=", value, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountIn(List<Integer> values) {
            addCriterion("dislike_count in", values, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotIn(List<Integer> values) {
            addCriterion("dislike_count not in", values, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountBetween(Integer value1, Integer value2) {
            addCriterion("dislike_count between", value1, value2, "dislikeCount");
            return (Criteria) this;
        }

        public Criteria andDislikeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("dislike_count not between", value1, value2, "dislikeCount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}