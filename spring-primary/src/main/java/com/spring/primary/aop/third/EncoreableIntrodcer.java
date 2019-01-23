package com.spring.primary.aop.third;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncoreableIntrodcer {
	@DeclareParents(value="com.spring.primary.aop.third.Performance+",defaultImpl=AdvertisementAbleImpl.class)
    public static AdvertisementAble advertisementAble;
}
