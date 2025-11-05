package com.piolin.spring.prototype.database.repo;

import com.piolin.spring.prototype.database.entity.APIUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface APIUserInfoRepository extends JpaRepository<APIUserInfo, Integer> {

    Optional<APIUserInfo> findByEmail(String email);

}
