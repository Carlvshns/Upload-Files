package carl.dev.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carl.dev.demo.domain.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
