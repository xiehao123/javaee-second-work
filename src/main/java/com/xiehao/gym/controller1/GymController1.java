package com.xiehao.gym.controller1;


import com.xiehao.gym.configuration.ApiVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/{version}")
@Controller
@ApiVersion(2)
public class GymController1 {

    //测试版本控制的链接
    @RequestMapping(value={"/vertion_test"},method = RequestMethod.GET)
    public String vertion_test(Map<String, String> map){
        map.put("vertion","v2");
        return "vertion_test";
    }

}
