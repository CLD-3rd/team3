package com.team3.fastpick.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.team3.fastpick.dto.response.DrawResponse;

@Service
public class DrawService {
    private static final String ATTEMPT_PREFIX = "draw:";             // 응모 기록
    private static final String COUNT_PREFIX = "draw_count:";         // 응모 카운트
    private static final int MAX_COUNT = 10;

    private final RedisTemplate<String, Object> redisTemplate;

    public DrawService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public DrawResponse requestDraw(int productId, int userId) {
        String countKey = buildCountKey(productId);
        Long currentCount = incrementCount(countKey);

        if (currentCount > MAX_COUNT) {
            decrementCount(countKey);
            return new DrawResponse(false, "이미 해당 상품에 대한 응모 횟수 한도를 초과했습니다.");
        }

        saveUserAttempt(productId, currentCount, userId);
        return new DrawResponse(true, "응모 성공. 현재 전체 응모 수: " + currentCount);
    }

    private String buildCountKey(int productId) {
        return COUNT_PREFIX + productId;
    }

    private Long incrementCount(String countKey) {
        return redisTemplate.opsForValue().increment(countKey);
    }

    private void decrementCount(String countKey) {
        redisTemplate.opsForValue().decrement(countKey);
    }

    private void saveUserAttempt(int productId, Long attemptNumber, int userId) {
        String attemptKey = ATTEMPT_PREFIX + productId + ":" + attemptNumber;
        redisTemplate.opsForValue().set(attemptKey, String.valueOf(userId));
    }
}



