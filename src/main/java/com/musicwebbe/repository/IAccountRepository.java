package com.musicwebbe.repository;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAllByEmail(String username);

    boolean existsByEmail(String email);

    Optional<Account> findAccountByEmail(String email);

    List<Account> getAllByIsAuthOrderByIdDesc(boolean isAuth);

    Integer countAccountByRoleId(int id);

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE musicweb_md6.account SET `is_auth` = '1' WHERE id = :idUser")
    Integer setAuth(@Param("idUser") Integer idUser);

}
