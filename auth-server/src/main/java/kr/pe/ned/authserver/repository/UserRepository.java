package kr.pe.ned.authserver.repository;

import kr.pe.ned.authserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLoginId(String loginId);

}
