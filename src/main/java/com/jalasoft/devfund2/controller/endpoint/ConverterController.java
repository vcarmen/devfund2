package com.jalasoft.devfund2.controller.endpoint;

import com.jalasoft.devfund2.model.Converter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/v1")
public class ConverterController {

    @PostMapping("/converter")
    public String convert(@RequestParam(value="file") MultipartFile file,
                           @RequestParam(value="convertTo") String convertTo) {
        String fileInput;
        String outputDir;
        try {
            String inputFolder = "inputFiles/";
            Files.createDirectories(Paths.get(inputFolder));
            fileInput = inputFolder + file.getOriginalFilename();
            Path path = Paths.get(fileInput);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            outputDir = "outputFiles/";
            Files.createDirectories(Paths.get(outputDir));
        }
        catch (IOException ex) {
            return ex.getMessage();
        }

        Converter con = new Converter();
        String result = con.convert(fileInput, convertTo, outputDir);

        return result;
    }
}
