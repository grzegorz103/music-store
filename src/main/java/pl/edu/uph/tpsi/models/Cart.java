package pl.edu.uph.tpsi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope (value = "session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart
{
        private List<CartItem> list = new ArrayList<>();
}
