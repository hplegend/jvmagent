package com.hepeng.controller;

import com.hepeng.service.SystemVmAttachService;
import com.hepeng.service.SystemVmListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 11:25
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SystemVmListService vmListService;
    @Autowired
    private SystemVmAttachService  attachService;

    @RequestMapping("/test.view")
    public ModelAndView test () {

        System.out.print("enter");

        vmListService.listAllVm();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/test");
        return mv;
    }


    @RequestMapping("/attach.view")
    public ModelAndView attach (@RequestParam("vmId") String vmId) {

        System.out.print("enter");

        attachService.attach(vmId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/test");
        return mv;
    }


}
