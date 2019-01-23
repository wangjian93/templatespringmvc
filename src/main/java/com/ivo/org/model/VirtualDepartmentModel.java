package com.ivo.org.model;

/**
 * @author wangjian
 * @date 2018/12/19
 */
public class VirtualDepartmentModel {

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


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
}
