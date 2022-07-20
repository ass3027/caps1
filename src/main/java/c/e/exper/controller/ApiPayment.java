package c.e.exper.controller;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.PaymentDTO;
import c.e.exper.mapper.PaymentMapper;
import c.e.exper.mapper.ReviewMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment")
public class ApiPayment {

    final
    PaymentMapper paymentMapper;

    final ReviewMapper reviewMapper;

    public ApiPayment(PaymentMapper paymentMapper, ReviewMapper reviewMapper) {
        this.paymentMapper = paymentMapper;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/bookList")
    Map<String,Object> getBookList(){
        Map<String,Object> result = new HashMap<>();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        result.put("productBook",paymentMapper.getProductBook(id));
        result.put("guideBook",paymentMapper.getGuideBook(id));
        return result;
    }

    @GetMapping("/paymentList")
    List<PaymentDAO> paymentList() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        return paymentMapper.paymentList(id);

    }

    @GetMapping("/paymentList/hotel")
    List<PaymentDAO> hotelPaymentList() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return paymentMapper.hotelPaymentList(id);
    }

    @GetMapping("/paymentList/{test}")
    List<PaymentDAO> paymentListTest(@PathVariable String test) {
        return paymentMapper.paymentList("um");
    }

    @GetMapping("/check/review/{type}/{id}/{pay_id}")
    String checkReview(@PathVariable String type, @PathVariable String id, @PathVariable String pay_id) {

        switch (type) {
            case "가이드 상품":
                if (paymentMapper.paymentCheck(id, SecurityContextHolder.getContext().getAuthentication().getName()).size() == 0) {
                    return "상품 결제 내역이 없습니다.";
                }
        }

        return "결제내역페이지 이동";
    }


    @GetMapping("/gitem/{pay_id}")
    GItemDAO gitemInfo(@PathVariable String pay_id) {

        return paymentMapper.gitemInfoToPayId(pay_id);
    }

    @GetMapping("/placeInfo/{type}/{pay_id}")
    PlaceDAO placeInfo(@PathVariable String type, @PathVariable String pay_id) {

        return switch (type) {
            case "guide" -> paymentMapper.placeInfoToPayIdGuide(pay_id);
            case "hotel" -> paymentMapper.placeInfoToPayIdHotel(pay_id);
            default -> null;
        };
    }

    // 물품배송 결제
    @PostMapping("/transportPay")
    public void transportPay(@RequestBody PaymentDTO imp){
        String orderId = paymentMapper.getorderId();
        imp.setOrd_id(orderId);
        paymentMapper.transportPay(imp);
    }
}
