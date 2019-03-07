package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RecipeTitleValidator.class)
public @interface UniqueRecipeTitle {
    String message() default RecipeTitleValidator.message;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}