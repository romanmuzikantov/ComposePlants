package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.ProductListDto
import com.example.soplant.application.network.models.request.GetUserWallRequest
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.*

interface ProductService {
    @GET(Constants.Endpoints.ProductApi.OFFLINE_WALL)
    suspend fun getOfflineWall(): ProductListDto
    @GET(Constants.Endpoints.ProductApi.USER_WALL)
    suspend fun getUserWall(@HeaderMap headers: Map<String, String>, @Query("lastPaginationTimestamp") lastPaginationTimestamp: Long?): Response<ProductListDto>
}