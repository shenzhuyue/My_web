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
    private List<web_list> web_listList=new ArrayList<web_list>();
    private List<user> userList=new ArrayList<user>();
    @RequestMapping("/register")
    public ModelAndView Register(Model model){
        model.addAttribute("user",new user());
        ModelAndView modelAndView =new ModelAndView("register","userModel",model);
        return modelAndView;
    }
    @PostMapping(value = "register/post")
    public  String register(user temp){
        userList.add(temp);
        UserRepository.save(temp);
        return "redircet:/login";
    }

    @RequestMapping({"/", "/login"})
    public String Login(Model model) {
        return "login";
    }
    @PostMapping(value = "/login/flag")
    public String Login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("admin") String admin,
                        @RequestParam("password") String password) throws IOException {


        if (admin.equals("Erii") && password.equals("2018211523")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", "2018211523");
            return "redirect:/mainPage";
        } else {
            return "redirect:/login";
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
