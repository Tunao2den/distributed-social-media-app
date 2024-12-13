package com.tuna.usersservice.service;

import com.tuna.usersservice.model.dto.FollowingUserDTO;
import com.tuna.usersservice.model.entity.FollowUsers;
import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.payload.request.FollowUserRequest;
import com.tuna.usersservice.payload.request.LoginRequest;
import com.tuna.usersservice.payload.request.RegisterRequest;
import com.tuna.usersservice.payload.request.UserInfoRequest;
import com.tuna.usersservice.payload.response.LoginResponse;
import com.tuna.usersservice.payload.response.MessageResponse;
import com.tuna.usersservice.repository.FollowUsersRepository;
import com.tuna.usersservice.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private FollowUsersRepository followUsersRepository;

    public List<Users> getUsers() {
        List<Users> users= usersRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }
        return users;
    }

    public Users getUserByName(String userName) {
        if (!usersRepository.existsByUserName(userName)) {
            throw new RuntimeException("User not found");
        }
        return usersRepository.findByUserName(userName);
    }

    @Transactional
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        String firstName = registerRequest.getFirstName();
        String lastName = registerRequest.getLastName();
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        LocalDate birthDate = registerRequest.getBirthDate();
        if (usersRepository.existsByUserName(username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if(usersRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }
        // TODO: 12.12.2024 encode password(spring security)
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        user.setRegisteredAt(LocalDateTime.now());
        usersRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Users user = usersRepository.findUsersByUserNameAndPassword(username, password);
        if (user != null) {
            return ResponseEntity.ok(new LoginResponse(user.getId(), user.getUserName()));
        } else {
            return ResponseEntity.ok(new MessageResponse("Login failed!"));
        }
    }

    @Transactional
    public ResponseEntity<?> sendFollowRequest(FollowUserRequest followUserRequest) {
        Integer followerId = followUserRequest.getFollowerId();
        Integer followedId = followUserRequest.getFollowedId();
        Optional<Users> followerUser = usersRepository.findById(followerId);
        Optional<Users> followedUser = usersRepository.findById(followedId);
        if (followUsersRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(followerUser.get().getUserName() + " is already following " + followedUser.get().getUserName())
            );
        }
        FollowUsers followUsers = new FollowUsers();
        followUsers.setFollower(followerUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
        followUsers.setFollowed(followedUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
        followUsersRepository.save(followUsers);
        return ResponseEntity.ok(new MessageResponse("User followed successfully"));
    }

    public ResponseEntity<?> getFollowers(UserInfoRequest userInfoRequest) {
        Optional<Users> users = usersRepository.findById(userInfoRequest.getId());
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        return ResponseEntity.ok(usersRepository.findFollowersByUserId(userInfoRequest.getId()));
    }

    public ResponseEntity<?> getFollowedUsers(UserInfoRequest userInfoRequest) {
        Optional<Users> users = usersRepository.findById(userInfoRequest.getId());
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        return ResponseEntity.ok(usersRepository.findFollowedUsersById(userInfoRequest.getId()));
    }

    public ResponseEntity<?> recommendUsers(UserInfoRequest userInfoRequest) {
        Integer id = userInfoRequest.getId();
        List<Integer> mutualFollowedIds = followUsersRepository.findMutualFollowingIds(id);
        List<Users> mutualFollowedUsersFollowings = followUsersRepository.findFollowedUsers(mutualFollowedIds);
        List<Integer> alreadyFollowingUserIds = followUsersRepository.findAlreadyFollowingUserIds(id);

        List<FollowingUserDTO> recommendedUserDTOs = mutualFollowedUsersFollowings.stream()
                .filter(user -> !alreadyFollowingUserIds.contains(user.getId()) && !user.getId().equals(id))
                .map(user -> new FollowingUserDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName()))
                .toList();

        return ResponseEntity.ok(recommendedUserDTOs);
    }
}
