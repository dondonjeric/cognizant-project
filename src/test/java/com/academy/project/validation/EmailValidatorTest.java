package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.helper.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {
    @Test
    @DisplayName("" +
            "Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfEmailIsNull() {
        //ARRANGE
        Validator validator = new Validator();
        //ACT
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkEmailIfValid(null);
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Email is required!"));
    }
    @Test
    @DisplayName("" +
            "Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfEmailIsInvalidFormat() {
        //ARRANGE
        Validator validator = new Validator();
        //ACT
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkEmailIfValid("vic@.com");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Invalid email format!"));
    }
}
