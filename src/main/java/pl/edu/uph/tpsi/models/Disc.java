package pl.edu.uph.tpsi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "band")
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String band;

    @Column(name = "title")
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    private String title;

    @Past
    private LocalDate releaseDate;

    @Column(name = "price")
    @Min(1)
    @Max(100000)
    @NotNull
    private Float price;

    @Column(name = "amount")
    @Min(1)
    @Max(100000)
    @NotNull
    private Integer amount;

    @Column(name = "deleted")
    private Boolean deleted;

    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "image_id"))
    @Column(name = "images")
    private List<String> images;

    @Column(name = "description", length = 5000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
