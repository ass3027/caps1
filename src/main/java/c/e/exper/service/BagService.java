package c.e.exper.service;

import c.e.exper.data.BagDAO;
import c.e.exper.mapper.BagMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BagService {
   @Autowired
   public BagMapper bagMapper;
   
//   //물품조회(보관)
//   public List<BagDAO> selectStorage(String user_id){
//      return bagMapper.selectStorage(user_id);
//   }
}
