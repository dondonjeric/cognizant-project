package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTypeValidatorTest {
    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfRoleTypeIsNull() {
        //Arrange
        Validator validator = new Validator();
        //Act
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            validator.checkRoleTypeIfValid(null);
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Roletype is required!"));
    }
    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfRoleTypeIsExceedIn10Characters() {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid("qwertyuiopas");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Roletype length should not exceed 10 characters!"));
    }
    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfRoleTypeIsInvalidFormat() {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid("qwerty");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Invalid roletype given!"));
    }
}