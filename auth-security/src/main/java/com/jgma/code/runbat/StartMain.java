package com.jgma.code.runbat;

import org.mybatis.generator.api.ShellRunner;

/**
 * Created By majg on 2020-07-01
 */
public class StartMain {
    public static void main(String[] args) {

        args = new String[] { "-configfile", "D:\\gitSource\\auth-management\\auth-security\\src\\main\\resources\\generatorConfig.xml", "-overwrite" };
        ShellRunner.main(args);
    }
}
