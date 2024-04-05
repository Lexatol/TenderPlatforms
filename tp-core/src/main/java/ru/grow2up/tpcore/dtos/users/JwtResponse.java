package ru.grow2up.tpcore.dtos.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

// Токен отправляется клиенту в виде json
@Data
@AllArgsConstructor
public class JwtResponse {
    @Schema(
            description = "Токен",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHZsYXBAbWFpbC5ydSIsInJvbGVzIjpbXSwiZXhwIjoxNjYxNTEyNTEyLCJpYXQiOjE2NjE0MjYxMTJ9.xgj0B10cl0OjSDBjvhgEodliVz689fqg7XFIAZJwnrw"
    )
    private String token;

    private UUID idUser;


}
