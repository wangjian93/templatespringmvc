package com.ivo.org.controller;

import com.ivo.org.entity.Adjust;
import com.ivo.org.entity.Department;
import com.ivo.org.entity.VirtualDepartment;
import com.ivo.org.model.SignedDepartment;
import com.ivo.org.model.SignedDepartmentTree;
import com.ivo.org.service.AdjustService;
import com.ivo.org.service.EntityConvertModelService;
import com.ivo.org.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/12/20
 */
@Controller
public class AdjustController {

    @Autowired
    private AdjustService adjustService;

    @Autowired
    private EntityConvertModelService convertModelService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/")
    public String adjustView() {
        return "adjust";
    }

    @RequestMapping("/listDept")
    @ResponseBody
    public JsonResult listDept() {
        List<Department> list = adjustService.listDept();

        return JsonResult.ok().put("data", list);
    }

    @RequestMapping("/listSignedDept")
    @ResponseBody
    public JsonResult listSignedDept() {
        List<SignedDepartment> list = adjustService.getSignedDept();
        return JsonResult.ok().put("data", list);
    }

    @RequestMapping("/listSignedDeptTree")
    @ResponseBody
    public SignedDepartmentTree getSignedDeptTree() {
        List<SignedDepartment> list = adjustService.getSignedDept();
        List<SignedDepartmentTree> treeList = convertModelService.convertSignedDeptTree(list);

        SignedDepartmentTree tree = null;

        if(treeList.size() > 0) {
            tree = treeList.get(0);

        }

        return tree;
    }

    @RequestMapping("/listVirtualDept")
    @ResponseBody
    public JsonResult listVirtualDept() {
        List<VirtualDepartment> list = adjustService.listVirtualDept();
        return JsonResult.ok().put("data", list);
    }

    @RequestMapping("/listAdjust")
    @ResponseBody
    public JsonResult listAdjust() {
        List<Adjust> list = adjustService.listAdjust();
        return JsonResult.ok().put("data", convertModelService.convertAdjust(list));
    }

    @RequestMapping("/adjust")
    @ResponseBody
    public JsonResult adjust(String source, String target, int type, String adjustDept, String adjustParent
            , String deptName, String deptNameEN, String deptNameS, int deptLevel, String deptHeader) {
        adjustService.adjust(source, target, type, adjustDept, adjustParent, deptName, deptNameEN, deptNameS, deptLevel, deptHeader);
        return JsonResult.ok();
    }

    @RequestMapping("/listPathTree")
    @ResponseBody
    public SignedDepartmentTree listPathTree(String deptId) {
        List<SignedDepartment> list = adjustService.findSignedDeptAllParent(deptId);
        List<SignedDepartmentTree> treeList = convertModelService.convertSignedDeptTree(list);

        SignedDepartmentTree tree = null;

        if(treeList.size() > 0) {
            tree = treeList.get(treeList.size()-1);
        }
        return tree;
    }


    @RequestMapping("/orgchart/siblings/{deptId}")
    @ResponseBody
    public JsonResult siblings(@PathVariable("deptId") String deptId) {
        List<SignedDepartment> list = adjustService.findSignedDeptSiblings(deptId);
        List<SignedDepartmentTree> treeList = convertModelService.convertSignedDeptTree(list);

        for(SignedDepartmentTree signedDepartmentTree : treeList) {
            signedDepartmentTree.setAdjustAble(false);
        }

        return JsonResult.ok().put("siblings", treeList);
    }

    @RequestMapping("/orgchart/families/{deptId}")
    @ResponseBody
    public JsonResult families(@PathVariable("deptId") String deptId) {
        List<SignedDepartment> list = adjustService.findSignedDeptSiblings(deptId);
        List<SignedDepartmentTree> treeList = convertModelService.convertSignedDeptTree(list);

        for(SignedDepartmentTree signedDepartmentTree : treeList) {
            signedDepartmentTree.setAdjustAble(false);
        }
        return JsonResult.ok().put("siblings", treeList);
    }

    @RequestMapping("/orgchart/children/{deptId}")
    @ResponseBody
    public JsonResult children(@PathVariable("deptId") String deptId) {
        List<SignedDepartment> list = adjustService.findSignedDeptChildren(deptId);
        List<SignedDepartmentTree> treeList = convertModelService.convertSignedDeptTree(list);
        for(SignedDepartmentTree signedDepartmentTree : treeList) {
            signedDepartmentTree.setAdjustAble(false);
        }

        return JsonResult.ok().put("children", treeList);
    }


    @RequestMapping("/adjust/remove")
    @ResponseBody
    public JsonResult remove(@RequestBody long[] ids) {
        adjustService.removeAdjust(ids);
        return JsonResult.ok();
    }

    @RequestMapping("/adjust/release")
    @ResponseBody
    public JsonResult release(@RequestBody long[] ids) {
        adjustService.realseAdjust(ids);
        return JsonResult.ok();
    }


}
