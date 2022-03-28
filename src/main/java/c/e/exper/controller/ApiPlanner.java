package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.InviteMapper;
import c.e.exper.mapper.PlannerMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class ApiPlanner {

    final
    UserMapper userMapper;

    final
    InviteMapper inviteMapper;

    final
    PlannerMapper plannerMapper;

    public ApiPlanner(UserMapper userMapper, InviteMapper inviteMapper,PlannerMapper plannerMapper) {
        this.userMapper = userMapper;
        this.inviteMapper = inviteMapper;
        this.plannerMapper = plannerMapper;
    }

    @PostMapping("/invite")
    public boolean inviting(@RequestBody InviteDTO inviteDTO){
        System.out.println(inviteDTO.getUser_id());
        Optional<UserDAO> userdata = userMapper.selectId(inviteDTO.getUser_id());
        if(userdata.isEmpty()) return false;

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

        List<InviteDAO> result = inviteMapper.selectByUserId(id);
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
