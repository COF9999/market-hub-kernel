package com.market_hub.kernel.master.user.infraestructure.rest.controllers;


import com.market_hub.kernel.master.global.domain.dtos.user.UserRequestDto;
import com.market_hub.kernel.master.global.domain.dtos.user.UserResponseDto;
import com.market_hub.kernel.master.user.application.usecases.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControllerRest {

    /*
    private ServiceCrud<UserResponseDto UserResponseDto> userImplCrud;


    private UserService userImplService;

    private PointsService pointsService;

     */

    private final UserService userService;

    public UserControllerRest(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id){
        return userService.search(id);
    }


    @PutMapping("/")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto){
        return userService.update(userRequestDto);
    }

    @DeleteMapping("/{id}")
    public UserResponseDto deleteUser(@PathVariable Long id){
        return null;
    }

    /*

    Cambiar
    @GetMapping("/my-products/id")
    public List<ProductResponse> findAllmyProducts(@PathVariable Long id){
        return null;
    }

     */

    /*
    @PostMapping("/my-points")
    public PointsDto getMyPoints(@RequestBody TokenDto tokenDto){
        return null;
    }

     */
}
