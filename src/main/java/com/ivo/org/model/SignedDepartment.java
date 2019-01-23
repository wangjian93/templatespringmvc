package com.ivo.org.model;

/**
 * @author wangjian
 * @date 2018/12/19
 */
public class SignedDepartment {

    /**
     * 部门编号
     */
    private String deptId;

    /**
     * 上级部门编号
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门名称-英
     */
    private String deptNameEN;

    /**
     * 部门简称
     */
    private String deptNameS;

    /**
     * 部门级别
     */
    private int deptLevel;

    /**
     * 部门主管
     */
    private String deptHeader;

    /**
     * 部门路径
     */
    private String deptPath;

    /**
     * 类型标识：1.实体  2.虚拟  3.虚拟-未生效
     */
    private int type;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNameEN() {
        return deptNameEN;
    }

    public void setDeptNameEN(String deptNameEN) {
        this.deptNameEN = deptNameEN;
    }

    public String getDeptNameS() {
        return deptNameS;
    }

    public void setDeptNameS(String deptNameS) {
        this.deptNameS = deptNameS;
    }

    public int getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(int deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptHeader() {
        return deptHeader;
    }

    public void setDeptHeader(String deptHeader) {
        this.deptHeader = deptHeader;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
