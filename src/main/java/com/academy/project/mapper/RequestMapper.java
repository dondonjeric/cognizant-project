package com.academy.project.mapper;


import com.academy.project.dto.CreateCommunityAdminAndManagerRest;
import com.academy.project.dto.DeleteCommunityAdminAndManagerRest;
import com.academy.project.dto.GetActiveCommunityAdminAndManagerRest;
import com.academy.project.dto.UpdateCommunityAdminAndManagerRest;
import com.academy.project.model.CommunityAdminAndManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper extends ModelMapper {

    public RequestMapper(){
        this.addMappings(updateMapper());
        this.addMappings(createMapper());
        this.addMappings(getAllActiveMapper());
        this.addMappings(deleteMapper());
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
            }
        };
    }

    private PropertyMap<CommunityAdminAndManager, GetActiveCommunityAdminAndManagerRest> getAllActiveMapper(){
        return new PropertyMap<CommunityAdminAndManager, GetActiveCommunityAdminAndManagerRest>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setCognizantId(source.getCognizantId());
                map().setEmail(source.getEmail());
                map().setRoleType(source.getRoleType());
            }
        };
    }

    private PropertyMap<DeleteCommunityAdminAndManagerRest, CommunityAdminAndManager> deleteMapper(){
        return new PropertyMap<DeleteCommunityAdminAndManagerRest, CommunityAdminAndManager>() {
            @Override
            protected void configure() {
                map().setIsactive(source.isIsactive());
            }
        };
    }

}
