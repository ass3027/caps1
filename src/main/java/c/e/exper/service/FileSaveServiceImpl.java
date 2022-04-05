package c.e.exper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class FileSaveServiceImpl implements FileSaveService {


    @Override
    public String photoSave(MultipartFile file, HttpServletRequest req){
        String fileName = file.getOriginalFilename();
        String safeFile = req.getSession().getServletContext().getRealPath("/userImage/") + System.currentTimeMillis() + fileName;
        System.out.println(safeFile);
        try {
            file.transferTo(new File(safeFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return safeFile;
    }

    @Override
    public String photoLoad(String path, HttpServletRequest req) {

        return null;
    }


}
