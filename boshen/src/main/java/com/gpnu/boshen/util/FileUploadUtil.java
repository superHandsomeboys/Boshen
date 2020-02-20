package com.gpnu.boshen.util;

import com.gpnu.boshen.dto.FileInfo;
import com.gpnu.boshen.dynamic.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    /**
     * 检验图片后缀是否为.jpg或.png，不是就返回空
     * 图片重命名，生成唯一文件名后，放在以authorid为名的文件夹下
     * 若上传异常，向外抛出
     * @param authorId
     * @param image 文件类
     * @return  文件封装类
     * @throws IOException
     */
    public static FileInfo image(int authorId, MultipartFile image) throws IOException{
        //1.验证文件是否为图片，既文件后缀名是否为.jpg或.png
        String imageName = image.getOriginalFilename();
        String substring = imageName.substring(imageName.lastIndexOf(".") + 1);
//        if(!"ZIP".equals(substring.toUpperCase())){}
        if(!substring.equals("jpg") && !substring.equals("png")){
            return null;
        }
        //2.图片上传，拿到url——根目录开始的路径/usr/local/xxx.jpg
        String fromPath = Data.UPLOAD_IMAGE_PATH;
        String backPath = authorId + Data.SEPARATOR + KeyUtil.genUniqueKey();   //获得唯一文件名,id/xxx.jpg
        if (substring.equals("jpg")){
            backPath = backPath+".jpg";
        }
        if (substring.equals("png")){
            backPath = backPath+".png";
        }
        File finalPath = new File(fromPath+backPath);   //路径+文件名
        if(!finalPath.getParentFile().exists()) //创建id的文件夹，不知道可不可以多级创建
            finalPath.getParentFile().mkdirs();
        FileInfo fileInfo = new FileInfo();

        File dest = new File(fromPath+backPath);
        image.transferTo(dest); //上传图片，可能有异常
        //封装类
        fileInfo.setFrontPath(fromPath);
        fileInfo.setBackPath(backPath);
        return fileInfo;
    }
}
