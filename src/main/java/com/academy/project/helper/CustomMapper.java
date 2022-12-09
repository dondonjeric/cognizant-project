package com.academy.project.helper;


import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.GetAllActiveCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.model.CommunityAdminAndManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper extends ModelMapper {

    public CustomMapper(){
        this.addMappings(updateMapper());
        this.addMappings(createMapper());
        this.addMappings(getMapper());
    }

    private PropertyMap<CreateCommunityAdminAndManagerRest, CommunityAdminAndManager> createMapper(){
        return new PropertyMap<CreateCommunityAdminAndManagerRest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setCognizantId(source.getCognizantId());
                map().setRoleType(source.getRoleType());
            }
        };
    }
    private PropertyMap<UpdateCommunityAdminAndManagerRest, CommunityAdminAndManager> updateMapper(){
        return new PropertyMap<UpdateCommunityAdminAndManagerRest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setIsactive(source.getIsactive());
            }
        };
    }
    private PropertyMap<CommunityAdminAndManager, GetAllActiveCommunityAdminAndManagerRest> getMapper(){
        return new PropertyMap<CommunityAdminAndManager, GetAllActiveCommunityAdminAndManagerRest>() {
            @Override
            protected void configure() {
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setCognizantId(source.getCognizantId());
                map().setRoleType(source.getRoleType());
            }
        };
    }

}
