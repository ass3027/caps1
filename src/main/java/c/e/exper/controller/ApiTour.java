package c.e.exper.controller;

import c.e.exper.data.BagDAO;
import c.e.exper.mapper.BagMapper;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiTour {

   @Autowired
   PlaceMapper placeMapper;

   @GetMapping("/getFirstImage")
   String getFirstImage(String contentid){
     return placeMapper.getFirstImage(contentid);
   }


}

