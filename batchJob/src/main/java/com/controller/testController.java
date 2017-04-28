package com.controller;

import com.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinqiang.liu on 17-3-30.
 */

@RestController
public class testController {

    @Autowired
    private Person person;

    @RequestMapping(value = "/person/get",method = RequestMethod.GET)
    public  Person getPerson(){
        person.setName("liujq" );
        person.setType("smart");
        return person;
    }
}
