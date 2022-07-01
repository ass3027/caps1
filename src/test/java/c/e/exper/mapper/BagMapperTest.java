package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BagMapperTest {
   
   @Autowired
   BagMapper bagMapper;
   
   @Test
   void selectTransport() {
      System.out.println(bagMapper.selectTransport("ttg"));
   }
}