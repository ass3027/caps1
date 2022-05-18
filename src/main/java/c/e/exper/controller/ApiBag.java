package c.e.exper.controller;

import c.e.exper.data.BagDAO;
import c.e.exper.data.BagDTO;
import c.e.exper.mapper.BagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiBag {
   
   final BagMapper bagMapper;
   
   
   @Autowired
   public ApiBag(BagMapper bagMapper) {
      this.bagMapper = bagMapper;
   }
   
   @PostMapping("/stAddOrder")
   public String storageAddOrder(@RequestBody BagDAO storageBag) {
      System.out.println(storageBag);
      bagMapper.storageInsert(storageBag);
      return "";
   }
   
   @PostMapping("/trAddOrder")
   public String transportAddOrder(@RequestBody BagDAO transportBag) {
      System.out.println(transportBag);
      bagMapper.transportInsert(transportBag);
      return "";
   }
   
   @GetMapping("/bag/{user_id}")
   public List<BagDAO> SearchOrder(@PathVariable String user_id) {
      
      return bagMapper.selectByUserId(user_id);
   }
   
}

