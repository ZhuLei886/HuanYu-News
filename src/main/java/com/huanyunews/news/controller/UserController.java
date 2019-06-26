package com.huanyunews.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huanyunews.news.excel.User;
import com.huanyunews.news.mapper.UserMapper;
import com.huanyunews.news.service.UserService;
import com.huanyunews.news.util.Response;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    public UserMapper mapper;
    @Autowired
    public UserService service;
    private Integer state_user;
    @RequestMapping("/tologin")
    public String toLogin(){
        return "/app/login.html";
    }
    @RequestMapping("/register")
    @ResponseBody
    public Response register(String t_user_account, String t_user_password, String t_user_email) {
        //service内容;
        Map<String,String> map=service.regUser(t_user_account,t_user_password,t_user_email);
        if (map.get("ok") != null) {
            return new Response(0, "系统已经向你的邮箱发送了一封邮件哦，验证后就可以登录啦~");
        } else {
            return new Response(1, "error", map);
        }
    }
    @RequestMapping("/tologinactivate")
    public String activate(String code) {
        service.activate(code);
        return "redirect:/tologin#activateSuccess";
    }
    @RequestMapping("/nodes")
    @ResponseBody
    public Map getNodes(@RequestParam String  pageNum,String pageSize) {
        //访问数据库
        Map map=new HashMap();
        //初始化分页插件
        int temp1=1;
        if(pageNum!=null){
            temp1=Integer.parseInt(pageNum);
        }
        int temp2=1;
        if(pageSize!=null){
            temp2=Integer.parseInt(pageSize);
        }
        PageHelper.startPage(temp1,temp2);

        List<Map<String,Object>> users=mapper.getAllUsers();
        PageInfo page=new PageInfo(users);
//       map.put("users",users);
        map.put("page",page);
        return  map;
    }

    @RequestMapping("/login")
    public ModelAndView userLogin(String t_user_account, String t_user_password, HttpSession session) {
        //在数据库中检验浏览器提交上来的数据
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> list = mapper.userLogin(t_user_account, t_user_password);
        Map t_user_id=mapper.userLogin2(t_user_account,t_user_password);
        if (list.size() > 0){
            int state=Integer.parseInt(list.get(0).get("t_user_state").toString());
            state_user=state;
        }
        if (list.size() > 0&&state_user==1) {
            //登录成功
            //存储session
            String t_user_url=list.get(0).get("t_user_url").toString();
            String id=list.get(0).get("t_user_id").toString();
            session.setAttribute("user", list.get(0));
            session.setAttribute("t_user",t_user_account);
            session.setAttribute("t_user_url",t_user_url);
//            session.setAttribute("t_user_url",t_user_url);
            session.setAttribute("t_user_id",t_user_id);
            session.setAttribute("t_id",id);
            modelAndView.setViewName("/app/index.html");
            modelAndView.addObject("user",list.get(0));
            //把菜单数据带首页-
            List nodes = mapper.getUserNode(list.get(0).get("t_user_id").toString());
            //把菜单数据放到域
            session.setAttribute("nodes", nodes);
//            modelAndView.addObject("nodes",nodes);
            return modelAndView;
        } else {
            //登录失败
            modelAndView.setViewName("/app/login.html");
            modelAndView.addObject("error", "密码错误或者未激活哦");
            return modelAndView;
        }

    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        //消除session登录信息
        session.invalidate();
        return "/app/login.html";
    }

    @RequestMapping("/usermanagement")
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView();
        List users = mapper.getAllUsers();
        mv.addObject("allusers", users);
        mv.setViewName("/app/users.html");
        return mv;
    }
    @RequestMapping("/userhome")
    public ModelAndView UserHome(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String t_user_account=session.getAttribute("t_user").toString();
        List<Map<String,Object>> users=mapper.getUserHome(t_user_account);
        mv.addObject("alluserhome",users);
        mv.setViewName("/app/userhome.html");
        return mv;
    }
    @RequestMapping("/newpassword")
    @ResponseBody
    public Map NewPassWord(@RequestParam Map map){
        Map map1=new HashMap();
        mapper.NewPassWord(map);
        map1.put("error","success");
        return map1;
    }
    @RequestMapping("/text")
    public ModelAndView gettext() {
        ModelAndView mv = new ModelAndView();
        List users = mapper.getAllUsers();
        mv.addObject("allusers", users);
        mv.setViewName("/edit/edit.html");
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteUser(String t_user_id) {
        ModelAndView mv = new ModelAndView();
        if (mapper.deleteById(t_user_id)) {
            List users = mapper.getAllUsers();
            mv.addObject("allusers", users);
            String success = "删除成功！";
            mv.addObject("success", success);
            mv.setViewName("/app/users.html");

        } else {
            List users = mapper.getAllUsers();
            mv.addObject("allusers", users);
            mv.setViewName("/app/users.html");
            String error = "删除失败？？？";
            mv.addObject("error", error);
        }
        return mv;
    }

    @RequestMapping("/newuser")
    public ModelAndView insertUser() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/app/newusers.html");
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView updateUser(String t_user_id) {
        ModelAndView mv = new ModelAndView();
        List<Map<String, Object>> users = mapper.queryUserById(t_user_id);
        mv.addObject("updateuser", users.get(0));
        mv.setViewName("/app/updateusers.html");
        return mv;
    }

    @RequestMapping("/edituser")
    public ModelAndView updateUser(String t_user_account, String t_user_password, String t_user_id) {
        ModelAndView mv = new ModelAndView();
        mapper.editUser(t_user_account, t_user_password, t_user_id);
        List users = mapper.getAllUsers();
        mv.addObject("allusers", users);
        mv.setViewName("/app/users.html");
        return mv;
    }

    @RequestMapping("/saveuser")
    public ModelAndView saveUser(String t_user_account, String t_user_password) {
        ModelAndView mv = new ModelAndView();
        mapper.insertUser2(t_user_account, t_user_password);
        List users = mapper.getAllUsers();
        mv.addObject("allusers", users);
        mv.setViewName("/app/users.html");
        return mv;
    }

    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<User> classmateList = mapper.teacherinfor();

        String fileName = "userinf" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"学号", "姓名", "身份类型", "登录密码"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (User user : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getT_user_id());
            row1.createCell(1).setCellValue(user.getT_user_account());
            row1.createCell(2).setCellValue(user.getT_user_password());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
    @RequestMapping("/touxiangurl")
    @ResponseBody
    public Map UpdateNewTX(String t_url,HttpSession session){
        Map map1=new HashMap();
        String t_user_account=session.getAttribute("t_user").toString();
        mapper.userhomeimg(t_url,t_user_account);
        map1.put("error","success");
        return map1;
    }
}
