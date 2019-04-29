package com.xiehao.gym.controller;

import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import com.xiehao.gym.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GymController {
    private GymService gymService;
    Boolean online=false;

    @Autowired
    public void setContactService(GymService gymService) {
        this.gymService = gymService;
    }

    @RequestMapping(value={"","/","/index"},method = RequestMethod.GET)
    public String index(Model model){
        if(online){
            model.addAttribute("show","true");
        }
        return "index";
    }

    @RequestMapping(value={"","/","/index"},method = RequestMethod.POST)
    public String login(@Valid Account account, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("error",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("show","true");
            return "index";
        }
        if(gymService.login(account.getUsername(),account.getPassword())==false){
            model.addAttribute("error","账号或密码错误");
            model.addAttribute("show","true");
            return "index";
        }
        model.addAttribute("status","online");
        model.addAttribute("name",account.getUsername());
        online=true;
        return "index";

    }
    @RequestMapping(value={"/register"},method = RequestMethod.POST)
    public String register(@Valid Account account,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("error",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("showreg","true");
            return "index";
        }
        if(gymService.register(account)==false){
            model.addAttribute("error","账号已经注册");
            model.addAttribute("showreg","true");
            return "index";
        }
        return "index";
    }
    @RequestMapping(value={"/search"},method = RequestMethod.GET)
    public String about(Model model){
        if(online){
            model.addAttribute("status","online");
        }
        return "about";
    }

    @RequestMapping(value={"/search"},method = RequestMethod.POST)
    public String searchcoach(String key, Model model){
        if(online){
            model.addAttribute("status","online");
        }
        Page<Info> contacts=gymService.search(4,5,key);
        model.addAttribute("fo",contacts);

        return "about";

    }
    @RequestMapping(value={"/searchaccount"},method = RequestMethod.POST)
    public String searchaccount(String tname,Model model){
        if(online){
            model.addAttribute("status","online");
        }
        if(tname!=null){
            String pass= gymService.searchAccount(tname);
            if(pass!=null){
                String temp[]=pass.split("#");
                model.addAttribute("username",tname);
                model.addAttribute("password",temp[0]);
                model.addAttribute("mobile",temp[1]);
                model.addAttribute("address",temp[2]);
            }
        }else{
            return "index";
        }
        return "about";
    }

}
