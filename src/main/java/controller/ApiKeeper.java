package controller;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.KeeperMapper;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.DeliveryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keep")
public class ApiKeeper {


    final KeeperMapper keeperMapper;

    public ApiKeeper(KeeperMapper keeperMapper) {
        this.keeperMapper = keeperMapper;
    }

    @GetMapping("/find/{keep_id}")
    public PlaceDAO getDuserOrders(@PathVariable("keep_id") String keep_id) {
        System.out.println("[/keep/find] keep_id " + keep_id);

        return keeperMapper.findKeep(keep_id);
    }


}

