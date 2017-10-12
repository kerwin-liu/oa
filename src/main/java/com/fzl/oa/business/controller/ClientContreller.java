package com.fzl.oa.business.controller;

import com.fzl.oa.business.pojo.QO.ClientQo;
import com.fzl.oa.business.pojo.TClient;
import com.fzl.oa.business.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/12.
 */
//@Controller
//@RequestMapping("clinet")
public class ClientContreller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientContreller.class);
    @Autowired
    private ClientService clientService;

    @GetMapping("getAll")
    public @ResponseBody
    TClient getAll(HttpServletResponse response, ClientQo clientQo) {

        return null;
    }
}
