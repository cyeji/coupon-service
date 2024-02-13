# 쿠폰 서비스

- 프로젝트 목표

선착순 쿠폰 발급 이벤트 시 동시성 처리방식에 대해 고안하기 위해 프로젝트 개발

- 프로젝트 세팅

1. Java21
2. SpringBoot3.2.1
3. Gradle 8.5

- 선착순 이벤트 요구사항

1. 이벤트 기간 동안 매일 특정 시간 오픈하며 총지급 수량을 한정한다.
2. 쿠폰의 지급 수량은 당일 정해진 양을 초과해서는 안된다.
3. 쿠폰은 1인당 1장만 지급한다.

- 동시성 처리방식

1. synchronized
2. Database Lock (Optimistic lock, Pessimistic lock)
3. Redis (Lettuce, Redisson)

+ 재시도 패턴
