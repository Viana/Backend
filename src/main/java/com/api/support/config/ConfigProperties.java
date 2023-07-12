package com.api.support.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public  class ConfigProperties {
    public synchronized static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/target/classes/config.properties");
        props.load(file);
        return props;
    }
}
