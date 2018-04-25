package com.ytsssss.collaborationblog.cotroller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.deploy.net.URLEncoder;
import com.ytsssss.collaborationblog.json.JsonResult;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/31 10:38
 */
@RestController
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucketName}")
    private String bucketName;
    @Value("${qiniu.domainOfBucket}")
    private String domainOfBucket;

    /**
     * 上传文件，传入本地文件路径，得到url
     * @param localFilePath
     * @return
     */
    @PostMapping(value = "/file/upload")
    public Object upload(@RequestParam("localFilePath") String localFilePath ){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = getUrl(putRet.hash);
            logger.info("上传文件返回的url为："+url);
            return JsonResult.success(url);
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            return JsonResult.fail(r.toString());
        }
    }

    /**
     * 获取七牛云上传token
     * @return
     */
    @GetMapping(value = "/file/getToken")
    public Object getToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);
        return JsonResult.success(upToken);
    }

    protected String getUrl(String fileName) {
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        logger.info("输出的url为： "+finalUrl);
        return finalUrl;
    }
}
