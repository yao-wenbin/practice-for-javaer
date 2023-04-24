package com.ywb.controller;

import com.ywb.entity.Liquor;
import com.ywb.mpper.LiquorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2022/7/12
 */
@RestController
public class LiquorController {
    @Autowired
    private LiquorMapper liquorMapper;

    @GetMapping("/find")
    public List<Liquor> findLiquor(){
        return liquorMapper.selectList(null);
    }
}

