package com.tuna.usersservice.model.dto;

import com.tuna.usersservice.model.entity.FollowUsers;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FollowUsersDTO {
    private Integer id;
    private UsersDTO follower;
    private UsersDTO followed;
    private boolean isRequest;

    public FollowUsersDTO toFollowUsersDTO(FollowUsers followUsers) {
        FollowUsersDTO followUsersDTO = new FollowUsersDTO();
        followUsersDTO.setId(followUsers.getId());
        followUsersDTO.setFollower(new UsersDTO().toFollowingUserDTO(followUsers.getFollower()));
        followUsersDTO.setFollowed(new UsersDTO().toFollowingUserDTO(followUsers.getFollowed()));
        followUsersDTO.setRequest(followUsers.isRequest());
        return followUsersDTO;
    }
}
