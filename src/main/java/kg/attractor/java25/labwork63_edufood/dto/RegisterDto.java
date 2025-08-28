package kg.attractor.java25.labwork63_edufood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Email(message = "Введите корректный email")
    @NotBlank(message = "Email не должен быть пустым")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, max = 64, message = "длина пароля должен быть больше 8")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Пароль должен содержать хотя бы 1 заглавную букву и 1 цифру")
    private String password;
}
