package pl.edu.uph.tpsi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table (name = "discs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disc
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long ID;

        @Column (name = "band")
        @Size (min = 2, max = 50)
        @NotNull
        @NotBlank
        private String band;

        @Temporal (TemporalType.DATE)
        @Past
        private Date releaseDate;

        @Column (name = "price")
        @Min (1)
        @Max (100000)
        @NotNull
        private Float price;

        @Column (name = "amount")
        @Min (1)
        @Max (100000)
        @NotNull
        private Integer amount;

        @Column (name = "deleted")
        private Boolean deleted;
}
