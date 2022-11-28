package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CognizantIdValidatorTest {
    @Test
    @DisplayName("Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfCognizantIdIsNull() throws NullPointerException {
        //Arrange
        Validator validator = new Validator();
        //Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            validator.checkCognizantIdIfValid(null);
        });
        //Assert
        assertTrue(thrown.getMessage().contains("CognizantId is required!"));
    }
    @Test
    @DisplayName("Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfCognizantIdIsExceedIn10Characters() throws InvalidInputException {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkCognizantIdIfValid("admin123456");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("CognizantId length should not exceed of 10 characters!"));
    }
}
