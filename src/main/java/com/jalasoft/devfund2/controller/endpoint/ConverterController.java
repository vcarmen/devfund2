package com.jalasoft.devfund2.controller.endpoint;

import com.jalasoft.devfund2.controller.component.Properties;
import com.jalasoft.devfund2.controller.response.Response;
import com.jalasoft.devfund2.controller.service.FileService;
import com.jalasoft.devfund2.model.Converter;
import com.jalasoft.devfund2.model.parameter.ConvertImageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/v1")
public class ConverterController {

    @Autowired
    private Properties properties;

    @Autowired
    private FileService fileService;

    @PostMapping("/converter")
    public ResponseEntity convert(@RequestParam(value="file") MultipartFile file,
                                  @RequestParam(value="md5") String md5,
                                  @RequestParam(value="convertTo") String convertTo) {
        try {
            File imageFile = fileService.store(file, md5);
            String outputDir = properties.getOutputFolder();
            Converter con = new Converter();

            ConvertImageParam param = new ConvertImageParam(imageFile, convertTo, outputDir);
            param.validate();
            String result = con.convert(param);

            return ResponseEntity.ok().body(
                    new Response(result,"", "200")
            );
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(new Response("",ex.getMessage(), "400"));
        }
    }
}
