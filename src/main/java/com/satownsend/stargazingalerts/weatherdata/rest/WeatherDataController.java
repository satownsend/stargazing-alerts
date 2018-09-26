package com.satownsend.stargazingalerts.weatherdata.rest;

import com.satownsend.stargazingalerts.user.dao.UserDao;
import com.satownsend.stargazingalerts.user.model.User;
import com.satownsend.stargazingalerts.weatherdata.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherDataController {

    @Autowired private UserDao userDao;
    @Autowired private WeatherDataService weatherDataService;

    @PostMapping(path="/triggerIt/{userId}")
    public @ResponseBody String triggerIt(@PathVariable(value="userId") Long userId) throws Exception {
        User user = userDao.findById(userId);
        weatherDataService.run(user);
        return "thumbs up";
    }

    @PostMapping(path="/triggerAll")
    public @ResponseBody String triggerAll() throws Exception {
        weatherDataService.runForAllUsers();
        return "thumbs up";
    }

}