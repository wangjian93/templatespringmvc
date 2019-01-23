package com.ivo.org.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 实体类模型
 * @author wangjian
 * @date 2018/10/22
 */
@MappedSuperclass
public class Model extends ModelAtom {

    /**
     * 创建者
     */
    @Column(name = "creator")
    private String creator = "sys";

    /**
     * 创建时间
     */
    @Column(name = "createDate")
    private Date createDate = new Date();

    /**
     * 更新者
     */
    @Column(name = "updater")
    private String updater = "sys";

    /**
     * 更新时间
     */
    @Column(name = "updateDate")
    private Date updateDate = new Date();

    public Model() {
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
