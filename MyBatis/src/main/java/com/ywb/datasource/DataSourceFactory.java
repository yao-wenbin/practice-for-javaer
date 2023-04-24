package com.ywb.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author yaowenbin
 * @Date 2022/7/30
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
