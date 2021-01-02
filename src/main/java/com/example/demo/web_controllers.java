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
public class web_controllers {
    private weblistRepository WeblistRepository;
    private userRepository UserRepository;
    @Autowired
    public void setWeblistRepository(weblistRepository WeblistRepository){this.WeblistRepository=WeblistRepository;}
    @Autowired
    public void setUserRepository(userRepository UserRepository){this.UserRepository=UserRepository;}
    private List<web_list> web_listList=new ArrayList<web_list>();
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
        return "redirect:/login";
    }

    @RequestMapping({"/"})
    public ModelAndView Login(Model model) {
        return new ModelAndView("/login");
    }
    @PostMapping("/login.action")
    public String Login(@RequestParam("admin") String admin,@RequestParam("password") String password) throws IOException {
        user User =new user();
        User.setUsername(admin);
        User.setPassword(password);

        user loginuser=UserRepository.findByUsernameAndPassword(User.getUsername(),User.getPassword());
        if(loginuser == null) {
            return "redirect:/login";
        }else {
            return "redirect:/mainPage";
        }
    }

    @RequestMapping("/mainPage")
    public ModelAndView Index(Model model){
        web_listList=WeblistRepository.findAll();
        model.addAttribute("weblist",web_listList);
        ModelAndView modelAndView =new ModelAndView("mainPage","weblistModel",model);
        return modelAndView;
    }


}
