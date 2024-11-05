package com.market_hub.kernel.master.user.application.implementations;

import com.market_hub.kernel.master.global.domain.dtos.token.TokenDto;
import com.market_hub.kernel.master.global.domain.dtos.user.UserRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.user.UserResponseDto;
import com.market_hub.kernel.master.global.domain.ports.DaoCrudPort;
import com.market_hub.kernel.master.global.infraestructure.advicers.exceptions.ResourceAlreadyExists;
import com.market_hub.kernel.master.role.domain.ports.RolePort;
import com.market_hub.kernel.master.role.infraestructure.model.Role;
import com.market_hub.kernel.master.user.application.usecases.UserService;
import com.market_hub.kernel.master.user.application.usecases.UserServiceBasicOperations;
import com.market_hub.kernel.master.user.domain.ports.currentInterfaces.UserDaoPort;
import com.market_hub.kernel.master.user.infraestructure.model.User;

import com.market_hub.kernel.master.utils.ThrowableActions;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserServiceBasicOperations {

    private final RolePort rolePort;

    private final PasswordEncoder passwordEncoder;

    private final UserDaoPort userDaoPort;

    private final DaoCrudPort<User> userDaoCrudPort;

    public UserServiceImpl(RolePort rolePort, PasswordEncoder passwordEncoder,UserDaoPort userDaoPort,DaoCrudPort<User> userDaoCrudPort){
        this.rolePort = rolePort;
        this.passwordEncoder = passwordEncoder;
        this.userDaoPort = userDaoPort;
        this.userDaoCrudPort = userDaoCrudPort;
    }

    @Override
    public UserResponseDto search(Long id) {
            return findUserById(id)
                    .map(this::convertToDto)
                    .orElseThrow();
    }


    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user;
        List<Role> roles;
        Boolean isAdmin = false;
        Optional.ofNullable(userRequestDto.email())
                .flatMap(this::findUserByEmail)
                .ifPresent((userOpt)->
                        ThrowableActions.launchRuntimeExeption(
                                ()-> new ResourceAlreadyExists("Error user with email: "+userOpt.getEmail()+"already exits")
                        ));

        roles = userRequestDto.roles()
                .stream()
                .map(role -> rolePort.findRoleByName(role.getName()))
                .filter(Optional::isPresent)
                .map(roleOpt-> (Role) roleOpt.get())
                .toList();
        String passwordEncoded = passwordEncoder.encode(userRequestDto.password());

        user = new User(null,
                userRequestDto.name(),
                userRequestDto.username(),
                userRequestDto.identification(),
                userRequestDto.email(),
                passwordEncoded,
                null,
                roles,
                isAdmin,
                true);


        return Optional.ofNullable(create(user))
                .map(this::convertToDto)
                .orElseThrow();
    }

    @Override
    public UserResponseDto update(UserRequestDto object) {
        User user = new User();
        user.setName(object.name());
        user.setPassword(object.password());
        user.setUsername(object.username());
        user.setRoles(object.roles());

        return  Optional.ofNullable(update(user))
                .map(this::convertToDto)
                .orElseThrow();
    }

    @Override
    public void activeDelete(Long id) {

    }

    @Override
    public UserResponseDto convertToDto(User user) {
        UserRequestDto userRequestDto = new UserRequestDto(
                user.getId(),
                user.getUsername(),
                user.getIdentification(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.isAdmin(),
                user.getRoles());


        return new UserResponseDto(userRequestDto,new TokenDto(user.getSesionToken()));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDaoPort.findByEmail(email)
                .map(objUser-> (User) objUser);
    }

   public Optional<User> findUserById(Long id){
        return userDaoCrudPort.get(id);
   }

    @Override
    public User update(User user) {
        return userDaoCrudPort.update(user)
                .orElseThrow();
    }

    @Override
    public User create(User user) {
        return userDaoCrudPort.create(user)
                .orElseThrow();
    }
}
