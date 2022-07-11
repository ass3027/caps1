package c.e.exper.controller;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.BookMarkMapper;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.*;

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
    public Map<String, List<PlaceDAO>> AllBookMark() {
        List<PlaceDAO> all = bookMarkMapper.selectAllByUserBookMark(getUser_id());
        List<PlaceDAO> keeper = bookMarkMapper.selectByUserBookMarkKeeper(getUser_id());
        List<PlaceDAO> hotel = bookMarkMapper.selectByUserBookMarkHotel(getUser_id());
        List<PlaceDAO> tour = bookMarkMapper.selectByUserBookMarkTour(getUser_id());
        Map<String, List<PlaceDAO>> bookmarkData = new HashMap<>();

        if (all.isEmpty()) {
            bookmarkData.put("all", new ArrayList<>());
        } else {
            bookmarkData.put("all", all);
        }

        if (keeper.isEmpty()) {
            bookmarkData.put("keeper", new ArrayList<>());
        } else {
            bookmarkData.put("keeper", keeper);
        }

        if (hotel.isEmpty()) {
            bookmarkData.put("hotel", new ArrayList<>());
        } else {
            bookmarkData.put("hotel", hotel);
        }

        if (tour.isEmpty()) {
            bookmarkData.put("tour", new ArrayList<>());
        } else {
            bookmarkData.put("tour", tour);
        }



        return bookmarkData;

    }

    @GetMapping("/getBookMarkStatus")
    String getBookMarkStatus(@RequestParam("user_id")String user_id,
                             @RequestParam("pl_id")String pl_id){
        return bookMarkMapper.getBookMarkStatus(user_id,pl_id);
    }

    @PostMapping("/insertBookMark")
    void insertBookMark(@RequestParam("user_id")String user_id,
                             @RequestParam("pl_id")String pl_id){
        bookMarkMapper.insertBookMark(user_id,pl_id);
    }

    @DeleteMapping("/deleteBookMark")
    void deleteBookMark(@RequestParam("user_id")String user_id,
                        @RequestParam("pl_id")String pl_id){
        bookMarkMapper.deleteBookMark(user_id,pl_id);
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



