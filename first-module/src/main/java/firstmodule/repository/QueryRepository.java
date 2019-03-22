package firstmodule.repository;

import firstmodule.domain.QueryDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<QueryDomain,Long> {
}
