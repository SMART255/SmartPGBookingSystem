package com.app.service;

import java.util.List;

import com.app.dto.request.AddWishlistRequest;
import com.app.dto.response.WishlistResponse;

public interface WishlistService {

    WishlistResponse addToWishlist(AddWishlistRequest request);

    List<WishlistResponse> getUserWishlist(Long userId);

    void removeFromWishlist(Long id);

}