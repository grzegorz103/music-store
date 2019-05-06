package pl.edu.uph.tpsi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table (name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long ID;

        @OneToMany (fetch = FetchType.EAGER)
        @JoinColumn (name = "order_info_id")
        private List<CartItem> discs;

        @Temporal (TemporalType.DATE)
        @Column (name = "order_date")
        private Date orderDate;

        @OneToOne (fetch = FetchType.EAGER)
        @JoinColumn (name = "user_id")
        private User user;
}
