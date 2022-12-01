package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {
    @Test
    @DisplayName("" +
            "Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfPasswordIsExceedIn100Characters(){
        //ARRANGE
        Validator validator = new Validator();
        //ACT
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkPasswordIfValid("abcdefghijklmnopqrstuvwxyz123456abcdefghijklmnopqrstuvwxyz123456abcdefghijklmnopqrstuvwxyz123456abcdefghijklmnopqrstuvwxyz123456abcdefghijklmnopqrstuvwxyz123456");
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Password length should not exceed 100 characters!"));
    }
    @Test
    @DisplayName("" +
            "Given a CommunityAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfPasswordIsNUll(){
        //ARRANGE
        Validator validator = new Validator();
        //ACT
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkPasswordIfValid(null);
        });
        //Assert
        assertTrue(thrown.getMessage().contains("Password is required!"));
    }
}
