package c.e.exper.controller;

import c.e.exper.data.GItemDTO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.ShareDTO;
import c.e.exper.data.recommendDTO;
import c.e.exper.mapper.GItemMapper;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.ShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiRecommend {

    final PlaceMapper placeMapper;

    @Autowired
    ShareMapper shareMapper;
    @Autowired
    GItemMapper gItemMapper;

    public ApiRecommend(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    @GetMapping("/recPlace")
    public List<PlaceDAO> recPlace() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id);
        List<recommendDTO> similarUsers = placeMapper.findSimilarUser(id);
        System.out.println(similarUsers);
        if (similarUsers.isEmpty()) {
            System.out.println("EMPTYYYYYYYYY");
            List<PlaceDAO> Empty = null;
            return Empty;
        }
        String similarUser = similarUsers.get(0).getUser_id();
        System.out.println(similarUser);
        List<PlaceDAO> places = placeMapper.findRecPlace(id, similarUser);
        System.out.println(places);

        return places;
    }

    @GetMapping("/bestPlace")
    public List<PlaceDAO> bestPlace() {

        List<PlaceDAO> places = placeMapper.findBestPlace();
        System.out.println(places);

        return places;
    }

    @GetMapping("/bestHotel")
    public List<PlaceDAO> bestHotel() {

        List<PlaceDAO> places = placeMapper.findBestHotel();
        System.out.println(places);

        return places;
    }

    @GetMapping("/bestGitem")
    public List<GItemDTO> bestGitem() {

        List<GItemDTO> gItems = gItemMapper.findBestGItem();
        System.out.println(gItems);

        return gItems;
    }

    @GetMapping("/bestShare")
    public List<ShareDTO> bestShare() {

        List<ShareDTO> shares = shareMapper.findBestShare();

        shares.forEach((i) -> {
            i.setPic_name(shareMapper.findPicturesById(i.getShare_id()));
            i.setFirstimage(shareMapper.getShareFirstImages(i.getShare_id()));
        });

        return shares;
    }


}
