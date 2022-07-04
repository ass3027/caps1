package c.e.exper.controller;

import c.e.exper.data.BookDAO;
import c.e.exper.data.ProductDAO;
import c.e.exper.data.ProductTime;
import c.e.exper.mapper.ProductMapper;
import oracle.ucp.proxy.annotation.Post;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

//    @PostMapping("/productPost")
//    public void productPost(@RequestParam("pay_price") String pay_price,
//                            @RequestParam("user_id") String user_id, @RequestParam("product_time_num") String product_time_num) {
//        productMapper.bookInsert(pay_price, user_id, product_time_num);
//    }

    @PostMapping("/productPost")
    public void productPost(@RequestBody List<ProductTime> bookInfo){
        bookInfo.forEach( it->{
            productMapper.product_Time_Insert(it);
        });
//        for(int i =0;i<bookInfo.size();i++){
//            productMapper.product_Time_Insert(bookInfo.get(i));
//        }
    }

    @GetMapping("/productBook/{user_id}")
    public List<BookDAO> SelectBook(@PathVariable("user_id")String user_id){
        return productMapper.SelectBook(user_id);
    }
}
