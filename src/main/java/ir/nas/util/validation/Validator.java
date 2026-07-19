package ir.nas.util.validation;

import ir.nas.exception.service.InvalidInputException;

public final class Validator
{
    private String message;
    private boolean isValid = true;

    private Validator()
    {}

    public static final Validator of()
    {
        return new Validator();
    }

    public final <T extends Object> Validator requireNotNull(T obj)
    {
        if (this.isValid && obj == null) {
            this.message = "Invalid Null Object!";
            this.isValid = false;
        }

        return this;
    }

    public final <T extends Number> Validator requireNotNegativeNumber(T number)
    {
        if (this.isValid && number.doubleValue() < 0) {
            this.message = "Invalid Negative Number";
            this.isValid = false;
        }

        return this;
    }

    public final <T extends Number> Validator requireNotNegativeZeroNumber(T number)
    {
        if (this.isValid && number.doubleValue() < 1) {
            this.message = "Invalid Negative Or Zero Number";
            this.isValid = false;
        }

        return this;
    }

    public void validate()
    {
        if (!this.isValid)
            throw new InvalidInputException(this.message);
    }
}
