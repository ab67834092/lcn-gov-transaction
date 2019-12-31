package com.cjb.controller;

import com.cjb.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by cjb on 2018/12/25.
 */
@Controller
@RequestMapping("/portal")
public class TestController {

    @Autowired
    PortalService portalService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test(){
        portalService.test();
    }

}
