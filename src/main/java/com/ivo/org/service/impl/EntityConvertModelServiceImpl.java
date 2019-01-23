package com.ivo.org.service.impl;

import com.ivo.org.entity.Adjust;
import com.ivo.org.entity.AdjustAction;
import com.ivo.org.model.AdjustActionModel;
import com.ivo.org.model.AdjustModel;
import com.ivo.org.model.SignedDepartmentTree;
import com.ivo.org.model.SignedDepartment;
import com.ivo.org.service.AdjustService;
import com.ivo.org.service.EntityConvertModelService;
import com.ivo.org.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangjian
 * @date 2018/12/21
 */
@Service
public class EntityConvertModelServiceImpl implements EntityConvertModelService {

    @Autowired
    private AdjustService adjustService;

    public AdjustModel convertAdjust(Adjust adjust) {
        return convertAdjust(adjust, null);
    }

    public AdjustModel convertAdjust(Adjust adjust, Map<String, SignedDepartment> map) {
        if(map == null) {
            map = adjustService.getSignedDeptMap();
        }

        AdjustModel adjustModel = new AdjustModel(adjust);

        String sourceName = "";
        String targetName = "";
        String adjustDeptName = "";
        String adjustParentName = "";
        String virtualDeptName = "";

        SignedDepartment source = map.get(adjust.getSource());
        if(source != null) {
            sourceName = source.getDeptName();
        }
        SignedDepartment target = map.get(adjust.getTarget());
        if(target != null) {
            targetName = target.getDeptName();
        }
        SignedDepartment adjustDept = map.get(adjust.getAdjustDept());
        if(adjustDept != null) {
            adjustDeptName = adjustDept.getDeptName();
        }
        SignedDepartment adjustParent  = map.get(adjust.getAdjustParent());
        if(adjustParent != null) {
            adjustParentName = adjustParent.getDeptName();
        }
        SignedDepartment virtualDept  = map.get(adjust.getVirtualDept());
        if(virtualDept != null) {
            virtualDeptName = virtualDept.getDeptName();
        }

        adjustModel.setNames(sourceName, targetName, adjustDeptName, adjustParentName, virtualDeptName);

        adjustModel.setActions(convertAdjustActionModel(adjust.getActions(), map));

        adjustModel.generateDescription();

        return adjustModel;
    }

    public List<AdjustModel> convertAdjust(List<Adjust> list) {
        Map<String, SignedDepartment> map = adjustService.getSignedDeptMap();

        List<AdjustModel> adjustModelList = new ArrayList<AdjustModel>();

        for(Adjust adjust : list) {
            AdjustModel adjustModel = convertAdjust(adjust, map);
            adjustModelList.add(adjustModel);
        }

        return adjustModelList;
    }



    public AdjustActionModel convertAdjustActionModel(AdjustAction adjustAction, Map<String, SignedDepartment> map) {
        if(map == null) {
            map = adjustService.getSignedDeptMap();
        }

        AdjustActionModel actionModel = new AdjustActionModel(adjustAction);

        String adjustDeptName = "";
        String adjustParentName = "";
        String virtualDeptName = "";


        SignedDepartment adjustDept = map.get(adjustAction.getAdjustDept());
        if(adjustDept != null) {
            adjustDeptName = adjustDept.getDeptName();
        }
        SignedDepartment adjustParent  = map.get(adjustAction.getAdjustParent());
        if(adjustParent != null) {
            adjustParentName = adjustParent.getDeptName();
        }
        SignedDepartment virtualDept  = map.get(adjustAction.getVirtualDept());
        if(virtualDept != null) {
            virtualDeptName = virtualDept.getDeptName();
        }

        actionModel.setNames(adjustDeptName, adjustParentName, virtualDeptName);

        actionModel.generateDescription();

        return actionModel;
    }

    public List<AdjustActionModel> convertAdjustActionModel(List<AdjustAction> list, Map<String, SignedDepartment> map) {
        if(map == null) {
            map = adjustService.getSignedDeptMap();
        }

        List<AdjustActionModel> actionModelList = new ArrayList<AdjustActionModel>();

        for(AdjustAction action : list) {
            AdjustActionModel adjustModel  = convertAdjustActionModel(action, map);
            actionModelList.add(adjustModel);
        }

        return actionModelList;
    }

    public List<SignedDepartmentTree> convertSignedDeptTree(List<SignedDepartment> list) {
        List<SignedDepartmentTree> treeList = new ArrayList<SignedDepartmentTree>();
        for(SignedDepartment signedDept : list) {
            SignedDepartmentTree departmentTree = new SignedDepartmentTree(signedDept);
            treeList.add(departmentTree);


            boolean hasParent = false;
            boolean hasSiblings = false;
            boolean hasChildren = false;
            if(signedDept.getParentId()==null || signedDept.getParentId().equals("")) {
                hasParent = true;
            }
            List<SignedDepartment> children = adjustService.findSignedDeptChildren(signedDept.getDeptId());
            List<SignedDepartment> siblings  = adjustService.findSignedDeptSiblings(signedDept.getDeptId());
            if(siblings != null && siblings.size()>0) {
                hasSiblings = true;
            }
            if(children != null && children.size()>0) {
                hasChildren = true;
            }

            String relationship = "";
            if(hasParent) {
                relationship += "1";
            } else {
                relationship += "0";
            }
            if(hasSiblings) {
                relationship += "1";
            } else {
                relationship += "0";
            }
            if(hasChildren) {
                relationship += "1";
            } else {
                relationship += "0";
            }
            departmentTree.setRelationship(relationship);
        }
        List<SignedDepartmentTree> tree = TreeUtils.formatTree(treeList, false);
        return treeList;
    }
}
