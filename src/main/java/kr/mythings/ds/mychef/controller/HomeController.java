package kr.mythings.ds.mychef.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/index")
    public String index(){
        log.info("home controller");
        return "index";
    }

    @RequestMapping("/hello")
    public String home(){
        log.info("home controller");
        return "hello";
    }
}
