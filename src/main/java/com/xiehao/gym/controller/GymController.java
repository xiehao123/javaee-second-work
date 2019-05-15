package com.xiehao.gym.controller;

import com.xiehao.gym.configuration.ApiVersion;
import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import com.xiehao.gym.service.AccountServer;
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
import java.util.Map;

@RequestMapping("/{version}")
@Controller
public class GymController {
    private GymService gymService;


    @Autowired
    private AccountServer accountServer;

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

    @RequestMapping(value={"/see_account"},method = RequestMethod.GET)
    public String see_account(Map<String, List<Account>> map){
        List<Account> alluser = gymService.getall();
        map.put("alluser",alluser);
        return "see_account";
    }


    //对用户的账号信息的获取
    @RequestMapping(value={"/account"},method = RequestMethod.GET)
    public String get_detail(String username,Map<String, Account> map){
        Account a = gymService.get_detail(username);
        map.put("user",a);
        return "get_detail";
    }

    //对用户账号信息的更新
    @RequestMapping(value={"/account"},method = RequestMethod.PUT)
    public String updata(String username,String mobile,String address,String password,Map<String, Account> map){
        //对账户信息进行修改
        Account b = gymService.get_detail(username);

        if(mobile.length() >0){
            b.setMobileNumber(mobile);
            System.out.println("1");
        }
        if(address.length() >0){
            b.setAddress(address);
            System.out.println("2");
        }
        if(password.length()>0){
            b.setPassword(password);
            System.out.println("3");
        }
        accountServer.updateAccount(b);
        map.put("user",b);
        return "get_detail";
    }

    //删除用户账户
    @RequestMapping(value={"/account"},method = RequestMethod.DELETE)
    public String delete(String username,Map<String, Account> map){
        Account b = gymService.get_detail(username);
        accountServer.deleteeAccount(b);
        map.put("user",b);
        return "get_detail";
    }

    //获取修改用户信息的界面
    @RequestMapping(value={"/updata_info"},method = RequestMethod.GET)
    public String updata_info(String username,Map<String, Account> map){
        Account a = gymService.get_detail(username);
        map.put("user",a);
        return "account_updata";
    }

    //测试版本控制的链接
    @RequestMapping(value={"/vertion_test"},method = RequestMethod.GET)
    public String vertion_test(Map<String, String> map){
        map.put("vertion","v1");
        return "vertion_test";
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
