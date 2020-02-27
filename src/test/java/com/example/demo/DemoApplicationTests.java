package com.example.demo;

import com.example.demo.dao.PmsBaseAttrInfoDao;
import com.example.demo.dao.PmsProductInfoDao;
import com.example.demo.entity.PmsBaseAttrInfo;
import com.example.demo.entity.PmsProductInfo;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    PmsProductInfoDao pmsProductInfoDao;
    @Resource
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;

    @Test
    public void contextLoads2() {
//        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
//        pmsBaseAttrInfo.setAttrName("测试");
//        Long insert = pmsBaseAttrInfoDao.insert(pmsBaseAttrInfo);
//        System.out.println(insert);

        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setProductName("测试3");
        pmsProductInfo.setDescription("测试3");
        pmsProductInfoDao.insert(pmsProductInfo);
        System.out.println(pmsProductInfo.getId());
    }

    @Test
    public void contextLoads() throws IOException, MyException {
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String orginalFilename = "C:\\Users\\Admin\\Desktop\\1.jpeg";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpeg", null);
        for (String s : upload_file) {
            System.out.println("s = " + s);
        }
    }

}
