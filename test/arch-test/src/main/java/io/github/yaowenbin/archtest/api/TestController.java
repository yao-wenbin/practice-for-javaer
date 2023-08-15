package io.github.yaowenbin.archtest.api;

import io.github.yaowenbin.archtest.persistent.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/7/28
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper mapper = new TestMapper();

    public List<Object> get() {
        return mapper.list();
    }

}
