package com.ivo.org.model;

import com.ivo.org.entity.Adjust;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/12/19
 */
public class AdjustModel {

    private long id;

    private String source;

    private String sourceName;

    private String target;

    private String targetName;

    private int type;

    private String typeName;

    private String adjustDept;

    private String adjustDeptName;

    private String adjustParent;

    private String adjustParentName;

    private String virtualDept;

    private String virtualDeptName;

    private List<AdjustActionModel> actions;

    private int status;

    private String statusName;

    /**
     * 描述
     */
    private String description;

    public AdjustModel(Adjust adjust) {
        this(adjust.getId(), adjust.getSource(), adjust.getTarget(), adjust.getType(), adjust.getAdjustDept(),
                adjust.getAdjustParent(), adjust.getVirtualDept(), adjust.getStatus());
    }

    public AdjustModel(long id, String source, String target, int type, String adjustDept, String adjustParent,
                       String virtualDept, int status) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.type = type;
        this.adjustDept = adjustDept;
        this.adjustParent = adjustParent;
        this.virtualDept = virtualDept;
        this.status = status;

        if(type == 1) {
            this.typeName = "加签";
        } else if(type == 2) {
            this.typeName = "部门上级";
        }

        if(status == 0) {
            this.statusName = "未发布";
        } else if(status == 1) {
            this.statusName = "已发布";
        }
    }

    public void setNames(String sourceName, String targetName, String adjustDeptName,
                         String adjustParentName, String virtualDeptName) {
        this.sourceName = sourceName;
        this.targetName = targetName;
        this.adjustDeptName = adjustDeptName;
        this.adjustParentName = adjustParentName;
        this.virtualDeptName = virtualDeptName;
    }


    /**
     * 生成描述
     */
    public void generateDescription() {
        StringBuffer str = new StringBuffer();
        str.append("["+source+"]" + sourceName + "签核中，");
        if(!source.equals(target)) {
            str.append("签核至" + "["+target+"]" + targetName);
        }

        if(type == 1) {
            str.append("加签部门" + "["+virtualDept+"]" + virtualDeptName);
        } else if(type == 2) {
            str.append("变更上级部门为" + "["+adjustParent+"]" + adjustParentName);
        }
        this.description = str.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
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

    public List<AdjustActionModel> getActions() {
        return actions;
    }

    public void setActions(List<AdjustActionModel> actions) {
        this.actions = actions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
