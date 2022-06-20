package c.e.exper.controller;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.BookMarkMapper;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookmark")
public class ApiBookMark {

    final BookMarkMapper bookMarkMapper;

    public ApiBookMark(BookMarkMapper bookMarkMapper) {
        this.bookMarkMapper = bookMarkMapper;
    }

    String getUser_id() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @GetMapping("/BookMark")
    public Map<String, Object> AllBookMark() {
        List<PlaceDAO> all = bookMarkMapper.selectAllByUserBookMark(getUser_id());
        List<PlaceDAO> keeper = bookMarkMapper.selectByUserBookMarkKeeper(getUser_id());
        List<PlaceDAO> hotel = bookMarkMapper.selectByUserBookMarkHotel(getUser_id());
        Map<String, Object> bookmarkData = new HashMap<>();

        if (all.isEmpty()) {
            bookmarkData.put("all", new PlaceDAO());
        } else {
            bookmarkData.put("all", all.get());
        }

        if (keeper.isEmpty()) {
            bookmarkData.put("keeper", new PlaceDAO());
        } else {
            bookmarkData.put("keeper", keeper.get(0));
        }

        if (hotel.isEmpty()) {
            bookmarkData.put("hotel", new PlaceDAO());
        } else {
            bookmarkData.put("hotel", hotel.get(0));
        }
        System.out.println(bookmarkData.get("all"));
        return bookmarkData;

    }
}



//    즐겨찾기 목록 나타내기
//    @GetMapping("/BookMark")
//    public List<PlaceDAO> getViewAll() {
//        System.out.println(bookMarkMapper.selectAllByUserBookMark(getUser_id()).get(0).getUser_id());
//        return bookMarkMapper.selectAllByUserBookMark(getUser_id());
//    }
//
//    즐겨찾기(키퍼)
//    @GetMapping("/keeper")
//    public List<PlaceDAO> getViewKeeper() {
//        System.out.println(bookMarkMapper.selectByUserBookMarkKeeper(getUser_id()).size());
//        System.out.println(bookMarkMapper.selectByUserBookMarkKeeper(getUser_id()).get(0).getUser_id());
//        return bookMarkMapper.selectByUserBookMarkKeeper(getUser_id());
//    }
//
//    즐겨찾기(숙소)
//    @GetMapping("/hotel")
//    public List<PlaceDAO> getViewHotel() {
//        System.out.println("Kk"+bookMarkMapper.selectByUserBookMarkHotel(getUser_id()).size());
////        System.out.println(bookMarkMapper.selectByUserBookMarkHotel(getUser_id()).get(0).getUser_id());
//        return bookMarkMapper.selectByUserBookMarkHotel(getUser_id());
//    }



