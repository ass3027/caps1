package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.*;
import c.e.exper.service.FileService;
import c.e.exper.service.InviteAffiliateService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class ApiPlanner {

    final
    AffiliatedMapper affiliatedMapper;

    final
    UserMapper userMapper;

    final
    InviteMapper inviteMapper;

    final
    PlannerMapper plannerMapper;

    final
    InviteAffiliateService inviteAffiliateService;

    final FileService fileService;

    final PictureMapper pictureMapper;

    public ApiPlanner(UserMapper userMapper, InviteMapper inviteMapper, PlannerMapper plannerMapper, AffiliatedMapper affiliatedMapper, InviteAffiliateService inviteAffiliateService, FileService fileService, PictureMapper pictureMapper) {
        this.userMapper = userMapper;
        this.inviteMapper = inviteMapper;
        this.plannerMapper = plannerMapper;
        this.affiliatedMapper = affiliatedMapper;
        this.inviteAffiliateService = inviteAffiliateService;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
    }





    @GetMapping("/affiliating")
    public List<AffiliatedDTO> affiliatedList(@RequestParam("plan_id") String plan_id){
        List<AffiliatedDAO> result = affiliatedMapper.SelectByPlanId(plan_id);
        List<AffiliatedDTO> response = new ArrayList<>();

        result.forEach( data -> response.add(data.toDTO()));

        return response;
    }

    @PostMapping("/affiliating")
    public boolean affiliating(@RequestBody AffiliatedDTO affiliatedDTO){
        System.out.println(affiliatedDTO.getUser_id());
        System.out.println(affiliatedDTO.getPlan_id());
        affiliatedMapper.insert(affiliatedDTO.toDAO());
        inviteMapper.deleteById(affiliatedDTO.getUser_id(),affiliatedDTO.getPlan_id());
        return true;
    }



    @PostMapping("/invite")
    public boolean inviting(@RequestBody InviteDTO inviteDTO){

        if ( !inviteAffiliateService.inviteRedundancyCheck(inviteDTO) ) {
            return false;
        }
        inviteMapper.insert(inviteDTO.toDAO());

        return true;
    }

    @GetMapping("/inviteListUser")
    public List<InviteDTO> inviteListUser(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        List<InviteDAO> result = inviteMapper.selectByUserId(id);
        List<InviteDTO> response = new ArrayList<>();
        result.forEach( data -> {
            response.add(data.toDTO());
        } );

        return response;

    }

    @GetMapping("/inviteListPlan")
    public List<InviteDTO> inviteListPlan(@RequestParam("id") String id){
        System.out.println(id);
        List<InviteDAO> result = inviteMapper.selectByPlanId(id);
        List<InviteDTO> response = new ArrayList<>();

        result.forEach( data -> response.add(data.toDTO()));
        return response;

    }

    @PostMapping("/plan")
    public boolean planMaking(@RequestBody PlannerDTO plannerDTO){
        plannerMapper.insert(plannerDTO.toDAO());

        return true;
    }

    @GetMapping("/plan")
    public List<PlannerDTO> plannerSearch(){
        List<PlannerDAO> result = plannerMapper.selectAll();
        List<PlannerDTO> convertResult = new ArrayList<>();
        result.forEach(
            row -> convertResult.add(row.toDTO())
        );

        return convertResult;
    }

    @GetMapping("/plan/{user_id}")
    public List<PlannerDTO> plannerSearchByUserId(@PathVariable String user_id){
        List<PlannerDAO> result = plannerMapper.selectAllById(user_id);
        List<PlannerDTO> convertResult = new ArrayList<>();
//        if(result.isEmpty()) return convertResult;

        result.forEach(
                row -> convertResult.add(row.toDTO())
        );

        return convertResult;
    }

    @DeleteMapping("/plan")
    public void plannerDelete(@RequestBody String plan_id){
        System.out.println(plan_id);
        plannerMapper.delete(plan_id);
    }

    @GetMapping("/getPlanPic")
    public List<PictureDAO> getPlanPic(@RequestParam("plan_id") String plan_id){
        return pictureMapper.selectAllbyPlanId(plan_id);
    }

    @PostMapping("/addPlanPic")
    public boolean addPlanPic(PictureDTO pictureDTO, HttpServletRequest req) {


        System.out.println("1");
        //파일 이름 저장 밑 파일 실제 저장
        String filePath = fileService.photoSave(pictureDTO.getPic_name(),req,"planImage");
        System.out.println("2");

        PictureDAO pictureDAO = new PictureDAO();
        pictureDAO.setPic_name(filePath);
        pictureDAO.setUser_id(pictureDTO.getUser_id());
        pictureDAO.setPlan_id(pictureDTO.getPlan_id());

        pictureMapper.InsertPlan(pictureDAO);

        return true;
    }
}
