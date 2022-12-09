package com.academy.project.validation;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.helper.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfNameInputNull() {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid("");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Name is required!"));
    }

    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfNameIfOneCharacter() {
        //Arrange
        Validator validator = new Validator();
       //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid("a");
        });
       //Assert
        assertTrue(thrown.getMessage().contains("Name length should exceed 2 characters!"));
    }
    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfNameIfExceed100Characters() {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid("A while back I needed to count the amount of letters that a piece of text in an email template had to");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Name length should not exceed 100 characters!"));
    }

    @Test
    @DisplayName("Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidInputException.class")
    public void checkIfNameIfContainsSpecialCharacters() {
        //Arrange
        Validator validator = new Validator();
        //Act
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid("!@vic");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Name should not contain invalid characters!"));
    }
}
