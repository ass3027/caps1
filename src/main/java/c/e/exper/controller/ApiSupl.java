package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiSupl {

    final
    UserMapper userMapper;

    @Autowired
    SuplMapper SuplMapper;

    public ApiSupl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/getMySupl/{plan_id}")
    public List<Plan_Suplies> getMySupl(@PathVariable("plan_id") String plan_id) {
        System.out.println(plan_id);

        return SuplMapper.findMyAllSupl(plan_id);
    }

    @GetMapping("/getSupl")
    public List<Suplies> getSupl() {

        return SuplMapper.findAllSupl();
    }

    @GetMapping("/getPlaceSupl/{pl_id}")
    public List<ImportSuppliesDTO> getPlaceSupl(@PathVariable("pl_id") String pl_id) {
        return SuplMapper.findPlacesSupplies(pl_id);
    }

    @GetMapping("/getSets/{plan_id}")
    public List<ImportSuppliesDTO> getSets(@PathVariable("plan_id") String plan_id) {
        return SuplMapper.findSuppliesSets(plan_id);
    }

    @PostMapping("/inputSupl")
    public String inputSupl(@RequestBody Plan_Suplies p) {
        Suplies b = SuplMapper.findSuplByName(p.getSupl_id().getSupl_name());
        if (b != null) {
            Plan_Suplies ps = new Plan_Suplies();
            ps.setSupl_id(b);
            ps.setName(p.getSupl_id().getSupl_name());
            ps.setPlan_id(p.getPlan_id());
            SuplMapper.insertPlanSuplies(ps);
        } else {
            Plan_Suplies ps = new Plan_Suplies();
            Suplies d = new Suplies();
            d.setSupl_id("0");
            ps.setSupl_id(d);
            ps.setName(p.getSupl_id().getSupl_name());
            ps.setPlan_id(p.getPlan_id());
            System.out.println(ps);
            SuplMapper.insertPlanSuplies(ps);

        }
//      SuplMapper.insertSuplies(p);
        return "yep";
    }

    @PostMapping("/sendItem")
    public String sendItem(@RequestBody ImportSuppliesDTO i) {
        Suplies s = SuplMapper.findSuplByName(i.getSupl_name());
        Plan_Suplies p = new Plan_Suplies();
        p.setSupl_id(s);
        p.setName(s.getSupl_name());
        p.setPlan_id(i.getPlan_id());
        SuplMapper.insertPlanSuplies(p);
        return "sending";
    }

    @PostMapping("/sendList")
    public String sendList(@RequestBody List<ImportSuppliesDTO> i,
                           @RequestParam("plan_id") String plan_id
    ) {
        System.out.println(i);
        System.out.println(plan_id);
        Suplies s;
        Plan_Suplies p;
        for (int j = 0; j < i.size(); j++) {

            s = SuplMapper.findSuplByName(i.get(j).getSupl_name());
            p = new Plan_Suplies();
            p.setSupl_id(s);
            p.setName(s.getSupl_name());
            p.setPlan_id(plan_id);
            SuplMapper.insertPlanSuplies(p);
        }
        return "sending List";
    }

    @PutMapping("/doneSupl")
    public String doneSupl(@RequestBody Plan_Suplies p, @RequestParam("done") boolean done) {
        System.out.println(p.getPlan_supl_id());
        System.out.println(p.getStatus());
        System.out.println(done);
        if (!done) {
            System.out.println("이건가?");
            SuplMapper.updateStatusById(p.getPlan_supl_id(), "0");
        } else {
            System.out.println("ㄴㄴ");
            SuplMapper.updateStatusById(p.getPlan_supl_id(), "1");
        }
        System.out.println("됨?");
        return "done good";
    }

    @DeleteMapping("/delSuplAll")
    public String delSuplAll(@RequestBody List<Plan_Suplies> p) {

        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getPlan_supl_id());
            SuplMapper.deleteSuplies(p.get(i).getPlan_supl_id());
        }
        return "del good";
    }

    @DeleteMapping("/delSuplOne")
    public String delSuplOne(@RequestBody Plan_Suplies p) {
        System.out.println(p.getPlan_supl_id());
        SuplMapper.deleteSuplies(p.getPlan_supl_id());

        return "del good";
    }

    @PutMapping("/updateSuplOne")
    public String updateSuplOne(
            @RequestParam("plan_supl_id") String plan_supl_id,
            @RequestParam("name") String name) {
        System.out.println("id=" + plan_supl_id + "바뀔내용" + name);
        SuplMapper.updateName(name, plan_supl_id);

        return "update good";
    }

    @PutMapping("/updateQuantity")
    public String updateQuantity(@RequestBody Plan_Suplies p) {
        System.out.println(p.getPlan_supl_id());
        System.out.println(p.getQuantity());
        SuplMapper.updateQuantity(p.getPlan_supl_id(), p.getQuantity());
        System.out.println("됨?");
        return "update quantity good";
    }

    @GetMapping("/searchSupply")
    public List<Suplies> searchSupply(@RequestParam("keyword") String keyword) {
        return SuplMapper.findSuplLikeName(keyword);
    }

    @DeleteMapping("/delPlSupply")
    public void delPlSupply(@RequestParam("pl_id") String pl_id,
                            @RequestParam("supl_id") String supl_id) {
        SuplMapper.delPlSupply(supl_id, pl_id);
    }

    @PostMapping("/addPlSupply")
    public void addPlSupply(@RequestParam("pl_id") String pl_id,
                            @RequestParam("supl_id") String supl_id) {
        SuplMapper.addPlSupply(supl_id, pl_id);

    }

    @PostMapping("/addSupply")
    public String addSupply(@RequestParam("supl_name") String supl_name) {
        SuplMapper.addSupplies(supl_name);
        return SuplMapper.findSuplByName(supl_name).getSupl_id();
    }


}

