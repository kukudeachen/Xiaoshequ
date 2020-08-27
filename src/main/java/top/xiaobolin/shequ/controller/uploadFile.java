package top.xiaobolin.shequ.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.xiaobolin.shequ.pojo.WangEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author：xiaobolin
 * @date 2020/8/27/0027 - 14:48
 */
@Controller
public class uploadFile {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public WangEditor uploadFile(
            @RequestParam("myFile")MultipartFile multipartFile,
            HttpServletRequest request
            ){
        try {
            // 获取项目路径
            String realPath = request.getSession().getServletContext()
                    .getRealPath("");
            System.out.println("路径"+realPath);
            InputStream inputStream = multipartFile.getInputStream();
            String contextPath = request.getContextPath();
            System.out.println("路径"+contextPath);
            // 服务器根目录的路径
            String path = realPath.replace(contextPath.substring(0), "");
            System.out.println("服务器根目录"+path);
            // 根目录下新建文件夹upload，存放上传图片
            String uploadPath = path + "upload";
            // 获取文件名称
            String filename = multipartFile.getOriginalFilename();
            System.out.println("文件名字"+filename);
            // 将文件上传的服务器根目录下的upload文件夹
            File file = new File(uploadPath, filename);
            FileUtils.copyInputStreamToFile(inputStream, file);
            // 返回图片访问路径
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/upload/" + filename;

            String [] str = {url};
            WangEditor we = new WangEditor(str);
            return we;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
