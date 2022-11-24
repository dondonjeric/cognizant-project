package com.academy.project;

import com.academy.project.controller.CommunityAdminAndManagerController;
import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunityAdminAndManagerService;
import com.academy.project.validation.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

public class CommunityAdminAndManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommunityAdminAndManagerService communityAdminAndManagerService;
    @Autowired
    CommunityAdminAndManagerController communityAdminAndManagerController;


    @Test
    public void testAddCommunityAndAdminManager_WhenValidManager_ShouldAddManagerName() throws Exception {

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("Admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin1234");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
        .andReturn();
        String content = result.getResponse().getContentAsString();
        String expected ="Successfully registered!";
        assertEquals(expected,content);
    }
//    @Test
//    public void testAddCommunityAndAdminManager_NegativeCase_For_CheckCreatedIfValidTable() throws Exception {
//
//        Validator validator =new Validator();
//
//        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
//        request.setName("Manager");
//        request.setId(2L);
//        request.setRoleType("admin");
//        request.setEmail("Cognizant.com");
//        request.setPassword("admin1234");
//        request.setCognizantId("34254454564655");
//        CommunityAdminAndManager response = new CommunityAdminAndManager();
//        response.setName("Manager");
//        response.setId(2L);
//        response.setRoletype("Admin");
//        response.setEmail("Cognizant@mail.com");
//        response.setPassword("admin1234");
//        response.setCognizantid("admin1234");
//        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
//                .thenReturn(response);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        CreateCommunityAdminAndManagerRest manager = request;
//        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
//            validator.checkCreateIfValid(manager);
//        });
//        assertTrue(thrown.getMessage().contains("Record not found!"));
//    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_NotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
         mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
      });
        assertTrue(thrown.getMessage().contains("Name is required!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_Length_ShouldNotExceed2() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("a");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
        });
        assertTrue(thrown.getMessage().contains("Name length should exceed 2 characters!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_LengthLessShouldNotExceed100() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("alivondonjoshanjehalalexhsdjghsdhsdghhdgjkhsdjhsdgjkhdsjkhdsjkhdsjkhbvjkvbsjkbvdvgvicoroaydfghjhjdgsfhjksdfgdfsuigdfuigdfsiudfdbgsdhjgsdfhgsdfsdfsdfldfhs");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
        });
        assertTrue(thrown.getMessage().contains("Name length should not exceed 100 characters!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_ShouldNotContainInvalidCharacters() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("@SD!!a");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
        });
        assertTrue(thrown.getMessage().contains("Name should not contain invalid characters!"));
    }

    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_Name_ShouldNotContainInvalidCharacter() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("_|||");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("admin1234");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkNameIfValid(request.getName());
        });
        assertTrue(thrown.getMessage().contains("Name should not contain invalid characters!"));
    }

    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_CognizantId_ShouldNotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkCognizantIdIfValid(request.getCognizantId());
        });
        assertTrue(thrown.getMessage().contains("CognizantId is required!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_CognizantId_ShouldNotBeExceedOf10() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant@mail.com");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkCognizantIdIfValid(request.getCognizantId());
        });
        assertTrue(thrown.getMessage().contains("CognizantId length should be a maximum of 10 characters!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_EmailIfValid_ShouldNotBeNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkEmailIfValid(request.getEmail());
        });
        assertTrue(thrown.getMessage().contains("Email is required!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_EmailIfValid_ForEmailFormat() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin12343455");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkEmailIfValid(request.getEmail());
        });
        assertTrue(thrown.getMessage().contains("Invalid email format!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_NoPasswordShouldNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkPasswordIfValid(request.getPassword());
        });
        assertTrue(thrown.getMessage().contains("Password is required!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_PasswordShouldNotExceed100() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("dfjbsdfgfdghfdsajksfjkafsjkasdfjksdf786234762347698rcgdfsggdfsghasfdhgasdfhggasdfgfsdhgfdshgasdu7yuiydfydjfshhfdsusfdyhfdsuhsduihfr");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkPasswordIfValid(request.getPassword());
        });
        assertTrue(thrown.getMessage().contains("Password length should be a maximum of 100 characters!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeShouldNotNull() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid(request.getRoleType());
        });
        assertTrue(thrown.getMessage().contains("Roletype is required!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeShouldNotExceed10() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("adminadminadmin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid(request.getRoleType());
        });
        assertTrue(thrown.getMessage().contains("Roletype length should be a maximum of 10 characters!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeAdmin() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("admin");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid(request.getRoleType());
        });
        assertTrue(thrown.getMessage().contains("Invalid roletype given!"));
    }
    @Test
    public void testAddCommunityAndAdminManager_NegativeCase_For_RoleTypeManager() throws Exception {

        Validator validator =new Validator();

        CreateCommunityAdminAndManagerRest request = new CreateCommunityAdminAndManagerRest();
        request.setName("Manager");
        request.setId(2L);
        request.setRoleType("manager");
        request.setEmail("Cognizant.com");
        request.setPassword("admin1234");
        request.setCognizantId("34254454564655");
        CommunityAdminAndManager response = new CommunityAdminAndManager();
        response.setName("Manager");
        response.setId(2L);
        response.setRoletype("Admin");
        response.setEmail("Cognizant@mail.com");
        response.setPassword("admin1234");
        response.setCognizantid("admin1234");
        when(communityAdminAndManagerService.addCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/community/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            validator.checkRoleTypeIfValid(request.getRoleType());
        });
        assertTrue(thrown.getMessage().contains("Invalid roletype given!"));
    }

}
