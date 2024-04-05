package ru.lexp00.platform.tpusers.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lexp00.platform.tpcommon.dtos.users.RoleDto;
import ru.lexp00.platform.tpusers.entities.Role;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
    }
}
