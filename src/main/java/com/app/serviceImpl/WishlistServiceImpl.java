package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddWishlistRequest;
import com.app.dto.response.WishlistResponse;
import com.app.entity.PG;
import com.app.entity.User;
import com.app.entity.Wishlist;
import com.app.repository.PGRepository;
import com.app.repository.UserRepository;
import com.app.repository.WishlistRepository;
import com.app.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final PGRepository pgRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserRepository userRepository,
                               PGRepository pgRepository) {

        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public WishlistResponse addToWishlist(AddWishlistRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        // Prevent duplicate wishlist
        wishlistRepository.findByUser_IdAndPg_Id(
                request.getUserId(),
                request.getPgId())
                .ifPresent(w -> {
                    throw new RuntimeException("PG already added to wishlist");
                });

        Wishlist wishlist = new Wishlist();

        wishlist.setUser(user);
        wishlist.setPg(pg);
        wishlist.setCreatedAt(LocalDateTime.now());

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        return mapToResponse(savedWishlist);
    }

    @Override
    public List<WishlistResponse> getUserWishlist(Long userId) {

        return wishlistRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removeFromWishlist(Long id) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        wishlistRepository.delete(wishlist);
    }

    private WishlistResponse mapToResponse(Wishlist wishlist) {

        WishlistResponse response = new WishlistResponse();

        response.setId(wishlist.getId());

        response.setUserId(wishlist.getUser().getId());

        response.setUserName(
                wishlist.getUser().getFirstName() + " "
                        + wishlist.getUser().getLastName());

        response.setPgId(wishlist.getPg().getId());

        response.setPgName(wishlist.getPg().getPgName());

        response.setCreatedAt(wishlist.getCreatedAt());

        return response;
    }
}