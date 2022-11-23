package com.academy.project.validation;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Validator
{
    @Autowired
    private CommunityAdminAndManagerRepository repository;
    private static final String NAME =  "[a-zA-Z]{2,}[a-zA-Z-, .Ññ]";
    private static final String SPECIAL_CHARACTERS = "[-, .Ññ]+[a-zA-Z-, .Ññ]";
    private static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    public boolean checkUpdateIfValid(CommunityAdminAndManager updateManager) throws InvalidInputException, RecordNotFoundException {
        CommunityAdminAndManager manager = repository.findById(updateManager.getId())
                .orElseThrow(() -> new RecordNotFoundException("Record not found!"));
        return checkIfValid(manager);
    }

    public boolean checkCreateIfValid(CommunityAdminAndManager manager) throws InvalidInputException, RecordNotFoundException {
        return checkIfValid(manager);
    }
    private boolean checkIfValid(CommunityAdminAndManager manager) throws InvalidInputException {
        return checkNameIfValid(manager.getName()) && checkEmailIfValid(manager.getEmail()) && checkCognizantIdIfValid(manager.getCognizantId())
        && checkPasswordIfValid(manager.getPassword()) && checkRoleTypeIfValid(manager.getRoleType());

    }

    private boolean checkNameIfValid(String name) throws InvalidInputException {
        if(name == null){
            throw new NullPointerException("Name should not be null!");
        }
        if(name.length() < 2){
            throw new InvalidInputException("Name length should be a minimum of 2 characters!");
        }
        if(name.length() > 20){
            throw new InvalidInputException("Name length should be a maximum of 20 characters!");
        }
        if(name.matches(SPECIAL_CHARACTERS)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
        if(!name.matches(NAME)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
        return true;
    }

    private boolean checkEmailIfValid(String email) throws InvalidInputException {
        if(email.matches(EMAIL)){
            return true;
        }
        throw new InvalidInputException("Invalid email format!");
    }

    private boolean checkPasswordIfValid(String password) throws InvalidInputException {
        if(password == null){
            throw new NullPointerException("Password should not be null!");
        }
        if(password.length() > 100){
            throw new InvalidInputException("Name length should be a maximum of 100 characters!");
        }
        return true;
    }
    private boolean checkCognizantIdIfValid(String cognizantId) throws InvalidInputException {
        if(cognizantId == null){
            throw new NullPointerException("CognizantId should not be null");
        }
        if(cognizantId.length() > 10){
            throw new InvalidInputException("Name length should be a maximum of 100 characters!");
        }
        return true;
    }
    private boolean checkRoleTypeIfValid(String roleType) throws InvalidInputException {
        if(roleType == null){
            throw new NullPointerException("Roletype should not be null!");
        }
        if(roleType.length() > 10){
            throw new InvalidInputException("Roletype length should be a maximum of 10 characters!");
        }
        if(!roleType.equals("admin".toLowerCase()) && !roleType.equals("manager".toLowerCase())){
            throw new InvalidInputException("Invalid roletype given!");
        }

        return true;
    }



}
