package com.team3.fastpick.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.team3.fastpick.dto.response.DrawResponse;

@Service
public class DrawService {
    private static final String KEY_PREFIX = "draw:count:";
    private static final int MAX_COUNT = 10;

    private final RedisTemplate<String, Integer> redis;

    public DrawService(RedisTemplate<String, Integer> redis) {
        this.redis = redis;
    }

    public DrawResponse enterDraw(int pidx, int uidx) {
        String key = KEY_PREFIX + uidx;
        // Redis의 INCR (opsForValue().increment) 로 원자적 증분
        Long newCount = redis.opsForValue().increment(key, 1);

        if (newCount > MAX_COUNT) {
            // 한도 초과하면 되돌리기
            redis.opsForValue().decrement(key, 1);
            return new DrawResponse(false,
                    "이미 응모 횟수 10회를 모두 사용하셨습니다.");
        }
		return new DrawResponse(true, "응모가 성공적으로 완료되었습니다.");
    }
}
