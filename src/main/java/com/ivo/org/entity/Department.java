package com.ivo.org.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体部门
 * @author wangjian
 * @date 2018/12/19
 */
@Entity
@Table(name = "HR_O_Department")
@Where(clause = "validFlag = true")
public class Department {

    /**
     * 部门编号
     */
    @Id
    @Column(name = "department_id")
    private String deptId;

    /**
     * 上级部门编号
     */
    @Column(name = "parentSigned_FK")
    private String parentSignedId;

    /**
     * 上级部门编号
     */
    @Column(name = "parent_FK")
    private String parentId;

    /**
     * 部门名称
     */
    @Column(name = "deptName")
    private String deptName;

    /**
     * 部门名称-英
     */
    @Column(name = "deptName_EN")
    private String deptNameEN;

    /**
     * 部门简称
     */
    @Column(name = "deptName_S")
    private String deptNameS;

    /**
     * 部门级别
     */
    private int deptLevel;

    /**
     * 部门主管
     */
    @Column(name = "deptHead_fk")
    private String deptHeader;

    /**
     * 部门路径
     */
    @Column(name = "deptPathSigned")
    private String deptPath;

    /**
     * 类型
     */
    @Column(name = "dept_type")
    private int type;

    @Column(name = "validFlag")
    private boolean validFlag = true;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isValidFlag() {
        return validFlag;
    }

    public void setValidFlag(boolean validFlag) {
        this.validFlag = validFlag;
    }

    public String getParentSignedId() {
        return parentSignedId;
    }

    public void setParentSignedId(String parentSignedId) {
        this.parentSignedId = parentSignedId;
    }
}
