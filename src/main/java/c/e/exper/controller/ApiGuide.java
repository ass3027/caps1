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
    @GetMapping("/guide/search/{keyword}")
    public List<GuideDAO> selectList(@PathVariable("keyword") String keyword){
        System.out.println("[api/guide/search] keyword: " + keyword);
        return guideService.가이드_검색(keyword);
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

    @GetMapping("/gitem")
    public List<GItemDAO> findAllProduct(){
        System.out.println("가이드상품조회");
        return guideService.가이드상품_조회();
    }







}

