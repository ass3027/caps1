package c.e.exper.service;


import c.e.exper.data.Available_TimeDAO;
import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import c.e.exper.mapper.GItemMapper;
import c.e.exper.mapper.GuideMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {
   
   final
   GuideMapper guideMapper;
   GItemMapper gitemMapper;
   
   
   public GuideService(GuideMapper guideMapper, GItemMapper gitemMapper) {
      
      this.guideMapper = guideMapper;
      this.gitemMapper = gitemMapper;
   }

   public List<GItemDAO> 매출_조회_리스트(String id){
      return gitemMapper.selectCountList(id);
   }


   public List<GuideDAO> 가이드_조회() {
      return guideMapper.selectAll();
   }
   
   public List<GuideDAO> 가이드_검색(String keyword) {
      return guideMapper.selectBykeyword(keyword);
   }

   public List<GItemDAO> 가이드상품_검색(String keyword){
      return gitemMapper.selectItemBykeyword(keyword);
   }
   
   public GuideDAO 가이드_조회1(String id) {
      return guideMapper.selectById(id);
   }

   public GItemDAO 매출_조회(String id){
      return gitemMapper.selectCount(id);
   }
   public GItemDAO 매출_조회7(String id){
      return gitemMapper.selectCount7(id);
   }

   public GItemDAO 매출_조회1(String id){
      return gitemMapper.selectCount1(id);
   }

   public GItemDAO 매출_조회30(String id){
      return gitemMapper.selectCount30(id);
   }

   public GItemDAO 가이드_상품조회1(String id){
      return gitemMapper.selectByGitemId(id);
   }
   
   public void 가이드_등록(GuideDAO guideDAO) {
      
      guideMapper.insert(guideDAO);
   }
   
   public void 가이드_상품등록(GItemDAO gitemDAO) {
      
      System.out.println(gitemDAO);
      gitemMapper.insert(gitemDAO);
   }
   public void 가이드_시간등록(GItemDAO gitemDAO){
      System.out.println(gitemDAO);
      gitemMapper.insertTime(gitemDAO);
   }
   
   public List<GItemDAO> 가이드상품_조회() {
      
      return gitemMapper.selectAll();
      
   }
   public List<GItemDAO> 가이드상품_시간조회(String id, String time){
      return gitemMapper.selectTime(id, time);
   }

   public void 가이드상품_삭제1(String id){
      gitemMapper.deleteGitemOne(id);

   }

   public void 예약_취소(String id){
      gitemMapper.deletePayment(id);
   }

   public int 시간_수정(int id){
      return gitemMapper.updateTime(id);
   }
   public void 소개_수정(GuideDAO guideDAO, String id){
       guideMapper.updateIntro(guideDAO, id);
   }

   public int 결제_완료(int id){
      return gitemMapper.updategTime(id);
   }

   public void 예약_등록(GItemDAO gitemDAO){
      gitemMapper.insertReserve(gitemDAO);
   }
   public void 결제(GItemDAO gitemDAO){
      gitemMapper.insertPay(gitemDAO);
   }

   public List<GItemDAO> 시간_조회(String id){
      return gitemMapper.selectTimes(id);
   }
   public List<GItemDAO> 시간_조회2(String id){
      return gitemMapper.selectTimes2(id);
   }

}
