package com.yeji.couponservice.application.port.out;

import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Aspect
@Component
public class OptimisticLockAspect {

    private final DatabaseLockProperties properties;

    /**
     * RETRY_MAX_COUNT만큼 반복하여 OptimisticLockException이 발생 하면 retry
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.yeji.couponservice.application.service.CouponLockService.issuanceCouponWithOptimisticLock(..))")
    public Object doOneMoreRetryTransactionIfOptimisticLockExceptionThrow(ProceedingJoinPoint joinPoint) throws Throwable {
        Exception exceptionHolder = null;
        for (int retryCount = 0; retryCount <= properties.getCount(); retryCount++) {
            try {
                log.info("[RETRY_COUNT]: {}", retryCount);
                return joinPoint.proceed();
            } catch (OptimisticLockException | ObjectOptimisticLockingFailureException | CannotAcquireLockException e) {
                log.error("{} 발생", e.getClass());
                exceptionHolder = e;
                //RETRY_WAIT_TIME ms 쉬고 다시 시도
                //for loop에서 sleep busy waiting이 된다는 경고가 뜸 무슨의미일지 찾아보자.
                //interval을 주는 다른 방법이 있을지 찾아 봐야함.
                Thread.sleep(properties.getSleep());
            }
        }
        //100번 retry했음에a도 실패하는 경우.
        throw exceptionHolder;
    }

}
