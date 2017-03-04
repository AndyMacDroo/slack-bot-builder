package com.macdonald.slack.registry;

import com.macdonald.slack.annotation.SlackBotResponseHandler;
import com.macdonald.slack.method.dto.InvokableMethod;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class InvokableMethodRegistry
{

    private AbstractApplicationContext applicationContext;

    @Autowired
    public InvokableMethodRegistry(AbstractApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public List<InvokableMethod> scanForInvokableMethods()
    {
        List<InvokableMethod> invokableMethodList = new ArrayList<>();
        for(String springBean : applicationContext.getBeanDefinitionNames())
        {
            Method[] methods = AopUtils.getTargetClass(applicationContext.getBean(springBean)).getMethods();

            for(Method method : methods)
            {
                if(method.isAnnotationPresent(SlackBotResponseHandler.class))
                {
                    InvokableMethod.InvokableMethodBuilder invokableMethod = InvokableMethod.builder()
                        .method(method)
                        .parameterClassArray(method.getParameterTypes())
                        .eventTrigger(method.getAnnotation(SlackBotResponseHandler.class).eventTrigger())
                        .regexPattern(method.getAnnotation(SlackBotResponseHandler.class).regexPattern());

                    invokableMethodList.add(invokableMethod.build());
                }
            }
        }
       return invokableMethodList;
    }

}
