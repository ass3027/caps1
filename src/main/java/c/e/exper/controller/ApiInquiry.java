package c.e.exper.controller;

import c.e.exper.data.AnswerDAO;
import c.e.exper.data.InquiryDAO;
import c.e.exper.data.UserDAO;
import c.e.exper.mapper.InquiryMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;


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
    public boolean write(InquiryDAO inquiryDAO) {
        System.out.println(inquiryDAO);
        return inquiryMapper.insert(inquiryDAO);
    }

    //게시판목록
    @GetMapping("/Questions")
    public List<InquiryDAO> getListInfo() {
        return inquiryMapper.list();
    }


    //상세페이지
    @GetMapping("/DetailPage")
    public Map<String,Object> getPageDetails( //(객체)
            @RequestParam("inq_id") String inq_id){


//        System.out.println(inq_id);
//        inquiryMapper.updateCount(inq_id);
        Optional<InquiryDAO> inquiry = inquiryMapper.listPage(inq_id);
        Optional<AnswerDAO> answer = inquiryMapper.answerListPage(inq_id);
        Map<String,Object> returnData = new HashMap<>();
        if (inquiry.isEmpty()) {
            returnData.put("inquiry",new InquiryDAO());
        } else {
            returnData.put("inquiry",inquiry.get());
        }

        if (answer.isEmpty()) {
            returnData.put("answer",new AnswerDAO());
        } else {
            returnData.put("answer",answer.get());
        }

//        System.out.println("88"+returnData.get("answer").getAns_id());
        inquiryMapper.updateCount(inq_id);
        return returnData;
    }

    //게시글 수정하기
    @PostMapping("WritingMod")
    public boolean getListPageModUpdate(InquiryDAO inquiryDAO) {
        return inquiryMapper.updateListPageInfo(inquiryDAO);
    }

    //게시글 삭제하기
    @DeleteMapping("/DetailPage")
    public String getListPageDetailsDelete(
            @RequestParam("inq_id") String inq_id) {
        inquiryMapper.deleteListPageInfo(inq_id);
        return "삭제되었습니다";
    }


//    @GetMapping("/Questions/{id}")
//        public String ListPageCount(@PathVariable String inq_id Model model){
//        model.addAttribute("");
//        //        inquiryMapper.updateCount(inq_id);
//
//    }



//    @RequestMapping("/Questions")
//    public void ListPageCount(
//        @RequestParam("inq_id")String inq_id){
//        inquiryMapper.updateCount(inq_id);
//    }




//    @GetMapping("/DetailPage")
//    public AnswerDAO getListPageAnswer(
//            @RequestParam("ans_id") String ans_id){
//            Optional<AnswerDAO> result = inquiryMapper.answerListPage(ans_id);
//            AnswerDAO answerDAO;
//
//            if (ans_id.isEmpty()){
//                answerDAO = new AnswerDAO();
//            }else {
//                answerDAO = result.get();
//            }
//            return answerDAO;
//    }

}