package com.ivo.org.service;

import com.ivo.org.entity.Adjust;
import com.ivo.org.entity.Department;
import com.ivo.org.entity.VirtualDepartment;
import com.ivo.org.model.SignedDepartment;

import java.util.List;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/12/20
 */
public interface AdjustService {

    List<Department> listDept();

    Department getDept(String deptId);

    List<VirtualDepartment> listVirtualDept();

    VirtualDepartment getVirtualDept(String deptId);

    List<SignedDepartment> getSignedDept();

    SignedDepartment getSignedDept(String deptId);

    List<Adjust> listAdjust();

    void adjust(String source, String target, int type, String adjustDept, String adjustParent
            , String deptName, String deptNameEN, String deptNameS, int deptLevel, String deptHeader);


    VirtualDepartment createVirtualDepartment(String parentI, String deptName, String deptNameEN, String deptNameS,
                                              int deptLevel, String deptHeader);

    Map<String, SignedDepartment> getSignedDeptMap();


    List<SignedDepartment> findSignedDeptSiblings(String deptId);
    List<SignedDepartment> findSignedDeptAllParent(String deptId);
    List<SignedDepartment> findSignedDeptChildren(String deptId);

    void realseAdjust(long[] ids);
    void removeAdjust(long[] ids);
}
