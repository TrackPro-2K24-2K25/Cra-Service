package org.youcode.trackprocraservice.web.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.trackprocraservice.domain.entities.AppUser;
import org.youcode.trackprocraservice.domain.enums.AccountStatus;
import org.youcode.trackprocraservice.domain.enums.Role;
import org.youcode.trackprocraservice.service.interfaces.UserService;
import org.youcode.trackprocraservice.web.vm.User.AppUserResponseVM;
import org.youcode.trackprocraservice.web.vm.User.AppUserVM;
import org.youcode.trackprocraservice.web.vm.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<AppUserResponseVM> createUser(@RequestBody AppUserVM appUserVM) {
        AppUser user = userMapper.toEntity(appUserVM);
        AppUser createdUser = userService.createUser(user);
        AppUserResponseVM response = userMapper.toResponseVM(createdUser);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userId}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a user by their unique ID")
    @ApiResponse(responseCode = "200", description = "User found successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> getUserById(@PathVariable UUID userId) {
        AppUser user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        AppUserResponseVM response = userMapper.toResponseVM(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all users with pagination", description = "Retrieve a paginated list of all users")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> getAllUsers(Pageable pageable) {
        Page<AppUser> users = userService.getAllUsers(pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user by ID", description = "Update an existing user with the provided details")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> updateUser(@PathVariable UUID userId, @RequestBody AppUserVM appUserVM) {
        AppUser user = userMapper.toEntity(appUserVM);
        AppUser updatedUser = userService.updateUser(userId, user);
        AppUserResponseVM response = userMapper.toResponseVM(updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user by ID", description = "Delete a user by their unique ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/password")
    @Operation(summary = "Update a user's password", description = "Update the password of an existing user")
    @ApiResponse(responseCode = "200", description = "Password updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> updateUserPassword(@PathVariable UUID userId, @RequestParam String newPassword) {
        AppUser user = userService.updateUserPassword(userId, newPassword);
        AppUserResponseVM response = userMapper.toResponseVM(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/verify-password")
    @Operation(summary = "Verify a user's password", description = "Verify if the provided password matches the user's current password")
    @ApiResponse(responseCode = "200", description = "Password verification successful")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Boolean> verifyPassword(@PathVariable UUID userId, @RequestParam String rawPassword) {
        boolean isValid = userService.verifyPassword(userId, rawPassword);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/{userId}/lock")
    @Operation(summary = "Lock a user's account", description = "Lock the account of an existing user")
    @ApiResponse(responseCode = "200", description = "Account locked successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> lockUserAccount(@PathVariable UUID userId) {
        AppUser user = userService.lockUserAccount(userId);
        AppUserResponseVM response = userMapper.toResponseVM(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/unlock")
    @Operation(summary = "Unlock a user's account", description = "Unlock the account of an existing user")
    @ApiResponse(responseCode = "200", description = "Account unlocked successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> unlockUserAccount(@PathVariable UUID userId) {
        AppUser user = userService.unlockUserAccount(userId);
        AppUserResponseVM response = userMapper.toResponseVM(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/account-status")
    @Operation(summary = "Update a user's account status", description = "Update the account status of an existing user")
    @ApiResponse(responseCode = "200", description = "Account status updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<AppUserResponseVM> updateAccountStatus(@PathVariable UUID userId, @RequestParam AccountStatus accountStatus) {
        AppUser user = userService.updateAccountStatus(userId, accountStatus);
        AppUserResponseVM response = userMapper.toResponseVM(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/role/{role}")
    @Operation(summary = "Find users by role with pagination", description = "Retrieve a paginated list of users filtered by role")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> findUsersByRole(@PathVariable Role role, Pageable pageable) {
        Page<AppUser> users = userService.findUsersByRole(role, pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Find users by city with pagination", description = "Retrieve a paginated list of users filtered by city")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> findUsersByCity(@PathVariable String city, Pageable pageable) {
        Page<AppUser> users = userService.findUsersByCity(city, pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/country/{country}")
    @Operation(summary = "Find users by country with pagination", description = "Retrieve a paginated list of users filtered by country")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> findUsersByCountry(@PathVariable String country, Pageable pageable) {
        Page<AppUser> users = userService.findUsersByCountry(country, pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account-status/{accountStatus}")
    @Operation(summary = "Find users by account status with pagination", description = "Retrieve a paginated list of users filtered by account status")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> findUsersByAccountStatus(@PathVariable AccountStatus accountStatus, Pageable pageable) {
        Page<AppUser> users = userService.findUsersByAccountStatus(accountStatus, pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/last-login")
    @Operation(summary = "Find users by last login date range with pagination", description = "Retrieve a paginated list of users filtered by last login date range")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<Page<AppUserResponseVM>> findUsersByLastLoginDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            Pageable pageable) {
        Page<AppUser> users = userService.findUsersByLastLoginDateRange(startDate, endDate, pageable);
        Page<AppUserResponseVM> response = users.map(userMapper::toResponseVM);
        return ResponseEntity.ok(response);
    }


}
