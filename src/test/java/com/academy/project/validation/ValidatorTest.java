package com.academy.project.validation;
import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.model.CommunityAdminAndManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ValidatorTest {

    //(1L, "dondon", "admin1", "dondon@softvision.com", "dondon", "admin", true)
    @Test
    @DisplayName("" +
            "Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should return true")
    public void checkIfNameIsValid() throws InvalidStringFormatException {
        //ARRANGE
        CommunityAdminAndManager manager = new CommunityAdminAndManager(1L, "dondon", "admin1", "dondon@softvision.com", "dondon", "admin", true);
        Validator validator = new Validator();
        //ACT
        boolean result = validator.checkIfValid(manager);
        //ASSERT
        assertTrue(result);
    }
    //(1L, ".dondon", "admin1", "dondon@softvision.com", "dondon", "admin", true)
    @Test
    @DisplayName("" +
            "Given a CommunitAdminAndManager with the setup above " +
            "When checkIfValid(CommunityAdminAndManager.class) is executed " +
            "Then result should throw InvalidStringFormatException.class")
    public void checkIfNameIsValidIfItStartsWithASpecialCharacter() throws InvalidStringFormatException {
        //ARRANGE
        CommunityAdminAndManager manager = new CommunityAdminAndManager(1L, ".dondon", "admin1", "dondon@softvision.com", "dondon", "admin", true);
        Validator validator = new Validator();
        //ACT
        assertThrows(InvalidStringFormatException.class, () -> {
            boolean result = validator.checkIfValid(manager);
        });
        //ASSERT
    }

}
