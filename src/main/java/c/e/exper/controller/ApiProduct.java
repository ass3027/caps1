package c.e.exper.controller;

import c.e.exper.data.ProductDAO;
import c.e.exper.mapper.ProductMapper;
import c.e.exper.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiProduct {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/product")
    public List<ProductDAO> findStoreProduct(@RequestParam("pl_id")String pl_id) {
        return productMapper.findStoreProduct(pl_id);
    }
}
