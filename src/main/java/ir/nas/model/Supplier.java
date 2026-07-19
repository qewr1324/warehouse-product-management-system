package ir.nas.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public final class Supplier extends BaseModel<Integer>
{
    private String companyName;
    private String phone;
}
