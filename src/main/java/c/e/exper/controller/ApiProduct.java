package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.ProductMapper;
import oracle.ucp.proxy.annotation.Post;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiProduct {

    final
    ProductMapper productMapper;

    public ApiProduct(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/product")
    public List<ProductDAO> findStoreProduct(@RequestParam("pl_id") String pl_id) {
        return productMapper.findStoreProduct(pl_id);
    }


    @GetMapping("/productImage")
    public List<ProductDAO> findProductImage(@RequestParam("pd_id") String pd_id) {
        return productMapper.ProductImage(pd_id);
    }

    @GetMapping("/productTime")
    public List<ProductTime> productTimes(@RequestParam("pd_id") String pd_id) {
        return productMapper.ProductTime(pd_id);
    }

    @PutMapping("/productPut")
    public void productPut(@RequestParam("product_time_num") String product_time_num) {
        productMapper.productSet(product_time_num);
    }

    @PostMapping("/productPost")
    public void productPost(@RequestBody List<ProductTime> bookInfo) {
        PaymentDAO payment = PaymentDAO.builder()
                .pay_time(LocalDateTime.now())
                .
                .build();
        bookInfo.forEach(it -> {
            it.setPay_id();
            productMapper.product_Time_Insert(it);
        });
    }

    @GetMapping("/productBook")
    public List<BookDTO> SelectBook(String id) {
        List<BookDAO> bookDAOS = productMapper.SelectBook(id);
        List<String> dateList = new ArrayList<>();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (BookDAO bookDAO : bookDAOS) {
            String bookDAODate = bookDAO.getDate();
            dateList.add(bookDAODate);
        }
        Collections.sort(dateList);
        String startDate = dateList.get(0);
        String endDate = dateList.get(dateList.size() - 1);
        String startDate1 = startDate.substring(0, 10);
        String endDate2 = endDate.substring(0, 10);

        for (BookDAO bookDAO : bookDAOS) {
            bookDAO.setStartDate(startDate1);
            bookDAO.setEndDate(endDate2);
        }
        bookDAOS.stream().map(bookDTO -> bookDTO.toBookDTO()).forEach(bookDTOs::add);
        return bookDTOs;
    }

    @GetMapping("/chart")
    public ProductDAO select(String id){
        return productMapper.product_Sales(id);
    }

    @GetMapping("/chart1")
    public ProductDAO select1(String id){
        return productMapper.product_Sales1(id);
    }

    @GetMapping("/chart7")
    public ProductDAO select7(String id){
        return productMapper.product_Sales7(id);
    }

    @GetMapping("/chart30")
    public ProductDAO select30(String id){
        System.out.println(productMapper.product_Sales30(id) + "asdfasdfa" +id);
        return productMapper.product_Sales30(id);
    }

    @GetMapping("/productNoBook")
    public List<BookDAO> productNoBook(String pd_id, String pay_id){
        System.out.println("pd_id + \"  \" +pay_id = " + pd_id + "  " +pay_id);
        return productMapper.product_book_no(pd_id, pay_id);
    }
}
