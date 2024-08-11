////Helper class to write location of directory where you want to store or upload file
//
//package com.gk.helper;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//@Component
//public class Helper {
//
////    This is for static uploading
////    public final String directory = "C:\\Users\\LENOVO\\Desktop\\TDIT\\SpringCRUD\\com.gk\\src\\main\\resources\\static\\image";
//
////    This is for dynamic uploading
//    public final String directory = new ClassPathResource("static/image/").getFile().getAbsolutePath();
//
//    public Helper()throws IOException {}
//
//    public boolean uploadfiles(MultipartFile mfile) {
//        boolean upload = false;
//
//        try {
//            Files.copy(mfile.getInputStream(), Paths.get(directory+ File.separator+mfile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return upload = true;
//    }
//}
//
