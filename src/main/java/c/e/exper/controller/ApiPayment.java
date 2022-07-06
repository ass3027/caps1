package c.e.exper.controller;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.mapper.PaymentMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class ApiPayment {

    final
    PaymentMapper paymentMapper;

    public ApiPayment(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/bookList")
    Map<String, Object> getBookList() {
        Map<String, Object> result = new HashMap<>();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        result.put("productBook", paymentMapper.getProductBook(id));

        return result;
    }

    @GetMapping("/paymentList")
    List<PaymentDAO> paymentList() {
        Map<String, Object> result = new HashMap<>();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("id = " + id);
        return paymentMapper.paymentList(id);
    }

    @GetMapping("/paymentList/{test}")
    List<PaymentDAO> paymentListTest(@PathVariable String test) {
        Map<String, Object> result = new HashMap<>();
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

}
