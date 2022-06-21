package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.PlannerMapper;
import c.e.exper.mapper.ScheduleMapper;
import c.e.exper.mapper.ShareMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiShare {


    final
    ShareMapper shareMapper;

    final
    PlannerMapper plannerMapper;

    final ScheduleMapper scheduleMapper;

    final PictureMapper pictureMapper;



    public ApiShare(ShareMapper shareMapper, PlannerMapper plannerMapper, ScheduleMapper scheduleMapper, PictureMapper pictureMapper) {
        this.shareMapper = shareMapper;
        this.plannerMapper = plannerMapper;
        this.scheduleMapper = scheduleMapper;
        this.pictureMapper = pictureMapper;
    }


    @GetMapping("/getSharePosts")
    public List<ShareDTO> getSharePosts() {
        List<ShareDTO> s = shareMapper.findAllShares();
        s.forEach((i)->{
            i.setPic_name(shareMapper.findPicturesById(i.getShare_id()));
        });
//        for(int i =0;i<s.size();i++){
//            Date d = s.get(i).getShare_created();
//            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//            String f = format.format(d);
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-mm-dd");
//            LocalDateTime date = LocalDateTime.parse(f, formatter);
//            s.get(i).setShare_created(java.sql.Timestamp.valueOf(date));
//        }
        System.out.println(s);


        return s;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestBody Share share) {

        System.out.println(share);
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
        for (SharePictureDAO picture : pictures) {
            System.out.println(picture);
            shareMapper.insertSharesPictures(picture);
        }

        return "good2";
    }

    @GetMapping("/loadPlanner")
    public List<PlannerDTO> loadPlanner(HttpServletRequest req) {

        List<PlannerDAO> plannerDAO = plannerMapper.selectAllById(req.getParameter("id"));

        List<PlannerDTO> plannerDTO = new ArrayList<PlannerDTO>();
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
    public List<Object> getSharePostDetails(
            @RequestParam("share_id") String share_id
    ) {
        System.out.println(share_id);
        List<Object> a = new ArrayList();
        Share s = shareMapper.findShareById(share_id);
        List<ScheduleDAO> schedules = scheduleMapper.selectAllById(s.getPlan_id());
        List<SharePictureDAO> pic = shareMapper.findPicturesById(share_id);

        a.add(s);
        a.add(schedules);
        a.add(pic);

        return a;
    }

    @GetMapping("/copyPlanner")
    public String copyPlanner(@RequestParam("plan_id") String plan_id,
                                 @RequestParam("user_id") String user_id,
                              @RequestParam("share_id") String share_id,
                              @RequestParam("share_title") String share_title

    ) {
        System.out.println(plan_id);
        System.out.println(user_id);
        System.out.println(share_id);

        //optional로 바꾼 부분
        Optional<PlannerDAO> pp = plannerMapper.selectById(plan_id);
        if(pp.isEmpty()) return "시일패애";
        PlannerDAO p = pp.get();

        System.out.println(p);
        p.setUser_id(user_id);
        System.out.println(p.getPlan_id());
        p.setPlan_name("copy of "+share_title);
        plannerMapper.insert(p);
        System.out.println(p.getPlan_id());
        System.out.println("?1");

        List<ScheduleDAO> s = scheduleMapper.selectAllById(plan_id);
        System.out.println(s);
        System.out.println("?2");

        for (ScheduleDAO scheduleDAO : s) {
            System.out.println("?3");
            scheduleDAO.setPlan_id(p.getPlan_id());
            System.out.println(scheduleDAO);
            System.out.println("?4");
            scheduleMapper.insert(scheduleDAO);
            System.out.println("?");
        }
        System.out.println("??");

        shareMapper.updateShareCount(share_id);
        System.out.println("???");

        return "성공";
    }

    @DeleteMapping("/delSharePlan")
    public String delSharePlan(@RequestParam("share_id")String share_id){
        System.out.println(share_id);
        shareMapper.deleteSharePic(share_id);
        shareMapper.deleteSharePlan(share_id);

        return "삭제성공";
    }

    @PutMapping("/editPost")
    public String editPost(@RequestBody Share share){
        System.out.println(share);
        shareMapper.updateShare(share);

        return "수정중";
    }

    @PostMapping("/editPostPic")
    public String editPostPic(
            @RequestBody List<SharePictureDAO> pictures,
            @RequestParam("share_id") String share_id
    ) {
        System.out.println("아아여기요"+share_id);
        shareMapper.deleteSharePic(share_id);

        for (SharePictureDAO picture : pictures) {
            System.out.println(picture);
            shareMapper.insertSharesPictures(picture);
        }

        return "수정완료";
    }

}

