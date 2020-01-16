package pl.edu.uph.tpsi.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class ItemOutOfStockException extends RuntimeException {
    private List<String> items;

    public ItemOutOfStockException(List<String> items) {
        this.items = items;
    }
}
