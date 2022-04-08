package c.e.exper.service;

import c.e.exper.data.GuideDAO;
import c.e.exper.mapper.GuideMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    final
    GuideMapper guideMapper;


    public GuideService(GuideMapper guideMapper) {
        this.guideMapper = guideMapper;
    }

    public List<GuideDAO> 가이드_조회() {
        return guideMapper.selectAll();
    }

    public void 가이드_등록(GuideDAO guideDAO) {
         guideMapper.insert(guideDAO);
    }
}
