package com.academy.project.validation;

import com.academy.project.exception.InvalidStringFormatException;
import com.academy.project.model.CommunityAdminAndManager;
import org.springframework.stereotype.Component;


@Component
public class Validator
{
    private static final String NAME =  "[a-zA-Z]{2,}[a-zA-Z-, .Ññ]";
    private static final String SPECIAL_CHARACTERS = "[-, .Ññ]+[a-zA-Z-, .Ññ]";
    private static final String EMAIL = "";

    public boolean checkIfValid(CommunityAdminAndManager manager) throws InvalidStringFormatException {
        if(manager.getName().length() < 2){
            throw new InvalidStringFormatException("Name should be greater than 2 characters");
        }
        if(manager.getName().length() > 20){
            throw new InvalidStringFormatException("Name length should be a maximum of 20 characters");
        }
        if(manager.getName().matches(SPECIAL_CHARACTERS)){
            throw new InvalidStringFormatException("Name must not start with special character.");
        }
        if(!manager.getName().matches(NAME)){
            throw new InvalidStringFormatException("Name should not contain invalid characters!");
        }
        return true;
    }

}
