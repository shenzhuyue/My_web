package com.example.demo;
import com.example.demo.Dao.weblistRepository;

import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class web_controllers {
    private weblistRepository WeblistRepository;
    @Autowired
    public void setWeblistRepository(weblistRepository WeblistRepository){this.WeblistRepository=WeblistRepository;}
    private List<web_list> web_listList=new ArrayList<web_list>();
    @RequestMapping("/mainPage")
    public ModelAndView Index(Model model){
        web_listList=WeblistRepository.findAll();
        model.addAttribute("weblist",web_listList);
        ModelAndView modelAndView =new ModelAndView("mainPage","weblistModel",model);
        return modelAndView;
    }


}
