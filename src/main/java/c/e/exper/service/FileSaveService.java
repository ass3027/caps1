package c.e.exper.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileSaveService {

    String photoSave(MultipartFile file, HttpServletRequest req);
}
