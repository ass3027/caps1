package c.e.exper.controller;

import c.e.exper.data.Place;
import c.e.exper.data.recommendDTO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiRecommend {

    final PlaceMapper placeMapper;

    public ApiRecommend(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    @GetMapping("/recPlace")
    public List<Place> recPlace(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id);
        List<recommendDTO> similarUsers = placeMapper.findSimilarUser(id);
        System.out.println(similarUsers);
        String similarUser = similarUsers.get(0).getUser_id();
        System.out.println(similarUser);

        List<Place> places = placeMapper.findRecPlace(id,similarUser);
        System.out.println(places);

        return places;
    }
}
