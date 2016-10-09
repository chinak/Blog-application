package com.interceptor.repository;

import com.interceptor.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nzhou026 on 10/5/2016.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

    @Modifying
    @Transactional
    @Query("update UserEntity us set us.nickname=:qNickname, us.firstname=:qFirstName, us.lastname=:qLastName, us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
                           @Param("qLastName") String qLastName, @Param("qPassword") String password, @Param("qId") Integer id);
}
