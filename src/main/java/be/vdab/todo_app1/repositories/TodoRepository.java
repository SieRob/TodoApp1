package be.vdab.todo_app1.repositories;

import be.vdab.todo_app1.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query( value = """
                INSERT INTO todos(mensId, tekst, prioriteit, gemaakt)
                VALUES (?,?,?,?)
                """, nativeQuery = true)
    long createTodo(Todo todo);
}
