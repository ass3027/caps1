package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.ProductMapper;
import c.e.exper.service.FileService;
import oracle.ucp.proxy.annotation.Post;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiProduct {

    private final
    FileService fileService;
    
    private final
    PictureMapper pictureMapper;
    
    final
    ProductMapper productMapper;

    public ApiProduct(ProductMapper productMapper, FileService fileService,PictureMapper pictureMapper) {
        this.productMapper = productMapper;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
       
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
    public void productPost(@RequestBody List<ProductTime> bookInfo, String user_id, String pay_price) {
        System.out.println("user_id = " + user_id);
        System.out.println("pay_price = " + pay_price);
        PaymentResult paymentResult = new PaymentResult(user_id, pay_price);
        productMapper.product_payment_Insert(paymentResult);

        String pay_id = productMapper.pay_id();
        System.out.println("pay_id = " + pay_id);

        bookInfo.forEach(it -> {
            it.setPay_id(pay_id);
            productMapper.product_Time_Insert(it);
        });
    }

    @GetMapping("/productBook")
    public List<BookDAO> SelectBook(String id) {
//        List<BookDAO> bookDAOS = productMapper.SelectBook(id);
//        List<String> dateList = new ArrayList<>();
//        List<BookDTO> bookDTOs = new ArrayList<>();
//        for (BookDAO bookDAO : bookDAOS) {
//            String bookDAODate = bookDAO.getDate();
//            dateList.add(bookDAODate);
//        }
//        Collections.sort(dateList);
//        String startDate = dateList.get(0);
//        String endDate = dateList.get(dateList.size() - 1);
//        String startDate1 = startDate.substring(0, 10);
//        String endDate2 = endDate.substring(0, 10);
//
//        for (BookDAO bookDAO : bookDAOS) {
//            bookDAO.setStartDate(startDate1);
//            bookDAO.setEndDate(endDate2);
//        }
//        bookDAOS.stream().map(bookDTO -> bookDTO.toBookDTO()).forEach(bookDTOs::add);
//        return bookDTOs;
        System.out.println("id = " + id);
        List<BookDAO> bookDAOList = productMapper.SelectBook(id);
        System.out.println("bookDAOList = " + bookDAOList);
        return bookDAOList;
    }

    @GetMapping("/productUserBook")
    public List<BookDAO> SelectUserBook(String id) {
//        List<BookDAO> bookDAOS = productMapper.SelectBook(id);
//        List<String> dateList = new ArrayList<>();
//        List<BookDTO> bookDTOs = new ArrayList<>();
//        for (BookDAO bookDAO : bookDAOS) {
//            String bookDAODate = bookDAO.getDate();
//            dateList.add(bookDAODate);
//        }
//        Collections.sort(dateList);
//        String startDate = dateList.get(0);
//        String endDate = dateList.get(dateList.size() - 1);
//        String startDate1 = startDate.substring(0, 10);
//        String endDate2 = endDate.substring(0, 10);
//
//        for (BookDAO bookDAO : bookDAOS) {
//            bookDAO.setStartDate(startDate1);
//            bookDAO.setEndDate(endDate2);
//        }
//        bookDAOS.stream().map(bookDTO -> bookDTO.toBookDTO()).forEach(bookDTOs::add);
//        return bookDTOs;
        System.out.println("id = " + id);
        List<BookDAO> bookDAOList = productMapper.SelectUserBook(id);
        System.out.println("bookDAOList = " + bookDAOList);
        return bookDAOList;
    }

    @GetMapping("/chart")
    public ProductDAO select(String id) {
        return productMapper.product_Sales(id);
    }

    @GetMapping("/chart1")
    public ProductDAO select1(String id) {
        return productMapper.product_Sales1(id);
    }

    @GetMapping("/chart7")
    public ProductDAO select7(String id) {
        return productMapper.product_Sales7(id);
    }

    @GetMapping("/chart30")
    public ProductDAO select30(String id) {
        System.out.println(productMapper.product_Sales30(id) + "asdfasdfa" + id);
        return productMapper.product_Sales30(id);
    }

    @GetMapping("/productNoBook")
    public List<BookDAO> productNoBook(String pd_id, String st_date, String end_date) {
        System.out.println("pd_id  " +  pd_id);
        System.out.println("st_date  " +  st_date);
        System.out.println("end_date  " +  end_date);
        return productMapper.product_book_no(pd_id, st_date, end_date);
    }

    @GetMapping("/productChart")
    public List<ProductDAO> productChart(String user_id){
        return productMapper.product_chart(user_id);
    }

    // 객실별 차트
    @GetMapping("/productChartss")
    public ProductDAO productSelect(String user_id, String value){
        return productMapper.product_chartss(user_id, value);
    }

    @GetMapping("/bookPdId")
    public ProductDAO productBookPdId(String pd_id){
        return productMapper.product_book_pd_id(pd_id);
    }
    
    @PostMapping("/productAdd")
    public boolean placeAdd(@ModelAttribute ProductItemDTO productItem, HttpServletRequest req) throws IOException {
        
        String filePath = fileService.photoSave(productItem.getImage(), req, "placeImage");
        System.out.println("[filePath]" + filePath);
        
        productMapper.productItem(productItem.getPdName(),productItem.getPdPrice(),productItem.getPlId(),productItem.getPdInfo());
        pictureMapper.productImage("/api/photo/"+ filePath,productItem.getPlId());
        return true;
    }

    @GetMapping("/productDetail")
    public List<ProductDAO> productDetail(String pl_id){
        System.out.println(pl_id);
        return productMapper.productItemGet(pl_id);
    }

    @DeleteMapping("/productDelete")
    void productDelete(String pd_id){
        productMapper.productDelete(pd_id);
    }

    @PostMapping("/productEdit")
    public Boolean productEdit(@RequestBody ProductDAO productDAO){
        System.out.println("수정수정수정" + productDAO);
        productMapper.productEdit(productDAO);
        return true;
    }
}
