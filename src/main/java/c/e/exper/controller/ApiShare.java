package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.PlannerMapper;
import c.e.exper.mapper.ShareMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        List<Share> s = shareMapper.findAllShares();
        System.out.println(s);

        return s;
    }

    @PostMapping("/addPost")
    public String addPost() {
        System.out.println();
        return "good";
    }
    @GetMapping("/loadPlanner")
    public List<PlannerDTO> loadPlanner(HttpServletRequest req) {
        List<PlannerDAO> plannerDAO = plannerMapper.selectAllById(req.getParameter("id"));
        List<PlannerDTO> plannerDTO = new ArrayList<>();
        plannerDAO.forEach( data -> plannerDTO.add(data.toDTO()));

        return plannerDTO;
    }

    @GetMapping("/loadPictures")
    public String loadPictures(HttpServletRequest req) {
        List<PictureDAO> pictureDAO = pictureMapper.selectAllbyPlanId(req.getParameter("id"));
        System.out.println(pictureDAO);

        return "good2";
    }







}

