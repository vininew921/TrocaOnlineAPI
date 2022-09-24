package com.facens.troca.online.api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


@Controller
public class UploadImage {
@GetMapping("/index")
public String index(){
	return "index";
}

@PostMapping("/upload") 
public String uploadFile(@RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException
{
String basedir = "C:\\Users\\Joel\\Desktop\\TrocaOnlineAPI\\src\\main\\java\\com\\facens\\troca\\online\\api\\uploads\\";


file.transferTo(new File(basedir + "\\Myfile.jpg"));
return "";
}

}
