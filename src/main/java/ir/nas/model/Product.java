package ir.nas.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public final class Product extends BaseModel<Integer>
{
    private String name;
    private double price;
    private int quantity;
}
