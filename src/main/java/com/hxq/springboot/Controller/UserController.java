package com.hxq.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxq.springboot.entity.User;
import com.hxq.springboot.mapper.UserMapper;
import com.hxq.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping
    public boolean save(@RequestBody User user){
        //新增或者更新 不知道为什么一直返回0
        return userService.saveUser(user);
    }

    //查询所有数据
    @GetMapping("/")
    public List<User> findall(){
        //List<User> all=userMapper.findall();
        return userService.list();
    }

    //删除
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        //return userMapper.deleteById(id);
        return userService.removeById(id);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        //return userMapper.deleteById(id);
        return userService.removeBatchByIds(ids);
    }

    //分页查询
    //接口路径 "/user/page"
    //@RequestParam 从前端接受
    //limit第一个参数(该页第一个数据序号)=（pageNum-1）*pageSize
    //Map<String,Object> 封装结果参数
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String username){
//        pageNum=(pageNum-1)*pageSize;
//        Integer total=userMapper.selectTotal(username);
//        username="%"+username+"%";
//        List<User> data= userMapper.selectPage(pageNum,pageSize,username);
//        Map<String,Object> res= new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }
    //mybatisplus分页
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address
                                ){
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }
}
