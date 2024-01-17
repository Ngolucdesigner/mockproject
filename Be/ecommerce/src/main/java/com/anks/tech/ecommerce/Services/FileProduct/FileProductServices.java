package com.anks.tech.ecommerce.Services.FileProduct;

import com.anks.tech.ecommerce.Entity.FileProduct;
import com.anks.tech.ecommerce.Respository.IFileProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileProductServices implements IFileProductServices {

    @Autowired
    private IFileProductRespository fileProductRespository;
    @Override
    public FileProduct store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileProduct fileDB = new FileProduct();

        fileDB.setFileName(fileName);
        fileDB.setFileType(file.getContentType());
        fileDB.setData(file.getBytes());


        return fileProductRespository.save(fileDB);
    }

    @Override
    public FileProduct getFile(String id) {
        return fileProductRespository.getById(id);
    }

    @Override
    public Stream<FileProduct> getAllFiles() {
        return fileProductRespository.findAll().stream();
    }
}
