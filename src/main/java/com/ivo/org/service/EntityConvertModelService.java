package com.ivo.org.service;

import com.ivo.org.entity.Adjust;
import com.ivo.org.model.AdjustModel;
import com.ivo.org.model.SignedDepartment;
import com.ivo.org.model.SignedDepartmentTree;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/12/21
 */
public interface EntityConvertModelService {

    AdjustModel convertAdjust(Adjust adjust);

    List<AdjustModel> convertAdjust(List<Adjust> list);

    List<SignedDepartmentTree> convertSignedDeptTree(List<SignedDepartment> list);

}
