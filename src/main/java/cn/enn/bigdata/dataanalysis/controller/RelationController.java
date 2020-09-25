package cn.enn.bigdata.dataanalysis.controller;

import cn.enn.bigdata.dataanalysis.service.RelationOp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "关系处理",tags = "关系处理")
@RestController
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    private RelationOp relationOp;

    @ApiOperation(value = "获取父节点", notes = "获取父节点")
    @PostMapping("/getParent")
    public List<Long> getParent(@RequestBody Long id){
        List<Long> parents = new ArrayList<>();
parents =relationOp.getParent(id);
        return parents;
    }

    @ApiOperation(value = "获取子节点", notes = "获取子节点")
    @PostMapping("/getSons")
    public List<Long> getSons(@RequestBody Long id){
        List<Long> sons = new ArrayList<>();
sons = relationOp.getSons(id);
        return sons;
    }
}
