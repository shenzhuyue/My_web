package com.example.demo;
import com.example.demo.Dao.weblistRepository;
import com.example.demo.Dao.userRepository;
import com.example.demo.Dao.commentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        return "/login";
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
        return "/login";
    }


    private weblistRepository WeblistRepository;

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
    public ModelAndView Index(Model model) {
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "time").ascending());
        model.addAttribute("weblist", web_listList);
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/time")
    public ModelAndView TIME(Model model) {
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "time").descending());
        model.addAttribute("weblist", web_listList);
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/comments")
    public ModelAndView Comments(Model model) {
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "commentcount").descending());
        model.addAttribute("weblist", web_listList);
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @RequestMapping("/mainPage/upvotes")
    public ModelAndView upvotes(Model model) {
        web_listList = WeblistRepository.findAll(Sort.by(Sort.Direction.DESC, "goodcount").descending());
        model.addAttribute("weblist", web_listList);
        ModelAndView modelAndView = new ModelAndView("mainPage", "weblistModel", model);
        return modelAndView;
    }

    @PostMapping("/addPost.action")
    public String Web_list(web_list temp, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        user now_user = UserRepository.findByUsername(user);
        temp.setUserid(now_user.getId());
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
        temp.setUserid(now_user.getId().intValue());
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
        nowuser.getAttention().add(UserRepository.findById(userid).get());

        return "redirect:/mainPage";
    }


}
