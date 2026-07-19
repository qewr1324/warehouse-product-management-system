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
public final class Supplier extends BaseModel<Integer>
{
    private String companyName;
    private String phone;
}
