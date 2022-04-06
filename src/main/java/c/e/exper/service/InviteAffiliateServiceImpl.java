package c.e.exper.service;

import c.e.exper.data.AffiliatedDAO;
import c.e.exper.data.InviteDTO;
import c.e.exper.mapper.AffiliatedMapper;
import c.e.exper.mapper.InviteMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class InviteAffiliateServiceImpl implements InviteAffiliateService{

    final
    InviteMapper inviteMapper;

    final
    AffiliatedMapper affiliatedMapper;

    final
    UserMapper userMapper;

    public InviteAffiliateServiceImpl(InviteMapper inviteMapper, AffiliatedMapper affiliatedMapper, UserMapper userMapper) {
        this.inviteMapper = inviteMapper;
        this.affiliatedMapper = affiliatedMapper;
        this.userMapper = userMapper;
    }

    @Override
    public boolean inviteRedundancyCheck(InviteDTO inviteDTO) {

        //초대할 회원이 없으면 ㅇ안대
        if(userMapper.selectId(inviteDTO.getUser_id()).isEmpty()) {
            return false;
        }

        //이 회원을 이미 초대 했는지
        if( inviteMapper.selectById(inviteDTO.toDAO()).isPresent() ) {
            return false;
        }
        AffiliatedDAO affiliatedDAO = AffiliatedDAO.builder()
                .plan_id(inviteDTO.getPlan_id())
                .user_id(inviteDTO.getUser_id())
                .build();

        //초대할 놈이 이미 플래너에 소속되어있는지
        return affiliatedMapper.SelectById(affiliatedDAO).isEmpty();

    }
}
