package com.ssooya.study1.domain.member;


import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUserId(String userId);
}
