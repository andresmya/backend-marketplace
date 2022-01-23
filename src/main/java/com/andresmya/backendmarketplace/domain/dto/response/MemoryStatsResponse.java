package com.andresmya.backendmarketplace.domain.dto.response;

import lombok.Data;

@Data
public class MemoryStatsResponse {
    private double heapSize;
    private double heapMaxSize;
    private double heapFreeSize;
}
