package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.GuideMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import c.e.exper.data.GuideDAO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiGuide {

    @Autowired
    GuideService guideService;

    @Autowired
    GuideMapper guideMapper;


    @GetMapping("/guide")
    public List<GuideDAO> findAllGuide() {
        System.out.println("가이드조회");
        return guideService.가이드_조회(); 

    }

    @GetMapping("/guide/{id}")
    public GuideDAO findAllGuide(@PathVariable("id") String id) {

        return guideService.가이드_조회1(id);
    }

    @GetMapping("/gcount")
    public GItemDAO findCount(String id){
        System.out.println(id +"매출조회");
        return guideService.매출_조회(id);
    }

    @GetMapping("/gcount7")
    public GItemDAO findCount7(String id){
        return guideService.매출_조회7(id);
    }


    @GetMapping("/gcount1")
    public GItemDAO findCount1(String id){
        System.out.println("매출조회1");
        return guideService.매출_조회1(id);
    }

    @GetMapping("/gcount30")
    public GItemDAO findCount30(String id){
        return guideService.매출_조회30(id);
    }


    @GetMapping("/gitemview/{id}")
    public GItemDAO findGitem(@PathVariable("id") String id){
        System.out.println("상품 상세보기");
        return guideService.가이드_상품조회1(id);
    }

    @GetMapping("/guide/search/{keyword}")
    public List<GuideDAO> selectList(@PathVariable("keyword") String keyword){
        System.out.println("[api/guide/search] keyword: " + keyword);
        return guideService.가이드_검색(keyword);
    }

    @GetMapping("/gitem/search/{keyword}")
    public List<GItemDAO> selectListItem(@PathVariable("keyword") String keyword){
        return guideService.가이드상품_검색(keyword);
    }

    @PostMapping("/gjoin")
    public void insertGuide(GuideDAO guideDAO) {

        guideService.가이드_등록(guideDAO);
        //System.out.println(guideDAO);
    }

    @PostMapping("/gijoin")
    public void insertGitem(GItemDAO gitemDAO) {

        guideService.가이드_상품등록(gitemDAO);

    }
    @PostMapping("/gitjoin")
    public void insertTimeGitem(GItemDAO gitemDAO){
        System.out.println("시간등록");
        guideService.가이드_시간등록(gitemDAO);
    }

    @GetMapping("/gitem")
    public List<GItemDAO> findAllProduct(){
        System.out.println("가이드상품조회");
        return guideService.가이드상품_조회();
    }
    @GetMapping("/gtime/{id}")
    public List<GItemDAO> findTime(@PathVariable("id") String id, String time){
        System.out.println(time);
        System.out.println("시간조회");
        return guideService.가이드상품_시간조회(id, time);

    }


    @DeleteMapping("/dgitemOne")
    public void dgitemOne(String id){
        System.out.println(id);
        guideService.가이드상품_삭제1(id);
    }

    @PutMapping("/gitemTimeUpdate")
    public int timeUpdate(int id){
        System.out.println(id+"시간수정");
        return guideService.시간_수정(id);
    }

    @PutMapping("/guideIntro/{id}")
    public void updateIntro(GuideDAO guideDAO, @PathVariable("id") String id){
        System.out.println("소개수정");
         guideService.소개_수정(guideDAO,id);
    }
    @PostMapping("/gitemReserve")
    public void insertReserve(@RequestBody GItemDAO gitemDAO){
        System.out.println(gitemDAO);
        guideService.예약_등록(gitemDAO);
    }

    @PostMapping("/gPayInsert")
    public void insertPay(GItemDAO gItemDAO){
        System.out.println(gItemDAO +"결제");
        guideService.결제(gItemDAO);
    }

    @GetMapping("/gTime")
    public List<GItemDAO> getTime(String id){
        return guideService.시간_조회(id);

    }









}

