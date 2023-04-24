package com.ywb.builder;

import com.ywb.session.Configuration;
import org.dom4j.Element;

import java.io.Reader;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public class XMLConfigBuilder extends BaseBuilder{

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        super(new Configuration());
    }
}
