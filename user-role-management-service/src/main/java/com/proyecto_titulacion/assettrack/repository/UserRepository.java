package com.proyecto_titulacion.assettrack.repository;

import com.proyecto_titulacion.assettrack.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u INNER JOIN u.identityDocuments i WHERE i.identification = :identification")
    Optional<UserEntity> findByIdentification(@Param("identification") String identification);
}
