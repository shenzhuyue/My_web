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
    public  String Web_list(web_list temp){
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
