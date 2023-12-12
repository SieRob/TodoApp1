package be.vdab.todo_app1.repositories;

import be.vdab.todo_app1.domain.Mens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensRepository extends JpaRepository<Mens, Long> {
}
