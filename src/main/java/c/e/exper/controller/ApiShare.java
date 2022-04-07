package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.PlannerMapper;
import c.e.exper.mapper.ShareMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiShare {


    final
    ShareMapper shareMapper;

    final
    PlannerMapper plannerMapper;

    final PictureMapper pictureMapper;

    public ApiShare(ShareMapper shareMapper, PlannerMapper plannerMapper, PictureMapper pictureMapper) {
        this.shareMapper = shareMapper;
        this.plannerMapper = plannerMapper;
        this.pictureMapper = pictureMapper;
    }


    @GetMapping("/getSharePosts")
    public List<Share> getSharePosts() {
        List<Share> s = shareMapper.getTest();
        System.out.println(s);


        return s;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestBody Share share) {

//        System.out.println(LocalDateTime.now().format());
//        Long datetime =
//        System.out.println(datetime);
//        System.out.println(share);
//        Date d = new Date();
//        System.out.println(d);
//        System.out.println("1");
//        System.out.println(d.getTime());
//        System.out.println("1");
//        Timestamp timestamp = new Timestamp(datetime);
//        System.out.println("1");
//        System.out.println("Current Time Stamp: "+timestamp);
//
//        share.setShare_created(timestamp);
        shareMapper.insert(share);

        return share.getShare_id();
    }

    @PostMapping("/addPostPic")
    public String addPostPic(@RequestBody List<SharePictureDAO> pictures) {
        for (int i = 0; i < pictures.size(); i++) {
            System.out.println(pictures.get(i));
            shareMapper.insertSharesPictures(pictures.get(i));
        }

        return "good2";
    }

    @GetMapping("/loadPlanner")
    public List<PlannerDTO> loadPlanner(HttpServletRequest req) {
        List<PlannerDAO> plannerDAO = plannerMapper.selectAllById(req.getParameter("id"));
        List<PlannerDTO> plannerDTO = new ArrayList<>();
        plannerDAO.forEach(data -> plannerDTO.add(data.toDTO()));

        return plannerDTO;
    }

    @GetMapping("/loadPictures")
    public List<PictureDAO> loadPictures(HttpServletRequest req) {
        List<PictureDAO> pictureDAO = pictureMapper.selectAllbyPlanId(req.getParameter("id"));
        System.out.println(pictureDAO);

        return pictureDAO;
    }


    /*상세보기 페이지*/
    @GetMapping("/getSharePostDetails")
    public List<SharePictureDAO> getSharePostDetails() {
        

        return ;
    }


}

