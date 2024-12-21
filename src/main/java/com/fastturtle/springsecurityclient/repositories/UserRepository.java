package com.fastturtle.springsecurityclient.repositories;

import com.fastturtle.springsecurityclient.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
