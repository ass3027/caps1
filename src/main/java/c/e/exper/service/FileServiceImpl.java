package c.e.exper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class FileServiceImpl implements FileService {




    @Override
    public String photoSave(MultipartFile file, HttpServletRequest req,String folder){
        String fileName = folder+"\\"+ System.currentTimeMillis() + file.getOriginalFilename();
        String safeFile = req.getSession().getServletContext().getRealPath("/") + fileName;
        System.out.println(safeFile);
        try {
            file.transferTo(new File(safeFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }



}
