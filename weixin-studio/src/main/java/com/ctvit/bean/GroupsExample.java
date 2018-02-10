package com.ctvit.bean;

import java.util.ArrayList;
import java.util.List;

public class GroupsExample extends Groups {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupsExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andGroupsidIsNull() {
            addCriterion("groupsId is null");
            return (Criteria) this;
        }

        public Criteria andGroupsidIsNotNull() {
            addCriterion("groupsId is not null");
            return (Criteria) this;
        }

        public Criteria andGroupsidEqualTo(String value) {
            addCriterion("groupsId =", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidNotEqualTo(String value) {
            addCriterion("groupsId <>", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidGreaterThan(String value) {
            addCriterion("groupsId >", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidGreaterThanOrEqualTo(String value) {
            addCriterion("groupsId >=", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidLessThan(String value) {
            addCriterion("groupsId <", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidLessThanOrEqualTo(String value) {
            addCriterion("groupsId <=", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidLike(String value) {
            addCriterion("groupsId like", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidNotLike(String value) {
            addCriterion("groupsId not like", value, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidIn(List<String> values) {
            addCriterion("groupsId in", values, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidNotIn(List<String> values) {
            addCriterion("groupsId not in", values, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidBetween(String value1, String value2) {
            addCriterion("groupsId between", value1, value2, "groupsid");
            return (Criteria) this;
        }

        public Criteria andGroupsidNotBetween(String value1, String value2) {
            addCriterion("groupsId not between", value1, value2, "groupsid");
            return (Criteria) this;
        }

        public Criteria andWaccountidIsNull() {
            addCriterion("waccountId is null");
            return (Criteria) this;
        }

        public Criteria andWaccountidIsNotNull() {
            addCriterion("waccountId is not null");
            return (Criteria) this;
        }

        public Criteria andWaccountidEqualTo(String value) {
            addCriterion("waccountId =", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidNotEqualTo(String value) {
            addCriterion("waccountId <>", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidGreaterThan(String value) {
            addCriterion("waccountId >", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidGreaterThanOrEqualTo(String value) {
            addCriterion("waccountId >=", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidLessThan(String value) {
            addCriterion("waccountId <", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidLessThanOrEqualTo(String value) {
            addCriterion("waccountId <=", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidLike(String value) {
            addCriterion("waccountId like", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidNotLike(String value) {
            addCriterion("waccountId not like", value, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidIn(List<String> values) {
            addCriterion("waccountId in", values, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidNotIn(List<String> values) {
            addCriterion("waccountId not in", values, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidBetween(String value1, String value2) {
            addCriterion("waccountId between", value1, value2, "waccountid");
            return (Criteria) this;
        }

        public Criteria andWaccountidNotBetween(String value1, String value2) {
            addCriterion("waccountId not between", value1, value2, "waccountid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
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