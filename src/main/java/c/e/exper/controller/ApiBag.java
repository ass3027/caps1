package c.e.exper.controller;

import c.e.exper.data.BagDAO;
import c.e.exper.mapper.BagMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ApiBag {
   
   final
   BagMapper bagMapper;
   
   public ApiBag(BagMapper bagMapper) {
      this.bagMapper = bagMapper;
   }
   
   @PostMapping("addOrder")
   public String addOrder(@RequestBody BagDAO bag){
      System.out.println(bag);
      bagMapper.insert(bag);
      return "";
   }


}

