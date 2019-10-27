package com.qf.myblog.temp;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Test {
    public static void main(String[] args) {
        //一个参数表示 简单散列（不安全）
        //密码  用户名   加密次数
        Md5Hash md5Hash = new Md5Hash("573678","sy",100);
        System.out.println(md5Hash.toString());
    }
}
