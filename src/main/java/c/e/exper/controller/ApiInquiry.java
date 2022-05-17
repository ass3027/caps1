package c.e.exper.controller;

import c.e.exper.data.InquiryDAO;
import c.e.exper.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/inquiry")
public class ApiInquiry {

    final
    InquiryMapper inquiryMapper;
    //형식         //내가쓰는거

    public ApiInquiry(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    //게시판 작성하기
    @PostMapping("/Writing")
    public boolean getListSendfo(InquiryDAO inquiryDAO){
        System.out.println(inquiryDAO);
        boolean result = inquiryMapper.insert(inquiryDAO);
        return result;
    }

    //게시판목록
   @GetMapping("/Questions")
    public List<InquiryDAO> getListinfo() {
        return inquiryMapper.list();
   }


    //상세페이지
    @GetMapping("/DetailPage")
    public InquiryDAO getListPageDetails(
            @RequestParam("inq_id") String inq_id) {
            System.out.println(inq_id);
            Optional<InquiryDAO> a = inquiryMapper.listPage(inq_id);
            InquiryDAO inquiryDAO;

            if(a.isEmpty()){
                inquiryDAO = new InquiryDAO();
            }else {
                inquiryDAO = a.get();
            }
            return inquiryDAO;
       }

    @PostMapping("WritingMod")
    public boolean getListPageModUpdate(InquiryDAO inquiryDAO){
        boolean result = inquiryMapper.updateListPageInfo(inquiryDAO);
        return result;
    }

}
