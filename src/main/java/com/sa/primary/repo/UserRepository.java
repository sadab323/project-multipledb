package com.sa.primary.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.primary.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

}
