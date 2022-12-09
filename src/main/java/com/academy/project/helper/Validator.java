package com.academy.project.helper;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.exception.RecordNotFoundException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class Validator
{
    @Autowired
    private CommunityAdminAndManagerRepository repository;
    private static final String NAME =  "[a-zA-Z]+[a-zA-Z-, .Ññ]+";
    private static final String SPECIAL_CHARACTERS = "[-, .Ññ]+[a-zA-Z0-9-, .Ññ]+";
    private static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD =  "[a-zA-Z]+[a-zA-Z0-9]+";
    private static final String PASSWORD1 =  "[0-9]+[a-zA-Z]+[a-zA-Z0-9]+";
    private static final String COGNIZANT_ID = "[0-9]+";
    public CommunityAdminAndManager checkIfValidId(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }

    public void checkUpdateIfValid(CommunityAdminAndManager updateManager) {
        CommunityAdminAndManager manager = checkIfValidId(updateManager.getId());
        System.out.println(manager.getIsactive());
        if(!manager.getIsactive()){
            throw new RecordNotFoundException("Record not found!");
        }
        if(updateManager.getIsactive() != true){
            throw new InvalidInputException("IsActive cannot be updated!");
        }
        checkNameIfValid(updateManager.getName());
    }
    public void checkCreateIfValid(CommunityAdminAndManager manager)  {
        checkIfValid(manager);
    }
    public void checkIfValid(CommunityAdminAndManager manager) {
        checkNameIfValid(manager.getName());
        checkEmailIfValid(manager.getEmail());
        checkCognizantIdIfValid(manager.getCognizantId());
        checkPasswordIfValid(manager.getPassword());
        checkRoleTypeIfValid(manager.getRoleType());
    }

    public void checkNameIfValid(String name) {
        if(name == null || name.isBlank()){
            throw new InvalidInputException("Name is required!");
        }
        if(name.length() < 2){
            throw new InvalidInputException("Name length should exceed 2 characters!");
        }
        if(name.length() > 100){
            throw new InvalidInputException("Name length should not exceed 100 characters!");
        }
        if(name.matches(SPECIAL_CHARACTERS)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
        if(!name.matches(NAME)){
            throw new InvalidInputException("Name should not contain invalid characters!");
        }
    }
    public void checkEmailIfValid(String email) throws InvalidInputException {
        if(email == null){
            throw new InvalidInputException("Email is required!");
        }
        if(email.length() > 50){
            throw new InvalidInputException("Email length should not exceed 50!");
        }
        if(!email.matches(EMAIL)){
            throw new InvalidInputException("Invalid email format!");
        }
        Optional<CommunityAdminAndManager> existing= repository.findByEmailAndIsactive(email,true);
        if(existing.isPresent()){
            throw new InvalidInputException("Email must be unique");
        }
    }
    public void checkPasswordIfValid(String password) {
        if(password == null || password.isBlank()){
            throw new InvalidInputException("Password is required!");
        }
        if(password.length() > 100){
            throw new InvalidInputException("Password length should not exceed 100 characters!");
        }

        if(!password.matches(PASSWORD)&& !password.matches(PASSWORD1)){
            throw new InvalidInputException("Password should not contain invalid characters!");
        }

    }
    public void checkCognizantIdIfValid(String cognizantId) {
        if(cognizantId == null || cognizantId.isBlank()){
            throw new InvalidInputException("CognizantId is required!");
        }
        if(cognizantId.length() > 10){
            throw new InvalidInputException("CognizantId length should not exceed of 10 characters!");
        }
        if(!cognizantId.matches(COGNIZANT_ID)){
            throw new InvalidInputException("CognizantId should not contain invalid characters!");
        }
        Optional<CommunityAdminAndManager> existing= repository.findByCognizantIdAndIsactive(cognizantId,true);
        if(existing.isPresent()){
            throw new InvalidInputException("CognizantId must be unique");
        }
    }
    public void checkRoleTypeIfValid(String roleType) {
        if(roleType == null || roleType.isBlank()){
            throw new InvalidInputException("Roletype is required!");
        }
        if(roleType.length() > 10){
            throw new InvalidInputException("Roletype length should not exceed 10 characters!");
        }
        if(!"Admin".equals(roleType) && !"Manager".equals(roleType)){
            throw new InvalidInputException("Invalid roletype given!");
        }
    }
    public void checkFilter(Integer size, Integer offset) {
        if (size == null || offset == null) {
            throw new InvalidInputException("Not a Valid Argument");
        }
        if (size < 0 && offset < 0) {
            throw new InvalidInputException("Invalid Size and Offset Value");
        }
        if(size < 1){
            throw new InvalidInputException("Invalid Size Value");
        }
        if (offset < 0){
            throw new InvalidInputException("Invalid Offset Value");
        }
    }
}
