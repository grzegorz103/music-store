package pl.edu.uph.tpsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
        @NotNull
        @NotBlank
        @Length (min = 4, max = 30)
        private String username;

        @NotNull
        @NotBlank
        @Length (min = 4)
        private String password;

        @NotNull
        @NotBlank
        @Length (min = 4)
        private String confirmPassword;

        @Email
        @NotNull
        @NotBlank
        private String email;

        private AddressDTO addressDTO;

        @AssertTrue
        public boolean isPasswordEqual ()
        {
                return password.equals( confirmPassword );
        }
}
