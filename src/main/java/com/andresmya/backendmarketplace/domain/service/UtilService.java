package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.dto.response.MemoryStatsResponse;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
    public MemoryStatsResponse getMemoryStats() {
        MemoryStatsResponse stats = new MemoryStatsResponse();
        stats.setHeapSize((double) Runtime.getRuntime().totalMemory() / (double) (1024 * 1024));
        stats.setHeapMaxSize((double) Runtime.getRuntime().maxMemory() / (double) (1024 * 1024));
        stats.setHeapFreeSize((double) Runtime.getRuntime().freeMemory() / (double) (1024 * 1024));
        return stats;
    }
}
