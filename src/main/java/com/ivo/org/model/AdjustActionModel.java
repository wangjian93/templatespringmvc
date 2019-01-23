package com.ivo.org.model;

import com.ivo.org.entity.Adjust;
import com.ivo.org.entity.AdjustAction;

/**
 * @author wangjian
 * @date 2018/12/19
 */
public class AdjustActionModel {

    private long id;

    private int type;

    private String typeName;

    private String adjustDept;

    private String adjustDeptName;

    private String adjustParent;

    private String adjustParentName;

    private String virtualDept;

    private String virtualDeptName;

    /**
     * 描述
     */
    private String description;

    public AdjustActionModel(AdjustAction action) {
        this(action.getId(), action.getType(), action.getAdjustDept(),
                action.getAdjustParent(), action.getVirtualDept());
    }

    public AdjustActionModel(long id, int type, String adjustDept, String adjustParent,
                       String virtualDept) {
        this.id = id;
        this.type = type;
        this.adjustDept = adjustDept;
        this.adjustParent = adjustParent;
        this.virtualDept = virtualDept;

        if(type == 1) {
            this.typeName = "创建部门";
        } else if(type == 2) {
            this.typeName = "变更上级";
        }

    }

    public void setNames(String adjustDeptName, String adjustParentName, String virtualDeptName) {
        this.adjustDeptName = adjustDeptName;
        this.adjustParentName = adjustParentName;
        this.virtualDeptName = virtualDeptName;
    }

    /**
     * 生成描述
     */
    public void generateDescription() {
        StringBuffer str = new StringBuffer();

        if(type == 1) {
            str.append("添加虚拟部门" + "["+virtualDept+"]" + virtualDeptName);
        } else if(type == 2) {
            str.append("变更部门" +"["+adjustDept +"]" + adjustDeptName);
            str.append("的上级部门为" + "["+adjustParent+"]" + adjustParentName);
        }
        this.description = str.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAdjustDept() {
        return adjustDept;
    }

    public void setAdjustDept(String adjustDept) {
        this.adjustDept = adjustDept;
    }

    public String getAdjustDeptName() {
        return adjustDeptName;
    }

    public void setAdjustDeptName(String adjustDeptName) {
        this.adjustDeptName = adjustDeptName;
    }

    public String getAdjustParent() {
        return adjustParent;
    }

    public void setAdjustParent(String adjustParent) {
        this.adjustParent = adjustParent;
    }

    public String getAdjustParentName() {
        return adjustParentName;
    }

    public void setAdjustParentName(String adjustParentName) {
        this.adjustParentName = adjustParentName;
    }

    public String getVirtualDept() {
        return virtualDept;
    }

    public void setVirtualDept(String virtualDept) {
        this.virtualDept = virtualDept;
    }

    public String getVirtualDeptName() {
        return virtualDeptName;
    }

    public void setVirtualDeptName(String virtualDeptName) {
        this.virtualDeptName = virtualDeptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
