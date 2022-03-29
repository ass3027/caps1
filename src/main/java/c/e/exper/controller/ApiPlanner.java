package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.AffiliatedMapper;
import c.e.exper.mapper.InviteMapper;
import c.e.exper.mapper.PlannerMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.InviteAffiliateService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    public ApiPlanner(UserMapper userMapper, InviteMapper inviteMapper, PlannerMapper plannerMapper, AffiliatedMapper affiliatedMapper, InviteAffiliateService inviteAffiliateService) {
        this.userMapper = userMapper;
        this.inviteMapper = inviteMapper;
        this.plannerMapper = plannerMapper;
        this.affiliatedMapper = affiliatedMapper;
        this.inviteAffiliateService = inviteAffiliateService;
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

        affiliatedMapper.insert(affiliatedDTO.toDAO());
        inviteMapper.deleteById(affiliatedDTO.getUser_id(),affiliatedDTO.getPlan_id());
        return true;
    }



    @PostMapping("/invite")
    public boolean inviting(@RequestBody InviteDTO inviteDTO){

        if ( inviteAffiliateService.inviteRedundancyCheck(inviteDTO) ) {
            return false;
        }
        inviteMapper.insert(inviteDTO.toDAO());

        return true;
    }

    @GetMapping("/inviteListUser")
    public List<InviteDTO> inviteListUser(@RequestParam("id") String id){
        List<InviteDAO> result = inviteMapper.selectByUserId(id);
        List<InviteDTO> response = new ArrayList<>();

        result.forEach( data -> response.add(data.toDTO()));

        return response;

    }

    @GetMapping("/inviteListPlan")
    public List<InviteDTO> inviteListPlan(@RequestParam("id") String id){

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

    @DeleteMapping("/plan")
    public void plannerDelete(@RequestBody String plan_id){
        System.out.println(plan_id);
        plannerMapper.delete(plan_id);
    }
}
