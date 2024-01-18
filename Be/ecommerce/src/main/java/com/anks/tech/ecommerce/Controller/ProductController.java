package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.DTO.FileProductDTO;
import com.anks.tech.ecommerce.DTO.ProductDTO;

import com.anks.tech.ecommerce.Entity.Product;
import com.anks.tech.ecommerce.Form.CreateProductForm;
import com.anks.tech.ecommerce.Services.Product.IProductServices;
import com.anks.tech.ecommerce.Utils.FileDownloadUtil;
import com.anks.tech.ecommerce.Utils.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/products", produces = {"application/json"})
@ResponseBody
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:3000"})

public class ProductController {

    @Autowired
    private IProductServices productServices;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/hello")
    String hello() {
        return "Hello product!";
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {

        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Page<Product> productPage = productServices.getAllProduct(pageable);
        List<Product> products = productPage.getContent();

        products.forEach(product -> {
            Resource resource = new ClassPathResource(product.getImgUrl());
            System.out.println(resource);
        });


        List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());


        productDTOS.forEach(file -> {
            String fileDowloadUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/products/files/")
                    .path(file.getFile().getId()).toUriString();


            ProductDTO.File file1 = new ProductDTO.File();
            file1.setUrl(fileDowloadUrl);
            file1.setId(file.getFile().getId());
            file1.setFileName(file.getFile().getFileName());
            file1.setFileType(file.getFile().getFileType());
            file.setFile(file1);

        });
        return ResponseEntity.ok().body(new PageImpl<>(productDTOS, pageable, productPage.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id) {
        Product product = productServices.getProductById(id);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        String fileDowloadUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/products/files/")
                .path(productDTO.getFile().getId()).toUriString();

        ProductDTO.File file1 = new ProductDTO.File();
        file1.setUrl(fileDowloadUrl);
        file1.setId(productDTO.getFile().getId());
        file1.setFileName(productDTO.getFile().getFileName());
        file1.setFileType(productDTO.getFile().getFileType());
        productDTO.setFile(file1);

        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping("/newProduct")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductForm form) {

        productServices.createProduct(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    @PostMapping("/new-products")
    public ResponseEntity<String> createNewProduct(@RequestParam("file") MultipartFile multipartFiles,
                                                   @RequestParam String productName,
                                                   @RequestParam double price,
                                                   @RequestParam double sales,
                                                   @RequestParam String shortDesc,
                                                   @RequestParam String description,
                                                   @RequestParam String category,
                                                   @RequestParam String manufacturer,
                                                   @RequestParam String madeIn,
                                                   @RequestParam String guarantee
    ) throws IOException {
        CreateProductForm form = new CreateProductForm();

        form.setProductName(productName);
        form.setDescription(description);
        form.setShortDesc(shortDesc);
        form.setPrice(Float.parseFloat(String.valueOf(price)));
        form.setPriceSales(sales);
        CreateProductForm.Category category1 = new CreateProductForm.Category(category);
        form.setCategory(category1);

        form.setImgUrl("src/main/resources/static/img/");
        String fileName = StringUtils.cleanPath(multipartFiles.getOriginalFilename());


        CreateProductForm.FileProduct fileProduct = new CreateProductForm.FileProduct();
        fileProduct.setFileName(fileName);
        fileProduct.setFileType(multipartFiles.getContentType());
        fileProduct.setData(multipartFiles.getBytes());
        form.setFileProduct(fileProduct);

        CreateProductForm.Origin origin = new CreateProductForm.Origin();
        origin.setGuarantee(guarantee);
        origin.setMadeIn(madeIn);
        origin.setManufacturer(manufacturer);
        form.setOrigin(origin);

        productServices.createProduct(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productServices.deleteProduct(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createProductForm(@RequestParam("file") MultipartFile multipartFiles,
                                                    @RequestParam String productName,
                                                    @RequestParam double price,
                                                    @RequestParam String shortDesc,
                                                    @RequestParam String description,
                                                    @RequestParam String category


    ) throws IOException {
        CreateProductForm form = new CreateProductForm();
        String fileName = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
        long size = multipartFiles.getSize();

        String uploadDir = "src/main/resources/static/img/" + productName.replace("\"", "");

        String imgUrl = uploadDir + "/" + fileName;
        form.setProductName(productName);
        form.setDescription(description);
        form.setShortDesc(shortDesc);
        form.setPrice(Float.parseFloat(String.valueOf(price)));

        CreateProductForm.Category category1 = new CreateProductForm.Category(category);
        form.setCategory(category1);
//        form.setImgUrl(Base64.getEncoder().encodeToString(multipartFiles.getBytes()));
        form.setImgUrl(imgUrl);
        FileUploadUtils.saveFile(uploadDir, fileName, multipartFiles);


//        productServices.createProduct(form);

        System.out.println(form.getProductName() + " " + form.getShortDesc() + " " + form.getImgUrl());
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }



    @PostMapping("/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile multipartFiles) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFiles.getOriginalFilename());
        long size = multipartFiles.getSize();

        String uploadDir = "src/main/resources/static/img";
        FileUploadUtils.saveFile(uploadDir, fileName, multipartFiles);

        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();
        String uploadDir = "src/main/resources/static/img";
        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(uploadDir, fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
