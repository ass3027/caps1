package c.e.exper.controller;

import c.e.exper.data.BagDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PaymentDTO;
import c.e.exper.mapper.PaymentMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/paymentList/test")
    List<PaymentDAO> paymentListTest() {
        Map<String, Object> result = new HashMap<>();
        return paymentMapper.paymentList("um");
    }
    
    //물품배송 결제
    @PostMapping("/transportPay")
    public void transportPay(@RequestBody PaymentDTO imp){
        String orderId = paymentMapper.getorderId();
        imp.setOrd_id(orderId);
        paymentMapper.transportPay(imp);
    }
}
