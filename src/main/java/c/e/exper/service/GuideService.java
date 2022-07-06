package c.e.exper.service;


import c.e.exper.data.GItemDTO;
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

    public List<GuideDAO> 가이드_조회() {
        return guideMapper.selectAll();
    }

    public List<GuideDAO> 가이드_검색(String keyword) {
        return guideMapper.selectBykeyword(keyword);
    }

    public List<GItemDTO> 가이드상품_검색(String keyword) {
        return gitemMapper.selectItemBykeyword(keyword);
    }

    public GuideDAO 가이드_조회1(String id) {
        return guideMapper.selectById(id);
    }

    public GItemDTO 매출_조회(String id) {
        return gitemMapper.selectCount(id);
    }

    public GItemDTO 매출_조회7(String id) {
        return gitemMapper.selectCount7(id);
    }

    public GItemDTO 매출_조회1(String id) {
        return gitemMapper.selectCount1(id);
    }

    public GItemDTO 매출_조회30(String id) {
        return gitemMapper.selectCount30(id);
    }

    public GItemDTO 가이드_상품조회1(String id) {
        return gitemMapper.selectByGitemId(id);
    }

    public void 가이드_등록(GuideDAO guideDAO) {

        guideMapper.insert(guideDAO);
    }

    public void 가이드_상품등록(GItemDTO gitemDTO) {

        System.out.println(gitemDTO);
        gitemMapper.insert(gitemDTO);
    }

    public void 가이드_시간등록(GItemDTO gitemDTO) {
        System.out.println(gitemDTO);
        gitemMapper.insertTime(gitemDTO);
    }

    public List<GItemDTO> 가이드상품_조회() {

        return gitemMapper.selectAll();

    }

    public List<GItemDTO> 가이드상품_시간조회(String id, String time) {
        return gitemMapper.selectTime(id, time);
    }

    public void 가이드상품_삭제1(String id) {
        gitemMapper.deleteGitemOne(id);

    }

    public int 시간_수정(int id) {
        return gitemMapper.updateTime(id);
    }

    public void 소개_수정(GuideDAO guideDAO, String id) {
        guideMapper.updateIntro(guideDAO, id);
    }

    public void 예약_등록(GItemDTO gitemDTO) {
        gitemMapper.insertReserve(gitemDTO);
    }

    public void 결제(GItemDTO gItemDTO) {
        gitemMapper.insertPay(gItemDTO);
    }

    public List<GItemDTO> 시간_조회(String id) {
        return gitemMapper.selectTimes(id);
    }

}
