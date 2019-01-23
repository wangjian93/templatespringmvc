package com.ivo.org.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangjian
 * @date 2018/12/19
 */
@Entity
@Table(name = "HR_O_DepartmentVirtual")
@Where(clause = "validFlag = true")
public class VirtualDepartment extends Model {

    /**
     * 部门编号
     */
    @Id
    @Column(name = "deptId")
    private String deptId;

    /**
     * 上级部门编号
     */
    @Column(name = "parentId")
    private String parentId;

    /**
     * 部门名称
     */
    @Column(name = "deptName")
    private String deptName;

    /**
     * 部门名称-英
     */
    @Column(name = "deptNameEN")
    private String deptNameEN;

    /**
     * 部门简称
     */
    @Column(name = "deptNameS")
    private String deptNameS;

    /**
     * 部门级别
     */
    @Column(name = "deptLevel")
    private int deptLevel;

    /**
     * 部门主管
     */
    @Column(name = "deptHeader")
    private String deptHeader;

    /**
     * 部门路径
     */
    @Column(name = "deptPath")
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
