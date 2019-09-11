package com.btjf.model.emp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ScoreExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_score
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_score
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_score
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public ScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_score
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_score
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andYearMonthIsNull() {
            addCriterion("yearMonth is null");
            return (Criteria) this;
        }

        public Criteria andYearMonthIsNotNull() {
            addCriterion("yearMonth is not null");
            return (Criteria) this;
        }

        public Criteria andYearMonthEqualTo(String value) {
            addCriterion("yearMonth =", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthNotEqualTo(String value) {
            addCriterion("yearMonth <>", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthGreaterThan(String value) {
            addCriterion("yearMonth >", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthGreaterThanOrEqualTo(String value) {
            addCriterion("yearMonth >=", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthLessThan(String value) {
            addCriterion("yearMonth <", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthLessThanOrEqualTo(String value) {
            addCriterion("yearMonth <=", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthLike(String value) {
            addCriterion("yearMonth like", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthNotLike(String value) {
            addCriterion("yearMonth not like", value, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthIn(List<String> values) {
            addCriterion("yearMonth in", values, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthNotIn(List<String> values) {
            addCriterion("yearMonth not in", values, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthBetween(String value1, String value2) {
            addCriterion("yearMonth between", value1, value2, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andYearMonthNotBetween(String value1, String value2) {
            addCriterion("yearMonth not between", value1, value2, "yearMonth");
            return (Criteria) this;
        }

        public Criteria andEmpIdIsNull() {
            addCriterion("empId is null");
            return (Criteria) this;
        }

        public Criteria andEmpIdIsNotNull() {
            addCriterion("empId is not null");
            return (Criteria) this;
        }

        public Criteria andEmpIdEqualTo(Integer value) {
            addCriterion("empId =", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotEqualTo(Integer value) {
            addCriterion("empId <>", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThan(Integer value) {
            addCriterion("empId >", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("empId >=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThan(Integer value) {
            addCriterion("empId <", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThanOrEqualTo(Integer value) {
            addCriterion("empId <=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdIn(List<Integer> values) {
            addCriterion("empId in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotIn(List<Integer> values) {
            addCriterion("empId not in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdBetween(Integer value1, Integer value2) {
            addCriterion("empId between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("empId not between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNull() {
            addCriterion("empName is null");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNotNull() {
            addCriterion("empName is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNameEqualTo(String value) {
            addCriterion("empName =", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotEqualTo(String value) {
            addCriterion("empName <>", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThan(String value) {
            addCriterion("empName >", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("empName >=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThan(String value) {
            addCriterion("empName <", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThanOrEqualTo(String value) {
            addCriterion("empName <=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLike(String value) {
            addCriterion("empName like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotLike(String value) {
            addCriterion("empName not like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameIn(List<String> values) {
            addCriterion("empName in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotIn(List<String> values) {
            addCriterion("empName not in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameBetween(String value1, String value2) {
            addCriterion("empName between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotBetween(String value1, String value2) {
            addCriterion("empName not between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andFiveScoreIsNull() {
            addCriterion("fiveScore is null");
            return (Criteria) this;
        }

        public Criteria andFiveScoreIsNotNull() {
            addCriterion("fiveScore is not null");
            return (Criteria) this;
        }

        public Criteria andFiveScoreEqualTo(BigDecimal value) {
            addCriterion("fiveScore =", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreNotEqualTo(BigDecimal value) {
            addCriterion("fiveScore <>", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreGreaterThan(BigDecimal value) {
            addCriterion("fiveScore >", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fiveScore >=", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreLessThan(BigDecimal value) {
            addCriterion("fiveScore <", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fiveScore <=", value, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreIn(List<BigDecimal> values) {
            addCriterion("fiveScore in", values, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreNotIn(List<BigDecimal> values) {
            addCriterion("fiveScore not in", values, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fiveScore between", value1, value2, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andFiveScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fiveScore not between", value1, value2, "fiveScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreIsNull() {
            addCriterion("coordinationScore is null");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreIsNotNull() {
            addCriterion("coordinationScore is not null");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreEqualTo(BigDecimal value) {
            addCriterion("coordinationScore =", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreNotEqualTo(BigDecimal value) {
            addCriterion("coordinationScore <>", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreGreaterThan(BigDecimal value) {
            addCriterion("coordinationScore >", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coordinationScore >=", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreLessThan(BigDecimal value) {
            addCriterion("coordinationScore <", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coordinationScore <=", value, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreIn(List<BigDecimal> values) {
            addCriterion("coordinationScore in", values, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreNotIn(List<BigDecimal> values) {
            addCriterion("coordinationScore not in", values, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coordinationScore between", value1, value2, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andCoordinationScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coordinationScore not between", value1, value2, "coordinationScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIsNull() {
            addCriterion("qualityScore is null");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIsNotNull() {
            addCriterion("qualityScore is not null");
            return (Criteria) this;
        }

        public Criteria andQualityScoreEqualTo(BigDecimal value) {
            addCriterion("qualityScore =", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotEqualTo(BigDecimal value) {
            addCriterion("qualityScore <>", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreGreaterThan(BigDecimal value) {
            addCriterion("qualityScore >", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qualityScore >=", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreLessThan(BigDecimal value) {
            addCriterion("qualityScore <", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qualityScore <=", value, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreIn(List<BigDecimal> values) {
            addCriterion("qualityScore in", values, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotIn(List<BigDecimal> values) {
            addCriterion("qualityScore not in", values, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qualityScore between", value1, value2, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andQualityScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qualityScore not between", value1, value2, "qualityScore");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<BigDecimal> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<BigDecimal> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("isDelete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("isDelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("isDelete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("isDelete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("isDelete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDelete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("isDelete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("isDelete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("isDelete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("isDelete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("isDelete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("isDelete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreIsNull() {
            addCriterion("checkworkScore is null");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreIsNotNull() {
            addCriterion("checkworkScore is not null");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreEqualTo(BigDecimal value) {
            addCriterion("checkworkScore =", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreNotEqualTo(BigDecimal value) {
            addCriterion("checkworkScore <>", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreGreaterThan(BigDecimal value) {
            addCriterion("checkworkScore >", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("checkworkScore >=", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreLessThan(BigDecimal value) {
            addCriterion("checkworkScore <", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("checkworkScore <=", value, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreIn(List<BigDecimal> values) {
            addCriterion("checkworkScore in", values, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreNotIn(List<BigDecimal> values) {
            addCriterion("checkworkScore not in", values, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("checkworkScore between", value1, value2, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andCheckworkScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("checkworkScore not between", value1, value2, "checkworkScore");
            return (Criteria) this;
        }

        public Criteria andYearMonthLikeInsensitive(String value) {
            addCriterion("upper(yearMonth) like", value.toUpperCase(), "yearMonth");
            return (Criteria) this;
        }

        public Criteria andEmpNameLikeInsensitive(String value) {
            addCriterion("upper(empName) like", value.toUpperCase(), "empName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_score
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_score
     *
     * @mbg.generated
     */
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