package com.ivo.org.model;

import com.ivo.org.util.BaseTreeGrid;

/**
 * @author wangjian
 * @date 2018/10/29
 */
public class SignedDepartmentTree extends BaseTreeGrid {

    private int level;

    private String deptName;

    private String deptNameS;

    private String deptHeader;

    private int deptType;

    /**
     * class样式
     */
    private String className;

    private String relationship = "111";

    private boolean adjustAble = true;

    /**
     * 标识是否发布状态
     */
    private int status = 1;

    public SignedDepartmentTree(SignedDepartment signedDept) {
        String parentId = signedDept.getParentId();
        String id = signedDept.getDeptId();
        if(id.equals(parentId) || ("").equals(parentId)) {
            parentId = null;
        }
        this.id = id;
        this.parentId = parentId;
        this.level = signedDept.getDeptLevel();
        this.deptName = signedDept.getDeptName();
        this.deptNameS = signedDept.getDeptNameS();
        this.deptHeader = signedDept.getDeptHeader();
        this.deptType = signedDept.getType();

         switch (level) {
             case 1 : className = "goldenrod"; break;
             case 2 : className = "lemonchiffon"; break;
             case 3 : className = "rd-dept"; break;
             case 4 : className = "pipeline1"; break;
             case 5 : className = "middle-level"; break;
             case 6 : className = "product-dept"; break;
             case 7 : className = "frontend1"; break;
             case 8 : className = "lemonchiffon"; break;
             default : break;
         }
    }

    public int getLevel() {
        return level;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptNameS() {
        return deptNameS;
    }

    public String getDeptHeader() {
        return deptHeader;
    }

    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptNameS(String deptNameS) {
        this.deptNameS = deptNameS;
    }

    public void setDeptHeader(String deptHeader) {
        this.deptHeader = deptHeader;
    }

    public boolean isAdjustAble() {
        return adjustAble;
    }

    public void setAdjustAble(boolean adjustAble) {
        this.adjustAble = adjustAble;
    }
}
