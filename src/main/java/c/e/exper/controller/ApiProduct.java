package c.e.exper.controller;

import c.e.exper.data.BookDAO;
import c.e.exper.data.ProductDAO;
import c.e.exper.data.ProductTime;
import c.e.exper.mapper.ProductMapper;
import oracle.ucp.proxy.annotation.Post;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void productPost(@RequestParam("product_time_num") String product_time_num,
                            @RequestParam("user_id") String user_id, @RequestParam("book_price") String book_price) {
        productMapper.productInsert(product_time_num, user_id, book_price);
    }

    @GetMapping("/productBook/{user_id}")
    public List<BookDAO> SelectBook(@PathVariable("user_id")String user_id){
        return productMapper.SelectBook(user_id);
    }
}
