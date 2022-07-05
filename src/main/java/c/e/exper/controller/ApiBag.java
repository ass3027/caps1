package c.e.exper.controller;

import c.e.exper.data.BagDAO;

import c.e.exper.mapper.BagMapper;
import c.e.exper.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiBag {
   @Autowired
   final BagMapper bagMapper;
   
   @Autowired
   final BagService bagService;
   
   public ApiBag(BagService bagService, BagMapper bagMapper) {
      this.bagService = bagService;
      this.bagMapper = bagMapper;
   }
   
   private String getUserId() {
      return SecurityContextHolder.getContext().getAuthentication().getName();
   }
   
   //일반회원 물품보관 등록
   @PostMapping("/storageAddOrder")
   public void storageAddOrder(@RequestBody BagDAO storageBag) {
      System.out.println(storageBag);
      bagService.storageInsert(storageBag);
   }
   
   //일반회원 물품배송 등록
   @PostMapping("/transportAddOrder")
   public void transportAddOrder(@RequestBody BagDAO transportBag) {
      System.out.println(transportBag);
      bagService.transportInsert(transportBag);
   }
   
   //일반회원 물품조회(보관)
   @GetMapping("/bagStorage")
   public List<BagDAO> SearchStorage() {
      System.out.println("보관데이터:" + bagMapper.selectStorage(getUserId()));
      return bagService.selectStorage(getUserId());
   }
   
   //일반회원 물품조회(운송)
   @GetMapping("/bagTransport")
   public List<BagDAO> SearchTransport() {
      System.out.println("운송데이터:" + bagMapper.selectTransport(getUserId()));
      return bagService.selectTransport(getUserId());
   }
   
   //물품 상세보기
   @GetMapping("/trackingDetail/{ord_id}")
   public BagDAO trackingDetail(@PathVariable String ord_id) {
      System.out.println();
      return bagService.trackingDetail(ord_id);
   }
   
   //보관중으로 변경
   @PostMapping("/storageCall/{ord_id}")
   public void storageCall(@PathVariable("ord_id") String ord_id) {
      bagService.storageCall(ord_id);
   }
   
   //일반회원 찾아감으로 변경
   @PostMapping("/visitCall/{ord_id}")
   public void visitCall(@PathVariable("ord_id") String ord_id) {
      bagService.visitCall(ord_id);
   }
   
   //키퍼회원 배송조회
   @GetMapping("/keeperTracking")
   public List<BagDAO> keeperTracking() {
      return bagService.keeperTracking(getUserId());
   }
   
   //출발키퍼회원 운송원 요청
   @PostMapping("/deliveryStartCall/{ord_id}")
   public void deliveryStartCall(@PathVariable("ord_id") String ord_id) {
      bagService.deliveryStartCall(ord_id);
   }
   
   //도착키퍼회원 운송원 요청
   @PostMapping("/deliveryEndCall/{ord_id}")
   public void deliveryEndCall(@PathVariable("ord_id") String ord_id) {
      bagService.deliveryEndCall(ord_id);
   }
   
   //키퍼회원 장소 ID 조회
   @GetMapping("/plId")
   public String plId() {
      return bagService.plId(getUserId());
   }
}

