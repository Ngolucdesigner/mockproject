package com.anks.tech.ecommerce.Controller;

import com.anks.tech.ecommerce.Entity.Review;
import com.anks.tech.ecommerce.Form.ProductForm.ReviewForm;
import com.anks.tech.ecommerce.Services.Review.IReviewServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reviews", produces = "application/json")
@ResponseBody

public class ReviewController {

    @Autowired
    private IReviewServices reviewServices;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<Page<Review>> getAllReview (Pageable pageable){
        Page<Review> reviewPage = reviewServices.getAllReview(pageable);
        List<Review> reviews = reviewPage.getContent();
        return  ResponseEntity.ok().body(new PageImpl<>(reviews, pageable,reviewPage.getTotalElements()));
    }

    @PostMapping("/new-review")
    public  ResponseEntity<String> createNewReview(@RequestBody  ReviewForm form){

//      System.out.println(form.getReviewText() +" " + form.getUsername());
        reviewServices.createReview(form);
        return ResponseEntity.ok().body("create successfully!");
    }
}
