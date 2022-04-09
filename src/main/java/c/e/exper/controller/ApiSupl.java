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

    @GetMapping("/getMySupl")
    public List<Plan_Suplies> getMySupl() {

        return SuplMapper.findMyAllSupl("1");
    }

    @GetMapping("/getSupl")
    public List<Suplies> getSupl() {

        return SuplMapper.findAllSupl();
    }

    @GetMapping("/getSets")
    public List<ImportSuppliesDTO> getSets() {
        return SuplMapper.findSuppliesSets("1");
    }

    @PostMapping("/inputSupl")
    public String inputSupl(@RequestBody Plan_Suplies p){
        System.out.println(p);
        System.out.println("post컨트롤러안");
        System.out.println(p.getSupl_id().getSupl_name());
        Suplies b = SuplMapper.findSuplByName(p.getSupl_id().getSupl_name());
        if(b != null){
            Plan_Suplies ps = new Plan_Suplies();
            System.out.println("1");
            ps.setSupl_id(b);
            System.out.println("2");
            ps.setName(p.getSupl_id().getSupl_name());
            System.out.println("3");
            ps.setPlan_id("1");
            System.out.println("4");
            SuplMapper.insertSuplies(ps);
            System.out.println("있을때완료");
        }else{
            Plan_Suplies ps = new Plan_Suplies();
            System.out.println("11");
            Suplies d = new Suplies();
            d.setSupl_id("0");
            System.out.println(d.getSupl_id());
            ps.setSupl_id(d);
            System.out.println("22");
            ps.setName(p.getSupl_id().getSupl_name());
            System.out.println("33");
            ps.setPlan_id("1");
            System.out.println("44");
            SuplMapper.insertSuplies(ps);
            System.out.println("없을때완료");

        }
//      SuplMapper.insertSuplies(p);
        return "yep";
    }

    @PostMapping("/sendItem")
    public String sendItem(@RequestBody ImportSuppliesDTO i){
        System.out.println(i);
        Suplies s = SuplMapper.findSuplByName(i.getSupl_name());
        Plan_Suplies p = new Plan_Suplies();
        p.setSupl_id(s);
        p.setName(s.getSupl_name());
        p.setPlan_id("1");
        SuplMapper.insertSuplies(p);
        return "sending";
    }

    @PostMapping("/sendList")
    public String sendList(@RequestBody List<ImportSuppliesDTO> i){
        System.out.println(i);
        Suplies s;
        Plan_Suplies p;
        for(int j=0; j<i.size();j++){

            s = SuplMapper.findSuplByName(i.get(j).getSupl_name());
            p = new Plan_Suplies();
            p.setSupl_id(s);
            p.setName(s.getSupl_name());
            p.setPlan_id("1");
            SuplMapper.insertSuplies(p);
        }
        return "sending List";
    }

    @PutMapping("/doneSupl")
    public String doneSupl(@RequestBody Plan_Suplies p){
        System.out.println(p.getPlan_supl_id());
        System.out.println(p.getStatus());
        if(p.getStatus().equals("true")){
            System.out.println("이건가?");
            SuplMapper.updateStatusById(p.getPlan_supl_id(),"0");
        } else{
            System.out.println("ㄴㄴ");
            SuplMapper.updateStatusById(p.getPlan_supl_id(),"1");
        }
        System.out.println("됨?");
        return "done good";
    }

    @DeleteMapping("/delSuplAll")
    public String delSuplAll(@RequestBody List<Plan_Suplies> p){

        for(int i =0; i<p.size(); i++){
            System.out.println(p.get(i).getPlan_supl_id());
            SuplMapper.deleteSuplies(p.get(i).getPlan_supl_id());
        }
        return "del good";
    }
    @DeleteMapping("/delSuplOne")
    public String delSuplOne(@RequestBody Plan_Suplies p){
        System.out.println(p.getPlan_supl_id());
        SuplMapper.deleteSuplies(p.getPlan_supl_id());

        return "del good";
    }

    @PutMapping("/updateQuantity")
    public String updateQuantity(@RequestBody Plan_Suplies p){
        System.out.println(p.getPlan_supl_id());
        System.out.println(p.getQuantity());
        SuplMapper.updateQuantity(p.getPlan_supl_id(),p.getQuantity());
        System.out.println("됨?");
        return "update quantity good";
    }




}

