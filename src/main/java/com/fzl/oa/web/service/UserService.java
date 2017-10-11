package com.fzl.oa.web.service;

import com.fzl.oa.web.entity.Return;
import com.fzl.oa.web.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xixin on 2017/10/11.
 */

@Service
public class UserService {

    private  static  final Logger logger = LoggerFactory.getLogger(UserService. class);


    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询用户
     * @param name
     * @param psd
     * @return
     */
    public Return findUser(String name, String psd) {
        Return r=new Return();
        String sql="select u.user_name from fd_user u";
        try{
           List<User> u= jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
           if(u!=null && u.size()>=0){
               r.setCode(Return.CODE_SUCCESS);
               return r;
           }
        }catch (Exception e){
            logger.error("添加新用户失败。",e);
        }
        r.setCode(Return.CODE_FAIL);
        r.setMsg("添加用户失败");
        return  r;
    }





}
