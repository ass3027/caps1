package controller;

import c.e.exper.data.BagDAO;

import c.e.exper.data.BagDTO;
import c.e.exper.mapper.BagMapper;
import c.e.exper.service.BagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiBag {
   final BagMapper bagMapper;
   
   public ApiBag(BagMapper bagMapper) {
      this.bagMapper = bagMapper;
   }
   
   @PostMapping("/storageAddOrder")
   public String storageAddOrder(@RequestBody BagDAO storageBag) {
      System.out.println(storageBag);
      bagMapper.storageInsert(storageBag);
      return "";
   }
   
   @PostMapping("/transportAddOrder")
   public String transportAddOrder(@RequestBody BagDAO transportBag) {
      System.out.println(transportBag);
      bagMapper.transportInsert(transportBag);
      return "";
   }
   
   //물품조회(보관)
   @GetMapping("/bagStorage/{user_id}")
   public List<BagDAO> SearchStorage(@PathVariable String user_id) {
      System.out.println("보관데이터:" + bagMapper.selectStorage(user_id));
      return bagMapper.selectStorage(user_id);
   }
   
   //물품조회(운송)
   @GetMapping("/bagTransport/{user_id}")
   public List<BagDAO> SearchTransport(@PathVariable String user_id) {
      System.out.println("운송데이터:" + bagMapper.selectTransport(user_id));
      return bagMapper.selectTransport(user_id);
   }
}

