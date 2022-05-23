package c.e.exper.service;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import c.e.exper.mapper.GItemMapper;
import c.e.exper.mapper.GuideMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    final
    GuideMapper guideMapper;
    GItemMapper gitemMapper;


    public GuideService(GuideMapper guideMapper, GItemMapper gitemMapper) {

        this.guideMapper = guideMapper;
        this.gitemMapper = gitemMapper;
    }

    public List<GuideDAO> 가이드_조회() {
        return guideMapper.selectAll();
    }

    public List<GuideDAO> 가이드_검색(String keyword){ return guideMapper.selectBykeyword(keyword);}

    public GuideDAO 가이드_조회1(String id) {
        return guideMapper.selectById(id);
    }

    public void 가이드_등록(GuideDAO guideDAO) {

         guideMapper.insert(guideDAO);
    }
    public void 가이드_상품등록(GItemDAO gitemDAO) {

        System.out.println(gitemDAO);
        gitemMapper.insert(gitemDAO);
    }
    public List<GItemDAO> 가이드상품_조회(){

        return gitemMapper.selectAll();

    }
}
