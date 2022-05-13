package c.e.exper.controller;

import c.e.exper.data.BagDAO;
import c.e.exper.data.BagDTO;
import c.e.exper.mapper.BagMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiBag {
   
   final BagMapper bagMapper;
   
   
   @Autowired
   public ApiBag(BagMapper bagMapper) {
      this.bagMapper = bagMapper;
   }
   
   @PostMapping("/addOrder")
   public String addOrder(@RequestBody String bag) throws IOException, JsonMappingException, JsonProcessingException {
      byte[] bytes = bag.getBytes(StandardCharsets.UTF_8);
      String text = new String(bytes, StandardCharsets.UTF_8);
      System.out.println(text);
      
      ObjectMapper objectMapper = new ObjectMapper();
      BagDAO bagDAO = objectMapper.readValue(text, BagDAO.class);

      System.out.println(bagDAO);
      bagMapper.insert(bagDAO);
      return "";
   }
   
   @GetMapping("/bag/{user_id}")
   public List<BagDAO> SearchOrder(@PathVariable String user_id) {
      
      
      return bagMapper.selectByUserId(user_id);
   }
   
}

