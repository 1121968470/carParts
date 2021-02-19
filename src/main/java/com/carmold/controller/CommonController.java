package com.carmold.controller;

import com.carmold.vo.CallResult;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;


/**
 * TODO
 *
 * @author :李志文
 * @date 2019/9/4 20:01
 */
@RestController
@RequestMapping("/api/{version}/common/upload/")
public class CommonController {
    @Value("${attend.img.dir}")
    String fileUrlPrefixDir;
//    @Value("${yun.oss.appId}")
//    String appId;
//    @Value("${yun.oss.appSecret}")
//    String appSecret;
//    @Value("${yun.oss.endPoint}")
//    String endPoint;
//    @Value("${yun.oss.bucket}")
//    String bucket;

    @PostMapping("file")
    public CallResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) {
        //本地缓存
        CallResult callResult=new CallResult();
        String fileName = file.getOriginalFilename();
        String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
                : null;
        String newFileName = new Date().getTime() + "." + type; //UUID.randomUUID()
        String url = saveToLocalMachine(file, httpServletRequest, newFileName);
        System.out.println("url: "+url);
        Map<String, String> data = new HashMap<String, String>();
        data.put("url", url);
        callResult.setData(data);
        return callResult;
    }
    @PostMapping("files")
    public CallResult uploadMutiFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest httpServletRequest) {
        //本地缓存
        CallResult callResult=new CallResult();
        List<String>urls=new ArrayList<>();
        if(files!=null&&files.length>0){
            for(MultipartFile file :files){
                String fileName = file.getOriginalFilename();
                String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
                        : null;
                String newFileName = UUID.randomUUID().toString() + "." + type;
                System.out.println(newFileName);
//                String url = saveToYun(file, httpServletRequest, newFileName);
                String url = saveToLocalMachine(file, httpServletRequest, newFileName);
                urls.add(url);
            }
        }
        Map<String, List<String>> data = new HashMap<String,  List<String>>();
        data.put("urls", urls);
        callResult.setData(data);
        return callResult;

    }

//    public String saveToYun(MultipartFile file, HttpServletRequest httpServletRequest, String fileName) {
//        ObsClient obsClient = new ObsClient(appId, appSecret, endPoint);
//        try {
//            PutObjectResult putObjectResult = obsClient.putObject(bucket, fileName, new ByteArrayInputStream(file.getBytes()));
//            return putObjectResult.getObjectUrl();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String saveToLocalMachine(MultipartFile file, HttpServletRequest httpServletRequest, String fileName) {
        CallResult callResult = new CallResult();
        callResult.succee("成功");
        String host = getHost(httpServletRequest);
        FileOutputStream fos = null;
//        String fileUrl = host + "upload" + File.separator + fileName;
        String fileUrl =  "/upload" + File.separator + fileName;
        try {
            String dirPath = fileUrlPrefixDir;
            File dir = new File(dirPath);
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdir();
            }
            //windows
//            fos = new FileOutputStream(new File("D:/project/carParts/upload/" + File.separator + fileName));
            //linux
            fos = new FileOutputStream(new File(fileUrlPrefixDir + File.separator + fileName));
            fos.write(file.getBytes());
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            callResult.fail("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            callResult.fail("服务异常");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    callResult.fail("服务异常");
                }
            }
        }
        return fileUrl;

    }

    private String getHost(HttpServletRequest httpServletRequest) {
        StringBuffer url = httpServletRequest.getRequestURL();
        return url.delete(url.length() - httpServletRequest.getRequestURI().length(), url.length()).append("/").toString();
    }

}
