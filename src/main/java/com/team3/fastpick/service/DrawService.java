package com.team3.fastpick.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.team3.fastpick.dto.response.DrawResponse;

@Service
public class DrawService {
    private static final String ATTEMPT_PREFIX = "draw:";             // 응모 기록
    private static final String COUNT_PREFIX = "draw_count:";         // 응모 카운트
    private static final String USER_SET_PREFIX = "draw_users:";       // 유저 중복 방지용
    private static final int MAX_COUNT = 3;

    private final RedisTemplate<String, Object> redisTemplate;

    public DrawService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public DrawResponse requestDraw(int productId, int userId) {
        
        String userSetKey = buildUserSetKey(productId);
        Boolean alreadyParticipated = redisTemplate.opsForSet().isMember(userSetKey, userId);

        if (Boolean.TRUE.equals(alreadyParticipated)) {
            return new DrawResponse(false, "이미 응모된 사용자입니다.");
        }
        
        String countKey = buildCountKey(productId);
        Long currentCount = incrementCount(countKey);

        if (currentCount > MAX_COUNT) {
            decrementCount(countKey);
            return new DrawResponse(false, "마감됐습니다.");
        }

        saveUserAttempt(productId, currentCount, userId);
        
        // 4. 중복 방지를 위해 유저 ID 저장
        recordUserParticipation(userSetKey, userId); // TTL 설정은 필요시 추가
        return new DrawResponse(true, "당첨됐습니다.");
    }

    private String buildCountKey(int productId) {
        return COUNT_PREFIX + productId;
    }
    
    private String buildUserSetKey(int productId) {
        return USER_SET_PREFIX + productId;
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
    
    private void recordUserParticipation(String userSetKey, int userId) {
        redisTemplate.opsForSet().add(userSetKey, userId);
    }
}



