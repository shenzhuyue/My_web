package com.example.demo;
import com.example.demo.Dao.weblistRepository;
import com.example.demo.Dao.userRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class web_controllers {

    private userRepository UserRepository;
    @Autowired
    public void setUserRepository(userRepository UserRepository){this.UserRepository=UserRepository;}
    private List<user> userList=new ArrayList<user>();
    @RequestMapping("/register")
    public ModelAndView Register(Model model){
        model.addAttribute("user",new user());
        ModelAndView modelAndView =new ModelAndView("register","userModel",model);
        return modelAndView;
    }
    @PostMapping("register/post")
    public  String register(user temp){
        userList.add(temp);
        UserRepository.save(temp);
        return "/login";
    }
    @PostMapping("/login.action")
    public String Login(HttpServletRequest request, HttpServletResponse response,@RequestParam("admin") String admin,@RequestParam("password") String password) throws IOException {
        user User =new user();
        User.setUsername(admin);
        User.setPassword(password);

        user loginuser=UserRepository.findByUsernameAndPassword(User.getUsername(),User.getPassword());
        if(loginuser == null) {
            return "/login";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            return "redirect:/mainPage";
        }
    }
    @RequestMapping("logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", 0);
        return "/login";
    }





    private weblistRepository WeblistRepository;
    @Autowired
    public void setWeblistRepository(weblistRepository WeblistRepository){this.WeblistRepository=WeblistRepository;}
    private List<web_list> web_listList=new ArrayList<web_list>();
    @RequestMapping({"/","/login"})
    public ModelAndView Login(Model model) {
        return new ModelAndView("/login");
    }
    @RequestMapping("/mainPage")
    public ModelAndView Index(Model model){
        web_listList=WeblistRepository.findAll();
        model.addAttribute("weblist",web_listList);
        ModelAndView modelAndView =new ModelAndView("mainPage","weblistModel",model);
        return modelAndView;
    }
    @PostMapping("/addPost.action")
    public  String Web_list(web_list temp,HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("user");
        user now_user= UserRepository.findByUsername(user);
        temp.setUser_id(now_user.getId());
        Date date=new Date();
        temp.setTime(date.toString());
        temp.setComment_count(0);
        temp.setGood_count(0);
        web_listList.add(temp);
        WeblistRepository.save(temp);
        return "/mainPage";
    }
    @RequestMapping("/addPost")
    public ModelAndView addPost(Model model){
        model.addAttribute("web_list",new web_list());
        ModelAndView modelAndView =new ModelAndView("addPost","PostModel",model);
        return modelAndView;
    }

}
