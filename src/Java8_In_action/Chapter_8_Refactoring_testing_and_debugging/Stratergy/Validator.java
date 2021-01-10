package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Stratergy;

import lombok.Data;

@Data
public class Validator {
    private final ValidationStratergy stratergy;

    public Validator(ValidationStratergy stratergy) {
        this.stratergy = stratergy;
    }

    public boolean  validate( String s )
    {
        return stratergy.execute(s);
    }

}
