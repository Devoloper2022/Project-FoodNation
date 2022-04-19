package com.example.project1.Services;

import com.example.project1.Domain.Food;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.ImageModel;
import com.example.project1.Domain.User;
import com.example.project1.Repository.GeneralOrganizationRepository;
import com.example.project1.Repository.ImageModelRepository;
import com.example.project1.Repository.UserRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

//@Service
//public class ImageService {
//    public static final Logger LOG = LoggerFactory.getLogger(ImageService.class);
//
//    private GeneralOrganizationRepository genOrgRepository;
//    private UserRepository userRepository;
//    private ImageModelRepository imageModelRepository;
//
//    @Autowired
//    public ImageService(GeneralOrganizationRepository genOrgRepository, UserRepository userRepository, ImageModelRepository imageModelRepository) {
//        this.genOrgRepository = genOrgRepository;
//        this.userRepository = userRepository;
//        this.imageModelRepository = imageModelRepository;
//    }
//
//
//    public ImageModel uploadImageToOrg(MultipartFile file, Principal principal, Long id) throws IOException {
//        User user = getUserByPrincipal(principal);
//        Long orgId = user.getLocalOrganization().getGeneralOrganization().getId();
//        ImageModel checkImageModel = imageModelRepository.findImageByOrgId(orgId).orElse(null);
//
//        if (!ObjectUtils.isEmpty(checkImageModel)) {
//            imageModelRepository.delete(checkImageModel);
//        }
//
//        ImageModel imageModel = new ImageModel();
//        imageModel.setFile(compressByte(file.getBytes()));
//        imageModel.setOrgId(orgId);
//        LOG.info("Uploading image orgID: " + id);
//        return imageModelRepository.save(imageModel);
//
//    }
//
//    public ImageModel uploadImageToFood(MultipartFile file, Principal principal, Long id) throws IOException {
//        User user = getUserByPrincipal(principal);
//        Long orgId = user.getLocalOrganization().getGeneralOrganization().getId();
//        GeneralOrganization genOrg=genOrgRepository.findById(orgId).get();
//        Food food= (Food) genOrg.getFoodList()
//                .stream()
//                .filter( p -> p.getId().equals(id))
//                .collect(toSingleFoodCollector());
//
//        ImageModel imageModel = new ImageModel();
//        imageModel.setFile(compressByte(file.getBytes()));
//        imageModel.setFoodId(food.getId());
//        LOG.info("Uploading image foodID: " + id);
//        return imageModelRepository.save(imageModel);
//
//    }
//
//    public ImageModel uploadImageToUser(MultipartFile file, Principal principal) throws IOException {
//        User user = getUserByPrincipal(principal);
//        LOG.info("Uploading image " + user.getUsername());
//
//        ImageModel checkImageModel = imageModelRepository.findImageByUserId(user.getId()).orElse(null);
//
//        if (!ObjectUtils.isEmpty(checkImageModel)) {
//            imageModelRepository.delete(checkImageModel);
//        }
//
//        ImageModel imageModel = new ImageModel();
//        imageModel.setFile(compressByte(file.getBytes()));
//        imageModel.setUserId(user.getId());
//        return imageModelRepository.save(imageModel);
//    }
//
//    public ImageModel getImageToUser(Principal principal) {
//        User user =getUserByPrincipal(principal);
//        ImageModel imageModel = imageModelRepository.findImageByUserId(user.getId()).get();
//
//        if (!ObjectUtils.isEmpty(imageModel)){
//            imageModel.setFile(decompressByte(imageModel.getFile()));
//        }
//
//        return imageModel;
//    }
//
//    public ImageModel getImageToFood(Long id)  {
//        ImageModel imageModel = imageModelRepository.findImageByFoodId(id).get();
//
//        if (!ObjectUtils.isEmpty(imageModel)){
//            imageModel.setFile(decompressByte(imageModel.getFile()));
//        }
//
//        return imageModel;
//    }
//
//    public ImageModel getImageToOrg(Long id)  {
//        ImageModel imageModel = imageModelRepository.findImageByOrgId(id).get();
//
//        if (!ObjectUtils.isEmpty(imageModel)){
//            imageModel.setFile(decompressByte(imageModel.getFile()));
//        }
//
//        return imageModel;
//    }
//
//    private byte[] compressByte(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//        byte[] buffer = new byte[1024];
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException ex) {
//            LOG.error("Cannot compress Bytes");
//        }
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//        return outputStream.toByteArray();
//    }
//
//    @SneakyThrows
//    private byte[] decompressByte(byte[] data)  {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        byte[] buffer = new byte[1024];
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//
//        while (!inflater.finished()) {
//            int count = inflater.inflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException ex) {
//            LOG.error("Cannot decompress Bytes");
//        }
//        return outputStream.toByteArray();
//    }
//
//    private <T> Collector<Object, Object, Object> toSingleFoodCollector() {
//        return Collectors.collectingAndThen(
//                Collectors.toList(),
//                list -> {
//                    if (list.size() != 1) {
//                        throw new IllegalStateException();
//                    }
//                    return list.get(0);
//                }
//        );
//    }
//
//    private User getUserByPrincipal(Principal principal) {
//        String username = principal.getName();
//        return userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new
//                        UsernameNotFoundException("User not found with username: " + username));
//    }
//
//}
