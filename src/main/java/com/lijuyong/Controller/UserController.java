package com.lijuyong.Controller;

import com.lijuyong.Domain.dao.mapper.House365Mapper;
import com.lijuyong.Domain.dao.mapper.UserMapper;
import com.lijuyong.Domain.dao.model.LinkDO;
import com.lijuyong.Domain.dao.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lijuyong.Domain.Model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 2017/2/9.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private House365Mapper house365Mapper;
    @Autowired
    private UserMapper userMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index(){
        return  "good day";
    }
    @RequestMapping(value = "/showUser",params = "id")
    public UserDO showUser(Integer id){
        return userMapper.showUser(id);
    }
    @RequestMapping("/admin")
    public User administrator(){
        return  new User("000000","Administrator");
    }
    @RequestMapping("/link")
    public LinkDO crawLink(){
        return  new LinkDO("http://www.baidu.com",new Date());
    }
    @RequestMapping("/lstUrls")
    public  List<LinkDO> listCrawlUrl(){
        List<LinkDO> linkDO = house365Mapper.listUrl();
        return  linkDO;
    }
    @RequestMapping("/searchUrls/{houseId}")
    public  List<LinkDO> searchCrawlUrl(@PathVariable String houseId){
        List<LinkDO> linkDO = house365Mapper.findUrls(houseId);
        return  linkDO;

    }





    @RequestMapping("/setsession")
    public SessionData setSession(HttpServletRequest request){
        SessionData sessionData = new SessionData("lijuyong","123456");
        request.getSession().setAttribute("sessionData",sessionData);
        return sessionData;

    }

    @RequestMapping("/getsession")
    public SessionData getSession(HttpServletRequest request){
        return  (SessionData)request.getSession().getAttribute("sessionData");
    }

    @RequestMapping("/forbidden")
    public JsonResult forbidden(){
        return  new JsonResult(403,"登录失败啦");
    }

    @RequestMapping("/logtest")
    public JsonResult logtest(){
        throw  new  IllegalArgumentException("这个是一个测试日志的异常");
        //return  new JsonResult(403,"异常验证");
    }

    @RequestMapping("/hello")
    public JsonResult hello(){
        log.info("这是一个简单的日志");
        return  new JsonResult(403,"这是一个伟大的开端");
    }


}
