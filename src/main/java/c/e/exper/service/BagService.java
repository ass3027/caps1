package c.e.exper.service;

import c.e.exper.data.BagDAO;
import c.e.exper.mapper.BagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BagService {
   @Autowired
   public BagMapper bagMapper;
   //일반회원 물품 상세보기
   public BagDAO gOrderDetail(@PathVariable("ord_id") String ord_id) {
      return bagMapper.gOrderDetail(ord_id);
   }
   
   //키퍼회원 물품 상세보기
   public BagDAO trackingDetail(@PathVariable("ord_id") String ord_id) {
      return bagMapper.trackingDetail(ord_id);
   }
   
   //물품 보관 등록
   public void storageInsert(BagDAO storageBag) {
      bagMapper.storageInsert(storageBag);
   }
   
   //물품 배송 등록
   public void transportInsert(BagDAO transportBag) {
      bagMapper.transportInsert(transportBag);
   }
   
   //물품조회(보관)
   public List<BagDAO> searchStorage(String user_id) {
      return bagMapper.searchStorage(user_id);
   }
   
   //물품조회(배송)
   public List<BagDAO> searchTransport(String user_id) {
      return bagMapper.searchTransport(user_id);
   }
   
   //출발키퍼 운송 승인완료로 변경
   public void deliveryStartCall(String ord_id) {
      bagMapper.deliveryStartCall(ord_id);
      bagMapper.deliveryAllCall(ord_id);
   }
   
   //도착키퍼 운송 승인완료로 변경
   public void deliveryEndCall(String ord_id) {
      bagMapper.deliveryEndCall(ord_id);
      bagMapper.deliveryAllCall(ord_id);
   }
   
   //물품 보관중으로 변경
   public void keeperInStorage(String ord_id) {
      bagMapper.keeperInStorage(ord_id);
   }
   
   //찾아감으로 변경
   public void visitCall(String ord_id) {
      bagMapper.visitCall(ord_id);
   }
   
   //키퍼회원 배송조회
   public List<BagDAO> keeperTransport(String user_id) {
      return bagMapper.keeperTransport(user_id);
   }
   
   //키퍼회원 보관조회
   public List<BagDAO> keeperStorage(String user_Id) {
      return bagMapper.keeperStorage(user_Id);
   }
   
   //장소ID
   public List<String> plId(String user_id) {
      List<String> plid = bagMapper.plId(user_id);
      return plid;
   }
   
   
   
}
