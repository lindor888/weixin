package com.ctvit.bean;

public class Groups {
    private String groupsId;

    private String waccountId;

    public String name;

    private Integer count;

    public String getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(String groupsId) {
        this.groupsId = groupsId == null ? null : groupsId.trim();
    }

    public String getWaccountId() {
        return waccountId;
    }

    public void setWaccountId(String waccountId) {
        this.waccountId = waccountId == null ? null : waccountId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}