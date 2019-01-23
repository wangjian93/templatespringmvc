package com.ivo.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author wangjian
 * @date 2018/12/19
 */
@Entity
@Table(name = "HR_O_DepartmentAdjustAction")
@Where(clause = "validFlag = true")
public class AdjustAction extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * 操作类型：1.加虚拟部门   2.变更上级
     */
    @Column(name = "type")
    private int type;

    @Column(name = "adjustDept")
    private String adjustDept;

    @Column(name = "adjustParent")
    private String adjustParent;

    @Column(name = "virtualDept")
    private String virtualDept;

    @ManyToOne
    @JoinColumn(name="adjust_fk")
    @JsonBackReference
    private Adjust adjust;

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

    public String getAdjustParent() {
        return adjustParent;
    }

    public void setAdjustParent(String adjustParent) {
        this.adjustParent = adjustParent;
    }

    public String getVirtualDept() {
        return virtualDept;
    }

    public void setVirtualDept(String virtualDept) {
        this.virtualDept = virtualDept;
    }

    public Adjust getAdjust() {
        return adjust;
    }

    public void setAdjust(Adjust adjust) {
        this.adjust = adjust;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
