package pl.edu.uph.tpsi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope (value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart
{
        @JsonProperty ("FGY")
        private List<CartItem> list = new ArrayList<>();

        public List<CartItem> getList ()
        {
                return list;
        }

        public void setList ( List<CartItem> list )
        {
                this.list = list;
        }
}
