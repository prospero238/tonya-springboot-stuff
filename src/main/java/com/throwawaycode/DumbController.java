package com.throwawaycode;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DumbController {

    @Value("${what.to.say:IM_DEFINED_IN_THE_CONTROLLER_ANNOTATION}")
    private String whatToSay;

    private static final Logger LOG = LoggerFactory.getLogger(DumbController.class);

    @RequestMapping("/saywhat")
    public ModelAndView say() {
        LOG.info("whatToSay={}", whatToSay);
        HashMap<String, Object> model = new HashMap<>();
        model.put("whatToSay", whatToSay);
        return new ModelAndView("sayit", model);
    }

}
