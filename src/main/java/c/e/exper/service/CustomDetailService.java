package c.e.exper.service;

import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomDetailService implements UserDetailsService {

    final
    UserMapper userMapper;

    public CustomDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        Optional<UserDAO> user = userMapper.selectId(username);
        System.out.println(user.isEmpty());
        if(user.isEmpty()) throw new UsernameNotFoundException(username);

        System.out.println(user.get().getUser_id());
        System.out.println(user.get().getUser_pw());
        System.out.println(user.get().getRole());

        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.get().getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.get().getUser_id(),
                user.get().getUser_pw(),
                authority
        ) ;



    }
}
