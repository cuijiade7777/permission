package com.mmall.service;

import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        BeanValidator.check(param);
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        dept.setOperator("cuiceshi");// TODO: 2019/1/16
        dept.setOperateIp("127.0.0.1");// TODO: 2019/1/16
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    private boolean checkExist(Integer parentId,String deptName,Integer deptId){
        return false;// TODO: 2019/1/16
    }

    private String getLevel(Integer deptid){
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptid);
        if(dept == null){
            return null;
        }
        return dept.getLevel();
    }
}
