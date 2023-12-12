package be.vdab.todo_app1.repositories;

import be.vdab.todo_app1.domain.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TodoRepository2 {
    private final JdbcTemplate template;
    public TodoRepository2(JdbcTemplate template) {
        this.template = template;
    }

    public void createTodo(Todo todo) {
        var sql = """
                INSERT INTO todos(mensId, tekst, prioriteit, gemaakt)
                VALUES (?,?,?,?)
                """;

        //var keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            var stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, todo.getMensId());
            stmt.setString(2, todo.getTekst());
            stmt.setInt(3, todo.getPrioriteit());
            stmt.setObject(4, todo.getGemaakt());
            return stmt;
        });
        //return keyHolder.getKey().longValue();
    }

    public List<Todo> findById(long id) {

        var sql = """
                SELECT t.mensId, t.tekst, t.prioriteit, t.gemaakt
                FROM todos t
                WHERE t.mensId = ?
                """;

        RowMapper<Todo> mapper = (rs, rowNum) ->
                new Todo(
                        rs.getLong("mensId"),
                        rs.getString("tekst"),
                        rs.getInt("prioriteit"),
                        rs.getObject("gemaakt", LocalDateTime.class));

        return template.query(sql, mapper, id);

    }
}
