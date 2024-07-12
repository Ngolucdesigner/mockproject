package com.anks.tech.ecommerce.Controller;



import com.anks.tech.ecommerce.DTO.CustomerDTO.FileProductDTO;
import com.anks.tech.ecommerce.Entity.FileProduct;
import com.anks.tech.ecommerce.Services.FileProduct.IFileProductServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseBody

@RequestMapping(value = "products/files", produces = {"application/json"})

public class FileProductController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IFileProductServices fileProductServices;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileProductServices.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }


    @GetMapping()
    public ResponseEntity<List<FileProductDTO>> getListFiles() {
        List<FileProductDTO> files = fileProductServices.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("products/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new FileProductDTO(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getFileType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getFile(@PathVariable String id) {
        FileProduct fileDB = fileProductServices.getFile(id);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
                .body(new ByteArrayResource(fileDB.getData()));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable String id){

        return ResponseEntity.ok().body("Delete successfully!");
    }

}
