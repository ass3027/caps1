package c.e.exper.controller;

import c.e.exper.data.ImportSuppliesDTO;
import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Share;
import c.e.exper.data.Suplies;
import c.e.exper.mapper.ShareMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiShare {


    final
    ShareMapper shareMapper;

    public ApiShare(ShareMapper shareMapper) {
        this.shareMapper = shareMapper;
    }


    @GetMapping("/getSharePosts")
    public List<Share> getSharePosts() {
        System.out.println("컨트롤러안");
        List<Share> s = shareMapper.findAllShares();
        System.out.println(s);

        return s;
    }




}

