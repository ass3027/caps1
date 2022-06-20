package c.e.exper.controller;

import c.e.exper.mapper.PictureMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/photo")
public class ApiPhoto {

    final PictureMapper pictureMapper;

    public ApiPhoto(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }


    @GetMapping("/{folderName}/{fileName}")
    public ResponseEntity<byte[]> photo(
            @PathVariable String folderName,
            @PathVariable String fileName, HttpServletRequest req) throws Exception {

        System.out.println("GET 이미지(folderName: " + folderName + " fileName: " + fileName + ")");

        InputStream in = null;
        ResponseEntity<byte[]> entity;

        String path = req.getSession().getServletContext().getRealPath("/") + folderName + "/" + fileName;
//        String path2 = req.getSession().getServletContext().getRealPath("/")+"\\"+folderName+"\\"+fileName;

        try {
            in = new FileInputStream(path);

            String formatName = path.substring(path.lastIndexOf(".") + 1);
            MediaType mediaType;
            HttpHeaders headers = new HttpHeaders();

            mediaType = switch (formatName) {
                case "jpg" -> MediaType.IMAGE_JPEG;
                case "gif" -> MediaType.IMAGE_GIF;
                case "png" -> MediaType.IMAGE_PNG;
                default -> null;
            };

            if (mediaType != null) {
                headers.setContentType(mediaType);
            } else {
                String fileName2;
                fileName2 = path.substring(path.indexOf("\"") + 1);//original file Name
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName2.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
            }

            entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            if (in != null) in.close();

        }


        return entity;
    }

    @DeleteMapping("/{folderName}/{fileName}")
    public ResponseEntity<Boolean> delPhoto(
            @PathVariable String folderName,
            @PathVariable String fileName,
            HttpServletRequest req) {

        try{
            String safeFile = req.getSession().getServletContext().getRealPath("/") +folderName+"/" +fileName;
            System.out.println(safeFile);

            File file = new File(safeFile);
            boolean result = file.delete();
            System.out.println(result);
            if(result){
                System.out.println(folderName+"/" +fileName);
                pictureMapper.deletePicture(folderName+"/" +fileName);
            }

            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
