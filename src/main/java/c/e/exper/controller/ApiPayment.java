package c.e.exper.controller;

import c.e.exper.mapper.PaymentMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    Map<String,Object> getBookList(){
        Map<String,Object> result = new HashMap<>();
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        result.put("productBook",paymentMapper.getProductBook(id));

        return result;
    }
}
