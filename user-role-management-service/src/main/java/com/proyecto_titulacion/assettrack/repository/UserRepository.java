package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.status = 'ACTIVE'")
    Page<UserEntity> getAllUsers(PageRequest pageRequest);
    @Query("SELECT u FROM UserEntity u WHERE u.status = 'ACTIVE' AND u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM UserEntity u INNER JOIN u.identityDocuments i WHERE u.status = 'ACTIVE' AND i.identification = :identification")
    Optional<UserEntity> findByIdentification(@Param("identification") String identification);

    @Query("SELECT u FROM UserEntity u INNER JOIN u.role r WHERE u.status = 'ACTIVE' AND r.name = :role")
    List<UserEntity> getUsersByRole(@Param("role") String role);

    @Query("SELECT u FROM UserEntity u INNER JOIN u.identityDocuments i INNER JOIN u.role r " +
            "WHERE u.status = 'ACTIVE' AND (" +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(u.phone) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(i.identification) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :filter, '%')))")
    Page<UserEntity> getUsersByFilter(@Param("filter") String filter, PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.status = 'INACTIVE' WHERE u.id = :id")
    void deleteUsers(@Param("id") Long id);
}
