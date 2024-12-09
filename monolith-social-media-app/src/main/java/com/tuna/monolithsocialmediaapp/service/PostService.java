package com.tuna.monolithsocialmediaapp.service;

import com.tuna.monolithsocialmediaapp.model.entity.DailyPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPost;
import com.tuna.monolithsocialmediaapp.model.entity.MasterPostCategory;
import com.tuna.monolithsocialmediaapp.model.entity.Users;
import com.tuna.monolithsocialmediaapp.payload.request.CreateMasterPostRequest;
import com.tuna.monolithsocialmediaapp.payload.request.MasterPostCategoryRequest;
import com.tuna.monolithsocialmediaapp.repository.DailyPostRepository;
import com.tuna.monolithsocialmediaapp.repository.MasterPostCategoryRepository;
import com.tuna.monolithsocialmediaapp.repository.MasterPostRepository;
import com.tuna.monolithsocialmediaapp.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private MasterPostCategoryRepository masterPostCategoryRepository;

    @Autowired
    private MasterPostRepository masterPostRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DailyPostRepository dailyPostRepository;

    public List<MasterPostCategory> getAllCategories() {
        return masterPostCategoryRepository.findAll();
    }

    @Transactional
    public MasterPostCategory addNewCategory(MasterPostCategoryRequest masterPostCategoryRequest) {
        String category = masterPostCategoryRequest.getCategory();
        if (category.isEmpty() || category.isBlank()) {
            throw new RuntimeException("Category name can not be null: " + category);
        }
        if (masterPostCategoryRepository.existsByCategory(category)) {
            throw new RuntimeException(category + " category already exists");
        }
        MasterPostCategory masterPostCategory = new MasterPostCategory();
        masterPostCategory.setCategory(category);
        return masterPostCategoryRepository.save(masterPostCategory);
    }

    public List<MasterPost> getAllMasterPosts() {
        return masterPostRepository.findAll();
    }

    public List<MasterPost> getMasterPostsByUsername(String userName) {
        Users user = usersRepository.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("User does not exists with username: " + userName);
        }
        return masterPostRepository.findByUser(user);
    }

    @Transactional
    public MasterPost addNewMasterPost(CreateMasterPostRequest createMasterPostRequest) {
        Users user = usersRepository.getReferenceById(createMasterPostRequest.getUserId());
        MasterPostCategory postCategory = masterPostCategoryRepository.getReferenceById(createMasterPostRequest.getMasterPostCategoryId());
        String username = user.getUserName();
        String category = postCategory.getCategory();

        if (!usersRepository.existsByUserName(username)) {
            throw new RuntimeException("User does not exists with username: " + username);
        }
        if (!masterPostCategoryRepository.existsByCategory(category)) {
            throw new RuntimeException("Category does not exists with category name: " + username);
        }

        MasterPost masterPost = new MasterPost();
        masterPost.setUser(user);
        masterPost.setMasterPostCategory(postCategory);
        masterPost.setContent(createMasterPostRequest.getContent());
        masterPost.setCurrentStreak(0);
        masterPost.setCreatedAt(LocalDateTime.now());

        return masterPostRepository.save(masterPost);
    }


    @Transactional
    public MasterPost addNewMasterPostToUser(String userName, CreateMasterPostRequest createMasterPostRequest) {
        Users user = usersRepository.findByUserName(userName);
        MasterPostCategory postCategory = masterPostCategoryRepository.getReferenceById(createMasterPostRequest.getMasterPostCategoryId());
        String category = postCategory.getCategory();
        if (!usersRepository.existsByUserName(userName)) {
            throw new RuntimeException("User does not exists with username: " + userName);
        }
        if (!masterPostCategoryRepository.existsByCategory(category)) {
            throw new RuntimeException("Category does not exists with category name: " + userName);
        }
        MasterPost masterPost = new MasterPost();
        masterPost.setUser(user);
        masterPost.setMasterPostCategory(postCategory);
        masterPost.setContent(createMasterPostRequest.getContent());
        masterPost.setCurrentStreak(0);
        masterPost.setCreatedAt(LocalDateTime.now());

        return masterPostRepository.save(masterPost);
    }

    public List<DailyPost> getAllDailyPosts() {
        return dailyPostRepository.findAll();
    }
}
