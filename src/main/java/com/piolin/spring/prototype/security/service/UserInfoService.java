package com.piolin.spring.prototype.security.service;

import com.piolin.spring.prototype.database.entity.UserInfo;
import com.piolin.spring.prototype.database.repo.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserInfoService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);

    private final PasswordEncoder encoder;
    private final UserInfoRepository userInfoRepository;

    @Lazy
    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, PasswordEncoder encoder) {
        this.userInfoRepository = userInfoRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);
        if(userInfo.isEmpty()){
            throw new UsernameNotFoundException("User NOT found by email: " + username);
        }

        UserInfo user = userInfo.get();
        return new User(user.getEmail(), user.getPwd(), Stream.of(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    public UserInfo addUser(UserInfo userInfo){
        userInfo.setPwd(encoder.encode(userInfo.getPwd()));
        UserInfo newUserInfo = userInfoRepository.save(userInfo);
        LOG.info("User Added successfully !!!");
        return newUserInfo;
    }
}
