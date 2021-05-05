package inflearn.spring_introduction.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {
    // SpringConfig로 등록시 자기자신 순환참조 방지하기 위해 !target(inflearn.spring_introduction.SpringConfig) 추가
    @Around("execution(* inflearn.spring_introduction..*(..))&& !target(inflearn.spring_introduction.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString() + "\n");

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms"+ "\n");
        }
    }
}
