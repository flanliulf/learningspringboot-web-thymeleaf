package com.fancyliu.learningspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    private static final String UPLOAD_DIR = "/Users/flanliulf/Downloads/fileuploadtest/";


    @GetMapping("/index")
    public String index() {
        return "upload";
    }

    @GetMapping("/result")
    public String result() {
        return "upload-result";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择需要上传的文件");
            return "redirect:/file/result";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "上传文件 " + file.getOriginalFilename() + " 成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/file/result";
    }
}
