package com.devsuperior.bds03.dto;

import com.devsuperior.bds03.entities.User;
import com.devsuperior.bds03.services.validation.UserInsertValid;

import javax.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @Size(min = 6, message = "Senha deve conter no minimo 6 caracteres")
    private String password;

    public UserInsertDTO (){}

    public UserInsertDTO(User entity) {
        super(entity);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
