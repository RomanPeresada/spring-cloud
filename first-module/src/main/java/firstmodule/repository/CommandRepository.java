package firstmodule.repository;

import firstmodule.domain.CommandDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<CommandDomain, Long> {
}
