package com.ivo.org.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类元模型
 * @author wangjian
 * @date 2018/10/22
 */
@MappedSuperclass
public class ModelAtom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标识逻辑删除
     */
    @Column(name = "validFlag")
    private boolean validFlag;

    public ModelAtom() {
        this.validFlag = true;
    }

    public boolean isValidFlag() {
        return validFlag;
    }

    public void setValidFlag(boolean validFlag) {
        this.validFlag = validFlag;
    }
}
