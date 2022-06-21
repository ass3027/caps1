package c.e.exper.controller;

import c.e.exper.data.ProductDAO;
import c.e.exper.mapper.ProductMapper;
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
    public List<ProductDAO> findStoreProduct(@RequestParam("pl_id")String pl_id) {
        return productMapper.findStoreProduct(pl_id);
    }

    @GetMapping("/productImage")
    public List<ProductDAO> findProductImage(@RequestParam("pd_id")String pd_id){
        return productMapper.ProductImage(pd_id);
    }

}
