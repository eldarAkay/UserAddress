package com.mustafaiev.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Retrieves index page
 */
@Controller
public class NavigationController {
    protected static Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model) {
        logger.debug("Received request to show index page");

        return "index";
    }
}
