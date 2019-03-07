package validators;

import models.Recipe;
import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.ConstraintValidator;

import static play.libs.F.Tuple;

public class RecipeTitleValidator extends Constraints.Validator<String> implements ConstraintValidator<UniqueRecipeTitle, String> {

    final static String message = "error.title";

    @Override
    public void initialize(UniqueRecipeTitle constraintAnnotation) {

    }

    public boolean isValid(String title) {
        // Check if there are other recipes with the same title
        Recipe recipe = Recipe.findByTitle(title);
        return recipe == null;
    }

    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return Tuple(message, new Object[]{});
    }

}
