package be.vdab.todo_app1.dto;

import jakarta.validation.constraints.NotBlank;

public record NieuweMens(@NotBlank String voornaam, @NotBlank String familienaam) {
}
