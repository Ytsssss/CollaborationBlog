package com.ytsssss.collaborationblog.cotroller;

import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/31 11:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {
    @Autowired
    private FileController fileController;
    @Test
    public void upload() {
        fileController.upload("C:\\Users\\Administrator\\Desktop\\人人.png");
    }
    @Test
    public void download() {
        fileController.getUrl("Fjy_z_h2buO4zTdDDe76A-_F0yA4");
    }
}