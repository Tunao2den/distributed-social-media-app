package com.tuna.usersservice.service;

import com.tuna.usersservice.model.dto.FollowUsersDTO;
import com.tuna.usersservice.model.dto.UsersDTO;
import com.tuna.usersservice.model.entity.FollowUsers;
import com.tuna.usersservice.model.entity.Users;
import com.tuna.usersservice.payload.request.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private FollowUsersRepository followUsersRepository;

    public List<Users> getUsers() {
        List<Users> users = usersRepository.findAll();
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
        if (usersRepository.existsByEmail(email)) {
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
        user.setPrivate(false);
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

    public ResponseEntity<?> switchToPrivateAccount(Integer userId) {
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        if (users.get().isPrivate()) {
            return ResponseEntity.badRequest().body(new MessageResponse("This account is already private"));
        }
        try {
            users.get().setPrivate(true);
            usersRepository.save(users.get());
            return ResponseEntity.ok(new MessageResponse("Account switched to private successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not update the isPrivate column to private"));
        }
    }

    public ResponseEntity<?> switchToPublicAccount(Integer userId) {
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        if (!users.get().isPrivate()) {
            return ResponseEntity.badRequest().body(new MessageResponse("This account is already public"));
        }
        try {
            users.get().setPrivate(false);
            usersRepository.save(users.get());
            return ResponseEntity.ok(new MessageResponse("Account switched to public successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not update the isPrivate column to public"));
        }
    }

    @Transactional
    public ResponseEntity<?> sendFollowRequest(FollowUserRequest followUserRequest) {
        Integer followerId = followUserRequest.getFollowerId();
        Integer followedId = followUserRequest.getFollowedId();
        if (followerId.equals(followedId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("User can not send a follow request to itself"));
        }
        Optional<Users> followerUser = usersRepository.findById(followerId);
        Optional<Users> followedUser = usersRepository.findById(followedId);
        if (followerUser.isEmpty() || followedUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        if (followUsersRepository.existsByFollowerIdAndFollowedId(followerId, followedId)) {
            FollowUsers followUsers = followUsersRepository.findByFollowerIdAndFollowedId(followerId, followedId);
            if (followUsers.isRequest()) {
                return ResponseEntity.badRequest().body(new MessageResponse(followerUser.get().getUserName() + " is already sent follow request to " + followedUser.get().getUserName()));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse(followerUser.get().getUserName() + " is already following " + followedUser.get().getUserName()));
            }
        }
        if (followedUser.get().isPrivate()) {
            FollowUsers followUsers = new FollowUsers();
            followUsers.setFollower(followerUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
            followUsers.setFollowed(followedUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
            followUsers.setRequest(true);
            followUsersRepository.save(followUsers);
            return ResponseEntity.ok(new MessageResponse("Follow request sent successfully"));
        } else {
            FollowUsers followUsers = new FollowUsers();
            followUsers.setFollower(followerUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
            followUsers.setFollowed(followedUser.orElseThrow(() -> new RuntimeException("Follower user not found")));
            followUsersRepository.save(followUsers);
            return ResponseEntity.ok(new MessageResponse("User followed successfully"));
        }
    }

    public ResponseEntity<?> getFollowRequests(Integer userId) {
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        try {
            List<FollowUsers> followUsers = followUsersRepository.findAllByFollowedAndIsRequest(user.get(), true);
            List<FollowUsersDTO> followUsersDTOS = new ArrayList<>();
            for (FollowUsers followUser : followUsers) {
                FollowUsersDTO followUsersDTO = new FollowUsersDTO();
                followUsersDTOS.add(followUsersDTO.toFollowUsersDTO(followUser));
            }
            return ResponseEntity.ok(followUsersDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not fetch follow requests"));
        }
    }

    public ResponseEntity<?> acceptFollowRequest(HandleFollowingRequest handleFollowingRequest) {
        Integer followUsersId = handleFollowingRequest.getFollowUserId();
        Integer userId = handleFollowingRequest.getUserId();
        Optional<FollowUsers> followUsers = followUsersRepository.findById(followUsersId);
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        if (followUsers.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Follow request not found"));
        }
        if (!followUsers.get().isRequest()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Follow request already accepted"));
        }
        if (!followUsers.get().getFollowed().getId().equals(userId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("This user is not the receiver of this request"));
        }
        try {
            followUsers.get().setRequest(false);
            followUsersRepository.save(followUsers.get());
            return ResponseEntity.ok(new MessageResponse("Follow request accepted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not update isRequest column to false"));
        }
        // TODO: 21.12.2024 send notification to the request sender
    }

    public ResponseEntity<?> declineFollowRequest(HandleFollowingRequest handleFollowingRequest) {
        Integer followUsersId = handleFollowingRequest.getFollowUserId();
        Integer userId = handleFollowingRequest.getUserId();
        Optional<FollowUsers> followUsers = followUsersRepository.findById(followUsersId);
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
        if (followUsers.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Follow request not found"));
        }
        if (!followUsers.get().isRequest()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Follow request already accepted"));
        }
        if (!followUsers.get().getFollowed().getId().equals(userId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("This user is not the receiver of this request"));
        }
        try {
            followUsersRepository.delete(followUsers.get());
            return ResponseEntity.ok(new MessageResponse("Follow request declined"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Could not fetch follow requests"));
        }
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

        List<UsersDTO> recommendedUserDTOs = mutualFollowedUsersFollowings.stream()
                .filter(user -> !alreadyFollowingUserIds.contains(user.getId()) && !user.getId().equals(id))
                .map(user -> new UsersDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName()))
                .toList();

        return ResponseEntity.ok(recommendedUserDTOs);
    }

    public ResponseEntity<?> searchUsersByUsername(String username) {
        return ResponseEntity.ok(usersRepository.searchByUserName(username));
    }
}
