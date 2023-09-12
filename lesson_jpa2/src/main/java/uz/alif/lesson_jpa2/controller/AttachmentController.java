package uz.alif.lesson_jpa2.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.alif.lesson_jpa2.entity.Attachment;
import uz.alif.lesson_jpa2.entity.AttachmentContent;
import uz.alif.lesson_jpa2.repository.AttachmentContentRepository;
import uz.alif.lesson_jpa2.repository.AttachmentRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    private static final String uploadDirectory = "saqlanganlar";

//    @PostMapping
//    public String add(MultipartHttpServletRequest request) throws IOException {
//    System.out.println(System.currentTimeMillis());
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//
//    if(file != null) {
//        String name  = file.getOriginalFilename();
//        String  contentType = file.getContentType();
//        long size = file.getSize();
//
//        Attachment attachment = new Attachment();
//        attachment.setOriginalFileName(name);
//        attachment.setContentType(contentType);
//        attachment.setSize(size);
//        Attachment save= attachmentRepository.save(attachment);
//
//        AttachmentContent attachmentContent = new AttachmentContent();
//        attachmentContent.setAttachment(save);
//        attachmentContent.setContent(file.getBytes());
//        attachmentContentRepository.save(attachmentContent);
//        System.out.println(System.currentTimeMillis());
//        return save.getOriginalFileName()+" file saved";
//    }
//    return "xatolik";
//    }


//    @GetMapping("/downloaddb/{id}")
//    public void dowloaddb(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()){
//            Attachment attachment = optionalAttachment.get();
//            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
//            if (optionalAttachmentContent.isPresent()){
//                AttachmentContent attachmentContent = optionalAttachmentContent.get();
//                response.setHeader("Content-Disposition", "attachment: filename=\""+attachment.getOriginalFileName()+"\"");
//                response.setContentType(attachment.getContentType());
//                FileCopyUtils.copy(attachmentContent.getContent(), response.getOutputStream());
//            }
//
//        }
//    }

    @PostMapping
    public String saveToSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        String name  = file.getOriginalFilename();
        String  contentType = file.getContentType();
        long size = file.getSize();
        String[] split = name.split("\\.");
        String s = UUID.randomUUID().toString();
        s = s+"."+split[(split.length-1)];
        Attachment attachment = new Attachment();
        attachment.setOriginalFileName(name);
        attachment.setContentType(contentType);
        attachment.setSize(size);
        attachment.setName(s);
        Attachment save= attachmentRepository.save(attachment);

        Path path = Paths.get(uploadDirectory+"/"+s);
        Files.copy(file.getInputStream(),path);
        return "file saqlandi id si "+ save.getId();
    }

    @GetMapping("/downloadfileSystem/{id}")
    public void DownloadFileSystem(@PathVariable int id,HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment: filename=\""+attachment.getOriginalFileName()+"\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+attachment.getName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

}
