package ru.lexp00.platform.tpusers.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lexp00.platform.tpcommon.dtos.users.RoleDto;
import ru.lexp00.platform.tpcommon.exceptions.ResourceNotFoundException;
import ru.lexp00.platform.tpusers.entities.Role;
import ru.lexp00.platform.tpusers.mappers.RoleMapper;
import ru.lexp00.platform.tpusers.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    public List<RoleDto> findAllWithoutAdmin() {
        List<Role> roles = roleRepository.findAll();
        roles.remove(findByName(ROLE_SUPERADMIN));
        return roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find role with id: " + id));
        return roleMapper.toDto(role);
    }

    public Role findByName (String name) {
        return roleRepository.findRoleByRoleName(name);
    }

}
