package com.example.project1.Controllers;

import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.Domain.ImageModel;
import com.example.project1.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.zip.DataFormatException;

//@CrossOrigin
//@RestController
//@RequestMapping("/api/image")
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<MessageResponse> UploadUserImage(@RequestParam("file")MultipartFile file, Principal principal) throws IOException{
//        imageService.uploadImageToUser(file,principal);
//        return  ResponseEntity.ok(new MessageResponse("Image Upload Successfully"));
//    }
//
//    @PostMapping("{foodID}/upload")
//    public ResponseEntity<MessageResponse> UploadFoodImage(@PathVariable("foodID") String foodID, @RequestParam("file")MultipartFile file, Principal principal) throws IOException{
//        imageService.uploadImageToFood(file,principal,Long.parseLong(foodID));
//        return  ResponseEntity.ok(new MessageResponse("Image Upload Successfully"));
//    }
//
//    @PostMapping("{orgID}/upload")
//    public ResponseEntity<MessageResponse> UploadOrgImage(@PathVariable("orgID") String orgID, @RequestParam("file")MultipartFile file, Principal principal) throws IOException{
//        imageService.uploadImageToOrg(file,principal,Long.parseLong(orgID));
//        return  ResponseEntity.ok(new MessageResponse("Image Upload Successfully"));
//    }
//
//    @PostMapping("profile")
//    public ResponseEntity<ImageModel> getProfileImage(Principal principal) throws IOException, DataFormatException {
//        ImageModel imageModel =imageService.getImageToUser(principal);
//        return new ResponseEntity<>(imageModel,HttpStatus.OK);
//    }
//
//    @PostMapping("{foodID}/image")
//    public ResponseEntity<ImageModel> getFoodImage(@PathVariable("foodID") String foodID) throws IOException, DataFormatException {
//        ImageModel imageModel =imageService.getImageToFood(Long.parseLong(foodID));
//        return new ResponseEntity<>(imageModel,HttpStatus.OK);
//    }
//
//    @PostMapping("{orgID}/image")
//    public ResponseEntity<ImageModel> getOrgImage(@PathVariable("orgID") String orgID) throws IOException, DataFormatException {
//        ImageModel imageModel =imageService.getImageToOrg(Long.parseLong(orgID));
//        return new ResponseEntity<>(imageModel,HttpStatus.OK);
//    }
//
//}
