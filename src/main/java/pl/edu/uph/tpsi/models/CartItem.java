package pl.edu.uph.tpsi.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disc_id")
    @NotNull
    private Disc disc;

    @NotNull
    @Max(100000)
    @Positive
    private Integer amount;

    public CartItem(@NotNull Disc disc, @NotNull @Max(100000) @Positive Integer amount) {
        this.disc = disc;
        this.amount = amount;
    }
}
