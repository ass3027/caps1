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

//    @PostMapping("/addPlanner")
//    public void addPlanner() {
//
//    }


    @GetMapping("/getSharePosts")
    public List<ShareDTO> getSharePosts() {
        List<ShareDTO> s = shareMapper.findAllShares();
        s.forEach((i) -> {
            i.setPic_name(shareMapper.findPicturesById(i.getShare_id()));
            i.setFirstimage(shareMapper.getShareFirstImages(i.getShare_id()));
        });
        System.out.println(s);


        return s;
    }

    @PostMapping("/addPost")
    public String addPost(@RequestBody Share share) {
        Optional<PlannerDAO> plannerDAO = plannerMapper.selectById(share.getPlan_id());
        System.out.println("여기전"+plannerDAO.get().getPlan_id());
        String prePlan_id=plannerDAO.get().getPlan_id();
        plannerDAO.get().setUser_id(null);
        System.out.println(plannerDAO.get().getUser_id());

        if(plannerDAO.isPresent()){
            plannerMapper.insert(plannerDAO.get());
        }else{
            return "0";
        }
        System.out.println("여기후"+plannerDAO.get().getPlan_id());
        String plan_id = plannerDAO.get().getPlan_id();

        List<ScheduleDAO> s = scheduleMapper.selectAllById(prePlan_id);
        System.out.println(s);
        System.out.println("?2");

        for (ScheduleDAO scheduleDAO : s) {
            System.out.println("?3");
            scheduleDAO.setPlan_id(plan_id);
            System.out.println(scheduleDAO);
            System.out.println("?4");
            scheduleMapper.insert(scheduleDAO);
            System.out.println("?");
        }


        share.setPlan_id(plan_id);
        System.out.println(share);
        //inser하기전에 planid를 새로만든걸로
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
        if (pp.isEmpty()) return "시일패애";
        PlannerDAO p = pp.get();

        System.out.println(p);
        p.setUser_id(user_id);
        System.out.println(p.getPlan_id());
        p.setPlan_name("copy of " + share_title);
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
    public String delSharePlan(@RequestParam("share_id") String share_id) {
        System.out.println(share_id);
        shareMapper.deleteSharePic(share_id);
        shareMapper.deleteSharePlan(share_id);

        return "삭제성공";
    }

    @PutMapping("/editPost")
    public String editPost(@RequestBody Share share) {
        System.out.println(share);
        shareMapper.updateShare(share);

        return "수정중";
    }

    @PostMapping("/editPostPic")
    public String editPostPic(
            @RequestBody List<SharePictureDAO> pictures,
            @RequestParam("share_id") String share_id
    ) {
        System.out.println("아아여기요" + share_id);
        shareMapper.deleteSharePic(share_id);

        for (SharePictureDAO picture : pictures) {
            System.out.println(picture);
            shareMapper.insertSharesPictures(picture);
        }

        return "수정완료";
    }

    @GetMapping("/getPreference")
    public String getPreference(String user_id){
        return shareMapper.selectPreference(user_id);
    }

    @PostMapping("/recShare")
    public String recShare(@RequestParam("share_id") String share_id,@RequestParam("user_id") String user_id){
        try{
            shareMapper.insertRecommend(share_id,user_id);
            return "";
        }
        catch (Exception e){
            System.out.println(e);
            return "이미 추천하였습니다";
        }
    }

    @GetMapping("/getShareRec")
    public String getShareRec(String share_id){
        return shareMapper.selectShareRec(share_id);
    }

    @PostMapping("/postComment")
    public void postComment(@RequestBody ShareComment comment){
        System.out.println(comment);
        shareMapper.postComment(comment);
    }

    @GetMapping("/getComments")
    public List<ShareComment> getComments(String share_id){
        return shareMapper.getComments(share_id);
    }

    @DeleteMapping("/delShareComment")
    public void delShareComment(String comment_id){
        shareMapper.delShareComment(comment_id);
    }

}

