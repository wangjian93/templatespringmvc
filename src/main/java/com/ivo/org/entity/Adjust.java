package com.ivo.org.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/12/19
 */
@Entity
@Table(name = "HR_O_DepartmentAdjust")
@Where(clause = "validFlag = true")
public class Adjust extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "source")
    private String source;

    @Column(name = "target")
    private String target;

    /**
     * 变动类型：1.加签  2.跳签
     */
    @Column(name = "type")
    private int type;

    @Column(name = "adjustDept")
    private String adjustDept;

    @Column(name = "adjustParent")
    private String adjustParent;

    @Column(name = "virtualDept")
    private String virtualDept;

    @OneToMany(mappedBy = "adjust", cascade = CascadeType.ALL)
    private List<AdjustAction> actions;

    @Column(name = "status")
    private int status;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdjustDept() {
        return adjustDept;
    }

    public void setAdjustDept(String adjustDept) {
        this.adjustDept = adjustDept;
    }

    public String getVirtualDept() {
        return virtualDept;
    }

    public void setVirtualDept(String virtualDept) {
        this.virtualDept = virtualDept;
    }

    public List<AdjustAction> getActions() {
        return actions;
    }

    public void setActions(List<AdjustAction> actions) {
        this.actions = actions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAdjustParent() {
        return adjustParent;
    }

    public void setAdjustParent(String adjustParent) {
        this.adjustParent = adjustParent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
