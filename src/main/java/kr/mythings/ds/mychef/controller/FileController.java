package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.common.SuccessResult;
import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    //   이미지 출력
    @GetMapping("/images/{fileId}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException {

        FileEntity file = fileService.findOne(id);

        if (file != null) {
            return new UrlResource("file:" + file.getFileSaveName());
        } else {
            return null;
        }
    }

    //
    @GetMapping("/attach/download/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {

        FileEntity one = fileService.findOne(fileId);

        UrlResource resource = new UrlResource("file:" + one.getFileSaveName());

        String encode = UriUtils.encode(one.getFileName(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encode + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);

    }

    @GetMapping(value = "/attach/delete/{fileId}" , produces = "application/json; charset=UTF-8")
    @ResponseBody
    public SuccessResult deleteAttach(@PathVariable Long fileId) {

        SuccessResult result = null;

        if (fileId != null) {
            result = fileService.delete(fileId);
        } else {
            String msg = "파일번호가 없습니다.";
            result = new SuccessResult(false, msg);
        }

        return result;
    }

}
