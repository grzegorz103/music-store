package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.CartDTO;
import pl.edu.uph.tpsi.models.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart DTOtoCart(CartDTO cartDTO);

    CartDTO CartToDTO(Cart cart);
}
