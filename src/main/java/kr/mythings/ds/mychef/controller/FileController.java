package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.common.SuccessResult;
import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class FileController {

    private static final String PATH_FILE = "file:";

    private final FileService fileService;

    //   이미지 출력
    @GetMapping("/images2/{fileId}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") Long id) throws IOException {

        FileEntity file = fileService.findOne(id);

        if (file != null) {
            return new UrlResource(PATH_FILE + file.getFileSaveName());
        } else {
            return null;
        }
    }

    @GetMapping("/images/{fileId}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadImage2(@PathVariable("fileId") Long id) throws IOException {

        FileEntity file = fileService.findOne(id);

        if (file != null) {
            UrlResource urlResource = new UrlResource(PATH_FILE + file.getFileSaveName());

            return ResponseEntity.ok()
                    .contentLength(file.getFileSize())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(new InputStreamResource(urlResource.getInputStream()));
        } else {
            return null;
        }
    }
    @GetMapping("/thumbnail/{fileId}")
    @ResponseBody
    public ResponseEntity<InputStreamResource>  imageThumb(@PathVariable("fileId") Long id) throws IOException {

        FileEntity file = fileService.findOne(id);

        if (file != null) {

            String pathName = file.getFileSaveName();
            String name = pathName.substring(pathName.lastIndexOf(File.separator)+1);

            String fileName = name.substring(0, name.indexOf("."));
            String ext = name.substring(name.indexOf("."));
            String path = pathName.substring(0, pathName.lastIndexOf(File.separator));

            String aa = String.format("%s%s%s%s%s",path,File.separator,fileName,"_s", ext);

            UrlResource urlResource = new UrlResource(PATH_FILE + aa);

            return ResponseEntity.ok()
                    .contentLength(urlResource.contentLength())
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .body(new InputStreamResource(urlResource.getInputStream()));
        } else {
            return null;
        }
    }



    //
    @GetMapping("/attach/download/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {

        FileEntity one = fileService.findOne(fileId);

        UrlResource resource = new UrlResource(PATH_FILE + one.getFileSaveName());

        String encode = UriUtils.encode(one.getFileName(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encode + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }

    @GetMapping(value = "/attach/delete/{fileId}" , produces = "application/json; charset=UTF-8")
    @ResponseBody
    public SuccessResult deleteAttach(@PathVariable Long fileId) {

        SuccessResult result;

        if (fileId != null) {
            result = fileService.delete(fileId);
        } else {
            String msg = "파일번호가 없습니다.";
            result = new SuccessResult(false, msg);
        }

        return result;
    }

}
