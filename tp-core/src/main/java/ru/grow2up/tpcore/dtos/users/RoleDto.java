package ru.grow2up.tpcore.dtos.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    @Schema(
            description = "id роли пользователя в системе",
            example = "1"
    )
    private Long roleId;

    @Schema(
            description = "Название роли пользователя в системе",
            example = "ROLE_ADMIN"
    )
    private String roleName;
}
