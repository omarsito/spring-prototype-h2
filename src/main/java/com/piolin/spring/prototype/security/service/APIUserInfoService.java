package com.piolin.spring.prototype.security.service;

import com.piolin.spring.prototype.database.entity.APIUserInfo;
import com.piolin.spring.prototype.database.repo.APIUserInfoRepository;
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
public class APIUserInfoService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(APIUserInfoService.class);

    private final PasswordEncoder encoder;
    private final APIUserInfoRepository APIUserInfoRepository;

    @Lazy
    @Autowired
    public APIUserInfoService(APIUserInfoRepository APIUserInfoRepository, PasswordEncoder encoder) {
        this.APIUserInfoRepository = APIUserInfoRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<APIUserInfo> userInfo = APIUserInfoRepository.findByEmail(username);
        if(userInfo.isEmpty()){
            throw new UsernameNotFoundException("User NOT found by email: " + username);
        }

        APIUserInfo user = userInfo.get();
        return new User(user.getEmail(), user.getPwd(), Stream.of(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    public APIUserInfo addUser(APIUserInfo APIUserInfo){
        APIUserInfo.setPwd(encoder.encode(APIUserInfo.getPwd()));
        APIUserInfo newAPIAPIUserInfo = APIUserInfoRepository.save(APIUserInfo);
        LOG.info("User Added successfully !!!");
        return newAPIAPIUserInfo;
    }
}
