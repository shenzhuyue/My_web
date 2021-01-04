package com.example.demo;
import com.example.demo.Dao.weblistRepository;
import com.example.demo.Dao.userRepository;
import com.example.demo.Dao.commentRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class web_controllers {

    private userRepository UserRepository;

    @Autowired
    public void setUserRepository(userRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    private List<user> userList = new ArrayList<user>();

    @RequestMapping("/register")
    public ModelAndView Register(Model model) {
        model.addAttribute("user", new user());
        ModelAndView modelAndView = new ModelAndView("register", "userModel", model);
        return modelAndView;
    }

    @PostMapping("register/post")
    public String register(user temp) {
        userList.add(temp);
        UserRepository.save(temp);
        return "redirect:/login";
    }

    @PostMapping("/login.action")
    public String Login(HttpServletRequest request, HttpServletResponse response, @RequestParam("admin") String admin, @RequestParam("password") String password) throws IOException {
        user User = new user();
        User.setUsername(admin);
        User.setPassword(password);
        user loginuser = UserRepository.findByUsernameAndPassword(User.getUsername(), User.getPassword());
        HttpSession session = request.getSession();
        if (loginuser == null) {
            session.setAttribute("flag", 0);
            session.setAttribute("loggedin",false);
            return "/login";
        } else {

            session.setAttribute("user", admin);
            session.setAttribute("flag", 1);
            session.setAttribute("loggedin",true);
            return "redirect:/mainPage";
        }
    }

    @RequestMapping("logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("loggedin",false);
        return "redirect:/mainPage";
    }


    private weblistRepository WeblistRepository;
    private int nowpage=0;
    private int pagenum=5;
    @Autowired
    public void setWeblistRepository(weblistRepository WeblistRepository) {
        this.WeblistRepository = WeblistRepository;
    }

    private List<web_list> web_listList = new ArrayList<web_list>();

    @RequestMapping({"/", "/login"})
    public ModelAndView Login(Model model) {
        return new ModelAndView("/login");
    }

    @RequestMapping("/mainPage")
    public ModelAndView Index(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("user"));
        model.addAttribute("loggedin",session.getAttribute("loggedin"));
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "time").ascending());
        if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
            if (web_listList.size()>nowpage*pagenum){
                if (web_listList.size()<=pagenum*(nowpage+1)){
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                }
                else {
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                }
            }

        }
        else {
            nowpage=0;
            if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
                if (web_listList.size()>nowpage*pagenum){
                    if (web_listList.size()<=pagenum*(nowpage+1)){
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                    }
                    else {
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                    }
                }

            }
            else model.addAttribute("weblist", web_listList);
        }
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/time")
    public ModelAndView TIME(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("user"));
        model.addAttribute("loggedin",session.getAttribute("loggedin"));
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "time").descending());
        if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
            if (web_listList.size()>nowpage*pagenum){
                if (web_listList.size()<=pagenum*(nowpage+1)){
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                }
                else {
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                }
            }

        }
        else {
            nowpage=0;
            if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
                if (web_listList.size()>nowpage*pagenum){
                    if (web_listList.size()<=pagenum*(nowpage+1)){
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                    }
                    else {
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                    }
                }

            }
            else model.addAttribute("weblist", web_listList);
        }
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/comments")
    public ModelAndView Comments(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("user"));
        model.addAttribute("loggedin",session.getAttribute("loggedin"));
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "commentcount").descending());
        if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
            if (web_listList.size()>nowpage*pagenum){
                if (web_listList.size()<=pagenum*(nowpage+1)){
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                }
                else {
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                }
            }

        }
        else {
            nowpage=0;
            if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
                if (web_listList.size()>nowpage*pagenum){
                    if (web_listList.size()<=pagenum*(nowpage+1)){
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                    }
                    else {
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                    }
                }

            }
            else model.addAttribute("weblist", web_listList);
        }
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/upvotes")
    public ModelAndView upvotes(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("user"));
        model.addAttribute("loggedin",session.getAttribute("loggedin"));
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "goodcount").descending());
        if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
            if (web_listList.size()>nowpage*pagenum){
                if (web_listList.size()<=pagenum*(nowpage+1)){
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                }
                else {
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                }
            }

        }
        else {
            nowpage=0;
            if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
                if (web_listList.size()>nowpage*pagenum){
                    if (web_listList.size()<=pagenum*(nowpage+1)){
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                    }
                    else {
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                    }
                }

            }
            else model.addAttribute("weblist", web_listList);
        }
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/subscribed")//----------------------------------------------------------------------------------
    public ModelAndView subscribeds(HttpServletRequest request,Model model) {
        web_listList.clear();
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("user"));
        model.addAttribute("loggedin",session.getAttribute("loggedin"));
        user nowuser=UserRepository.findByUsername((String) session.getAttribute("user"));
        int nowuserid=nowuser.getId();
        List<user> attention=nowuser.getAttention();
        for(user atuser:attention){
            web_listList.addAll(WeblistRepository.findAllByUserid(atuser.getId()));
        }
        if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
            if (web_listList.size()>nowpage*pagenum){
                if (web_listList.size()<=pagenum*(nowpage+1)){
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                }
                else {
                    model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                }
            }

        }
        else {
            nowpage=0;
            if(web_listList.size()>nowpage*pagenum&&nowpage>=0){
                if (web_listList.size()>nowpage*pagenum){
                    if (web_listList.size()<=pagenum*(nowpage+1)){
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,web_listList.size()));
                    }
                    else {
                        model.addAttribute("weblist", web_listList.subList(nowpage*pagenum,(nowpage+1)*pagenum));
                    }
                }

            }
            else model.addAttribute("weblist", web_listList);
        }
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }
    @RequestMapping("/mainPage/prepage")
    public String prepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        nowpage--;
        return "redirect:/mainPage";
    }
    @RequestMapping("/mainPage/nextpage")
    public String nextpage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        nowpage++;
        return "redirect:/mainPage";
    }
    @RequestMapping("/mainPage/first")
    public String first(HttpServletRequest request, HttpServletResponse response) throws IOException {
        nowpage=0;
        return "redirect:/mainPage";
    }
    public int imagecount=1;
    @PostMapping("/addPost.action")
    public String Web_list(web_list temp, HttpServletRequest request, HttpServletResponse response) {

        if(temp.getImagepath()!=""){
            File originalFile = new File(temp.getImagepath());

            String newpath="D:\\学习\\大三上\\web基础\\BigWork\\src\\main\\resources\\static\\"+imagecount+".jpg";

            String newpath2=""+imagecount+".jpg";

            File result = new File(newpath);
            try {
                FileInputStream in = new FileInputStream(originalFile);
                FileOutputStream out = new FileOutputStream(result);// 指定要写入的图片
                int n = 0;// 每次读取的字节长度
                byte[] bb = new byte[1024];// 存储每次读取的内容
                while ((n = in.read(bb)) != -1) {
                    out.write(bb, 0, n);// 将读取的内容，写入到输出流当中
                }
                out.close();
                in.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp.setImagepath(newpath2);

        }
        imagecount++;
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        user now_user = UserRepository.findByUsername(user);
        temp.setUserid(now_user.getId());
        temp.setUsername(now_user.getUsername());
        Date date = new Date();
        temp.setTime(date.toString());
        temp.setCommentcount(0);
        temp.setGoodcount(0);
        web_listList.add(temp);
        WeblistRepository.save(temp);
        return "redirect:/mainPage";
    }

    @RequestMapping("/addPost")
    public ModelAndView addPost(Model model) {
        model.addAttribute("web_list", new web_list());
        ModelAndView modelAndView = new ModelAndView("addPost", "PostModel", model);
        return modelAndView;
    }

    private commentRepository CommentRepository;
    @Autowired
    public void setCommentRepository(commentRepository CommentRepository) {
        this.CommentRepository = CommentRepository;
    }
    public void setCommentRepository(){}
    private List<comment> commentlist = new ArrayList<comment>();


    @RequestMapping("/comment/{id}")
    public ModelAndView comments(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer webid) throws IOException {
        user User = new user();
        web_list nowweb = new web_list();
        comment nowcomment = new comment();
        user loginuser = UserRepository.findByUsernameAndPassword(User.getUsername(), User.getPassword());
        nowweb = WeblistRepository.findById(webid).get();
        commentlist = CommentRepository.findAllByWebid(nowweb.getWebid());
        HttpSession session = request.getSession();
        session.setAttribute("nowwebid",nowweb.getWebid());
        model.addAttribute("commentlist", commentlist);
        model.addAttribute("nowweb", nowweb);
        model.addAttribute("comment", new comment());
        ModelAndView modelAndView = new ModelAndView("comments", "commentModel", model);
        return modelAndView;
    }
    @PostMapping("/comments.action")
    public String commentaction(comment temp, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        temp.setWebid((int)session.getAttribute("nowwebid"));
        int nowID=(int)session.getAttribute("nowwebid");
        Date date = new Date();
        temp.setComment_time(date.toString());
        String user = (String) session.getAttribute("user");
        user now_user = UserRepository.findByUsername(user);
        temp.setUserid(now_user.getId());
        temp.setUsername(now_user.getUsername());
        web_list nowweb=WeblistRepository.findById(nowID).get();
        nowweb.setCommentcount(nowweb.getCommentcount()+1);
        WeblistRepository.save(nowweb);
        commentlist.add(temp);
        CommentRepository.save(temp);
        return "redirect:/mainPage";
    }
    @RequestMapping("/addGood/{id}")
    public String addGood(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Integer webid){
        HttpSession session = request.getSession();
        session.setAttribute("nowwebid",webid);
        web_list nowweb=WeblistRepository.findById(webid).get();
        nowweb.setGoodcount(nowweb.getGoodcount()+1);
        WeblistRepository.save(nowweb);
        return "redirect:/mainPage";
    }

    @RequestMapping("/subscribe/{id}")
    public String attention(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Integer userid){
        HttpSession session = request.getSession();
        String nowusername=(String)session.getAttribute("user");
        user nowuser=UserRepository.findByUsername(nowusername);
        List<user> nowattention=nowuser.getAttention();
        nowattention.add(UserRepository.findById(userid).get());
        nowuser.setAttention(nowattention);
        UserRepository.save(nowuser);
        return "redirect:/mainPage";
    }





}
