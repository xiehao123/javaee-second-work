package com.xiehao.gym;

import com.xiehao.gym.domain.Info;
import com.xiehao.gym.repository.GymRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymApplicationTests {
    @Autowired
    private GymRepository repository;

//    @Test
//    public void findCityAndHotelByHQLResultObj() throws Exception {
//        int page=1,size=10;
//        Pageable pageable = new PageRequest(page, size);
//        List<Info> cityAndHotelByHQLResultObj = repository.findclass("ball");
//        Assert.assertTrue(cityAndHotelByHQLResultObj.size() > 0);
//    }


}
