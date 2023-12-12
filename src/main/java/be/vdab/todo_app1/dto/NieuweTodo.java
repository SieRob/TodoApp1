package be.vdab.todo_app1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record NieuweTodo(@NotBlank String tekst, @Min(1) @Max(10) int prioriteit) {
}
