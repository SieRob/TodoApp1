package be.vdab.todo_app1.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    private Long mensId;
    private String tekst;
    private int prioriteit;
    private LocalDateTime gemaakt;

    public Todo(long mensId, String tekst, int prioriteit) {
        this.mensId=mensId;
        this.tekst = tekst;
        this.prioriteit = prioriteit;
        gemaakt=LocalDateTime.now();
    }

    public Todo(long mensId, String tekst, int prioriteit, LocalDateTime gemaakt) {
        this.mensId=mensId;
        this.tekst = tekst;
        this.prioriteit = prioriteit;
        this.gemaakt=gemaakt;
    }

    protected Todo() {

    }

    public String getTekst() {
        return tekst;
    }

    public int getPrioriteit() {
        return prioriteit;
    }

    public LocalDateTime getGemaakt() {
        return gemaakt;
    }

    public Long getMensId() {
        return mensId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo todo)) return false;
        return Objects.equals(gemaakt, todo.gemaakt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gemaakt);
    }
}
