package com.lanou.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/13.
 */
public class GenTest {

    // generator方法和main方法是固定的，每次都是这个
    // 但是每次执行都会覆盖掉原来的内容，xml文件会继续在后面拼接内容
    // 所以第一次执行完就不要总是执行！！！

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件

        // 每次把这里的路径改一下就行－－路径指向resources文件夹下的generatorConfig.xml文件！！！
        File configFile = new File("src/main/resources/generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) {

        try {
            GenTest genTest = new GenTest();
            genTest.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
