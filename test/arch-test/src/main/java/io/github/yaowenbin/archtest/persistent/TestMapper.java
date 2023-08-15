package io.github.yaowenbin.archtest.persistent;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/7/28
 */
@Repository
public class TestMapper {

    public List<Object> list() {
        return new ArrayList<>();
    }

}
