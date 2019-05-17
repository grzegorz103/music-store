package pl.edu.uph.tpsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.uph.tpsi.models.Disc;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscDTO
{
        private Long ID;
        private String band;
        private String title;
        private String description;
        private Date releaseDate;
        private Float price;
        private Integer amount;
        private Boolean deleted;
        private List<String> images;

        public DiscDTO ( Disc disc )
        {
                this.ID = disc.getID();
                this.band = disc.getBand();
                this.title = disc.getTitle();
                this.releaseDate = disc.getReleaseDate();
                this.price = disc.getPrice();
                this.amount = disc.getAmount();
                this.deleted = disc.getDeleted();
                this.images = disc.getImages();
        }
}
