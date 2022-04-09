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
        return guideService.가이드_조회();
    }
    @GetMapping("/guide/{id}")
    public GuideDAO findAllGuide(@PathVariable("id") String id) {
        return guideService.가이드_조회1(id);
    }
    @PostMapping("/gjoin")
    public void insertGuide(GuideDAO guideDAO) {
        guideService.가이드_등록(guideDAO);
        //System.out.println(guideDAO);
    }



}

