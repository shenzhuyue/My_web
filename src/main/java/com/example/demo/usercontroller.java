package com.example.demo;
import com.example.demo.Dao.weblistRepository;
import com.example.demo.Dao.userRepository;

import java.io.IOException;
import java.util.ArrayList;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class usercontroller {
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

}
