package com.jgma.code;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class AuthSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
//        for (int q = 0; q < 50; q++) {
//            printX();
//        }
        printX(3);
    }

    public static void printX(int height){
        for (int i = 10; i > 0; i--) {
            System.out.println();
            // 打印一行 第一列
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("X");
            // 打印一行 最后一列
            for (int k = 0; k <20; k++) {
                if (i==10 || i==1){
                    System.out.print("--");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.print("X");
        }
        for (int h = 0; h < height; h++) {
            System.out.println();
        }
        for (int i = 10; i > 0; i--) {
            System.out.println();
            // 打印一行 第一列
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("X");
            // 打印一行 最后一列
            for (int k = 0; k <20; k++) {
                if (i==10 || i==1){
                    System.out.print("--");
                }else {
                    System.out.print("  ");
                }
            }
            System.out.print("X");
        }
    }

}
