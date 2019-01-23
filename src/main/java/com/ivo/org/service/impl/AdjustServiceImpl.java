package com.ivo.org.service.impl;

import com.ivo.dao.hibernate.HibernateCaller;
import com.ivo.org.entity.*;
import com.ivo.org.model.SignedDepartment;
import com.ivo.org.service.AdjustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author wangjian
 * @date 2018/12/20
 */
@Service
public class AdjustServiceImpl implements AdjustService {

    @Autowired
    private HibernateCaller hibernateCaller;

    public List<Department> listAllDept() {

        List<Department> list = hibernateCaller.find("from Department d where d.validFlag=true");
        return list;
    }

    public List<Department> listDept() {
        List<Department> list = hibernateCaller.find("from Department d where d.validFlag=true and d.type=1");
        return list;
    }

    public Department getDept(String deptId) {
        Department department = hibernateCaller.getObject(Department.class, deptId);
        return department;
    }

    public List<VirtualDepartment> listVirtualDept() {
        List<VirtualDepartment> list = hibernateCaller.loadAll(VirtualDepartment.class);
        return list;
    }

    public VirtualDepartment getVirtualDept(String deptId) {
        VirtualDepartment virtualDepartment = hibernateCaller.getObject(VirtualDepartment.class, deptId);
        return virtualDepartment;
    }



    public List<SignedDepartment> findSignedDeptAllParent(String deptId) {
        Map<String, SignedDepartment> map = convertSignedDeptMap();

        List<SignedDepartment> list = new ArrayList<SignedDepartment>();
        SignedDepartment signedDept = map.get(deptId);
        list.add(signedDept);

        String parentid = signedDept.getParentId();
        while (parentid != null && !("").equals(parentid)) {
            SignedDepartment parent = map.get(parentid);
            list.add(parent);
            parentid = parent.getParentId();
        }
        return list;
    }

    public List<SignedDepartment>  findSignedDeptChildren(String deptId) {
        List<SignedDepartment> list = new ArrayList<SignedDepartment>();

        List<SignedDepartment> signedDepartments = getSignedDept();
        for(SignedDepartment signedDept : signedDepartments) {
            if((deptId).equals(signedDept.getParentId())) {
                list.add(signedDept);
            }
        }
        return list;
    }

    public List<SignedDepartment>  findSignedDeptSiblings(String deptId) {
        List<SignedDepartment> list = new ArrayList<SignedDepartment>();
        SignedDepartment s = getSignedDept(deptId);

        List<SignedDepartment> signedDepartments = getSignedDept();
        String parentId= s.getParentId();
        if(parentId == null) {
            return list;
        }
        for(SignedDepartment signedDept : signedDepartments) {
            if(s.getDeptId().equals(signedDept.getDeptId())){
                continue;
            }
            if(parentId.equals(signedDept.getParentId())) {
                list.add(signedDept);
            }
        }
        return list;
    }


    public List<SignedDepartment> getSignedDept() {
        List<Adjust> adjusts = listAdjust();
        return getSignedDept(adjusts);
    }

    public List<SignedDepartment> getSignedDept(List<Adjust> adjusts) {
        List<Department> departments = listDept();

        List<SignedDepartment> signedDepartments = new ArrayList<SignedDepartment>();

        for(Department department : departments) {
            SignedDepartment signedDept = convertDeptToSigned(department);
            signedDepartments.add(signedDept);
        }

        for(Adjust adjust : adjusts) {
            List<AdjustAction> actions = adjust.getActions();
            for(AdjustAction action : actions) {

                int type = action.getType();
                // 添加虚拟部门
                if(type == 1) {
                    VirtualDepartment virtualDept = getVirtualDept(action.getVirtualDept());
                    SignedDepartment signedDept = convertVirtualDeptToSigned(virtualDept);
                    signedDepartments.add(signedDept);
                }
            }

            Map<String, SignedDepartment> map = convertSignedDeptMap(signedDepartments);

            for(AdjustAction action : actions) {
                int type = action.getType();
                // 修改了部门上级
                if (type == 2) {
                    SignedDepartment signedDept = map.get(action.getAdjustDept());
                    signedDept.setParentId(action.getAdjustParent());
                }
            }

        }

        return signedDepartments;
    }

    public SignedDepartment getSignedDept(String deptId) {
        Map<String, SignedDepartment> map = convertSignedDeptMap();
        return map.get(deptId);
    }

    public List<Adjust> listAdjust() {
        List<Adjust> list = hibernateCaller.loadAll(Adjust.class);
        return list;
    }

    public Adjust getAdjust(long id) {
        Adjust adjust = hibernateCaller.getObject(Adjust.class, id);
        return adjust;
    }

    public Map<String, SignedDepartment> convertSignedDeptMap() {
        List<SignedDepartment> list = getSignedDept();
        return convertSignedDeptMap(list);
    }

    public Map<String, SignedDepartment> convertSignedDeptMap(List<SignedDepartment> list) {
        Map<String, SignedDepartment> map = new HashMap<String, SignedDepartment>(list.size());
        for(SignedDepartment dept : list) {
            map.put(dept.getDeptId(), dept);
        }
        return map;
    }

    public SignedDepartment convertDeptToSigned(Department dept) {
        SignedDepartment signedDepartment = new SignedDepartment();
        signedDepartment.setDeptId(dept.getDeptId());
        signedDepartment.setParentId(dept.getParentId());
        signedDepartment.setDeptName(dept.getDeptName());
        signedDepartment.setDeptNameEN(dept.getDeptNameEN());
        signedDepartment.setDeptNameS(dept.getDeptNameS());
        signedDepartment.setDeptLevel(dept.getDeptLevel());
        signedDepartment.setDeptHeader(dept.getDeptHeader());
        signedDepartment.setDeptPath(dept.getDeptPath());
        signedDepartment.setType(1);
        return signedDepartment;
    }

    public SignedDepartment convertVirtualDeptToSigned(VirtualDepartment dept) {
        SignedDepartment signedDepartment = new SignedDepartment();
        signedDepartment.setDeptId(dept.getDeptId());
        signedDepartment.setParentId(dept.getParentId());
        signedDepartment.setDeptName(dept.getDeptName());
        signedDepartment.setDeptNameEN(dept.getDeptNameEN());
        signedDepartment.setDeptNameS(dept.getDeptNameS());
        signedDepartment.setDeptLevel(dept.getDeptLevel());
        signedDepartment.setDeptHeader(dept.getDeptHeader());
        signedDepartment.setDeptPath(dept.getDeptPath());
        signedDepartment.setType(2);
        return signedDepartment;
    }

    public void adjust(String source, String target, int type, String adjustDept, String adjustParent
            , String deptName, String deptNameEN, String deptNameS, int deptLevel, String deptHeader) {

        Adjust adjust = new Adjust();
        adjust.setSource(source);
        adjust.setTarget(target);
        adjust.setType(type);

        if(type == 1) {
            SignedDepartment signedDept = getSignedDept(target);
            String parentId = signedDept.getParentId();

            VirtualDepartment virtualDept = createVirtualDepartment(parentId, deptName, deptNameEN, deptNameS, deptLevel, deptHeader);
            adjust.setVirtualDept(virtualDept.getDeptId());

            List<AdjustAction> actions = add(adjust);
            adjust.setActions(actions);
        }
        else if(type == 2) {
            List<AdjustAction> actions = jump(adjust);
            adjust.setActions(actions);
        }
        hibernateCaller.saveObject(adjust);
    }

    public List<AdjustAction> add(Adjust adjust) {

        List<AdjustAction> actions = new ArrayList<AdjustAction>();

        // 创建虚拟的操作
        AdjustAction action = new AdjustAction();
        action.setAdjust(adjust);
        action.setType(1);
        action.setVirtualDept(adjust.getVirtualDept());


        actions.add(action);

        // 涉及source至target路径上部门的变更操作
        SignedDepartment source = getSignedDept(adjust.getSource());
        if(adjust.getSource().equals(adjust.getTarget())) {
            AdjustAction action1 = new AdjustAction();
            action1.setAdjust(adjust);
            action1.setType(2);
            action1.setAdjustDept(source.getDeptId());
            action1.setAdjustParent(adjust.getVirtualDept());
            actions.add(action1);
        } else {

            // 从source至target的上级部门
            List<SignedDepartment> parents = getParent(adjust.getSource(), adjust.getTarget());

            // 加虚拟部门
            Map<String, String> map = new HashMap<String, String>();
            for(SignedDepartment signedDept : parents) {
                VirtualDepartment virtualDepartment = createVirtualDepartment(signedDept);
                AdjustAction action2 = new AdjustAction();
                action2.setAdjust(adjust);
                action2.setType(1);
                action2.setVirtualDept(virtualDepartment.getDeptId());
                actions.add(action2);

                map.put(signedDept.getDeptId(), virtualDepartment.getDeptId());
            }

            // 修改上级
            for(SignedDepartment signedDept : parents) {
                String deptId = signedDept.getDeptId();
                AdjustAction action3 = new AdjustAction();
                action3.setAdjust(adjust);
                action3.setType(2);
                if(deptId.equals(adjust.getTarget())) {
                    action3.setAdjustDept(map.get(deptId));
                    action3.setAdjustParent(adjust.getVirtualDept());
                } else {
                    action3.setAdjustDept(map.get(deptId));
                    action3.setAdjustParent(map.get(signedDept.getParentId()));
                }
                actions.add(action3);
            }

            AdjustAction action4 = new AdjustAction();
            action4.setAdjust(adjust);
            action4.setType(2);
            action4.setAdjustDept(source.getDeptId());
            action4.setAdjustParent(map.get(source.getParentId()));
            actions.add(action4);
        }
        return actions;
    }

    public List<AdjustAction> jump(Adjust adjust) {
        // todo..
        return null;
    }

    public VirtualDepartment createVirtualDepartment(String parentId, String deptName, String deptNameEN, String deptNameS, int deptLevel, String deptHeader) {
        VirtualDepartment virtualDept = new VirtualDepartment();
        virtualDept.setParentId(parentId);
        virtualDept.setDeptName(deptName);
        virtualDept.setDeptNameEN(deptNameEN);
        virtualDept.setDeptNameS(deptNameS);
        virtualDept.setDeptLevel(deptLevel);
        virtualDept.setDeptHeader(deptHeader);
        virtualDept.setDeptId(generateNumber());
        virtualDept.setDeptPath(generatePath());

        hibernateCaller.saveObject(virtualDept);
        return virtualDept;
    }

    public VirtualDepartment createVirtualDepartment(SignedDepartment signedDept) {
        VirtualDepartment virtualDept = new VirtualDepartment();
        virtualDept.setParentId(signedDept.getParentId());
        virtualDept.setDeptName(signedDept.getDeptName());
        virtualDept.setDeptNameEN(signedDept.getDeptNameEN());
        virtualDept.setDeptNameS(signedDept.getDeptNameS());
        virtualDept.setDeptLevel(signedDept.getDeptLevel());
        virtualDept.setDeptHeader(signedDept.getDeptHeader());
        virtualDept.setDeptId(generateNumber());
        virtualDept.setDeptPath(generatePath());

        hibernateCaller.saveObject(virtualDept);
        return virtualDept;
    }

    public List<SignedDepartment> getParent(String from, String to) {
        Map<String, SignedDepartment> map = convertSignedDeptMap();
        SignedDepartment f = map.get(from);
        List<SignedDepartment> parents = new ArrayList<SignedDepartment>();
        if(from.equals(to)) {
            parents.add(f);
            return parents;
        }

        String parentId = f.getParentId();
        while(true) {
            SignedDepartment s = map.get(parentId);
            if(s == null) {
                break;
            }
            parents.add(s);
            if(to.equals(s.getDeptId())) {
                break;
            }
            parentId = s.getParentId();
        }
        return parents;
    }

    public String generateNumber() {
        String departmentNumber = "200";
        Random random = new Random();
        for(int i=0; i<5; i++) {
            departmentNumber += random.nextInt(10);
        }
        return departmentNumber;
    }

    public String generatePath() {
        // todo..
        return null;
    }

    public Map<String, SignedDepartment> getSignedDeptMap() {
        return convertSignedDeptMap();
    }


    public void realseAdjust(long[] ids) {
        for(long id : ids) {
            Adjust adjust = getAdjust(id);
            adjust.setStatus(1);
            adjust.setUpdateDate(new Date());
            adjust.setUpdater("sys");
            hibernateCaller.saveOrUpdateObject(adjust);
        }

        realseProcess();
    }

    public void realseProcess() {
        List<Adjust> adjusts = hibernateCaller.getSession().createQuery("from Adjust a where a.validFlag=true and a.status=1").list();

        List<SignedDepartment> signedDepartments = getSignedDept(adjusts);

        List<Department> departments = listAllDept();

        List<String> deptIds = new ArrayList<String>();

        for(SignedDepartment signedDepartment : signedDepartments) {

            int type = signedDepartment.getType();
            if(type == 1) {
                Department dept = getDept(signedDepartment.getDeptId());
                dept.setParentSignedId(signedDepartment.getParentId());
                hibernateCaller.saveOrUpdateObject(dept);

            } else {

                Department dept = null;
                dept = getDept(signedDepartment.getDeptId());
                if(dept == null) {
                    dept = new Department();
                }
                dept.setType(2);
                dept.setValidFlag(true);
                dept.setDeptId(signedDepartment.getDeptId());
                dept.setParentSignedId(signedDepartment.getParentId());
                dept.setDeptHeader(signedDepartment.getDeptHeader());
                dept.setDeptName(signedDepartment.getDeptName());
                dept.setDeptNameEN(signedDepartment.getDeptNameEN());
                dept.setDeptNameS(signedDepartment.getDeptNameS());
                dept.setDeptLevel(signedDepartment.getDeptLevel());
                hibernateCaller.saveOrUpdateObject(dept);
            }

            deptIds.add(signedDepartment.getDeptId());
        }

        for(Department department : departments) {
            if (!deptIds.contains(department.getDeptId())) {
                department.setValidFlag(false);
                hibernateCaller.saveOrUpdateObject(department);
            }
        }
    }


    public void removeAdjust(long[] ids) {
        for(long id : ids) {
            Adjust adjust =  hibernateCaller.getSession().get(Adjust.class, id);

            adjust.setValidFlag(false);
            adjust.setUpdateDate(new Date());
            adjust.setUpdater("sys");
            hibernateCaller.saveOrUpdateObject(adjust);
        }
        realseProcess();
    }
}
