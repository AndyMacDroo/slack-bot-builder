package com.macdonald.slack.bot;

import com.macdonald.slack.messaging.dto.BotResponse;
import com.macdonald.slack.messaging.dto.SlackMessage;
import com.macdonald.slack.registry.InvokableMethodRegistry;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import static com.macdonald.slack.utils.InvokableMethodUtilities.getMatchingInvokableMethods;

@Log4j
@Service
public class SlackMessageProcessor
{

    private ListableBeanFactory listableBeanFactory;

    @Autowired
    public SlackMessageProcessor(ListableBeanFactory listableBeanFactory)
    {
        this.listableBeanFactory = listableBeanFactory;
    }

    List<BotResponse> processSlackMessage(final SlackMessage slackMessage)
    {
        List<BotResponse> returnedResponses = new ArrayList<>();
        InvokableMethodRegistry invokableMethodRegistry = listableBeanFactory.getBean(InvokableMethodRegistry.class);
        getMatchingInvokableMethods(slackMessage, invokableMethodRegistry.scanForInvokableMethods()).forEach(invokableMethod ->
        {
            try
            {
                if(slackMessage.getEventType().equalsIgnoreCase(invokableMethod.getEventTrigger().name().toLowerCase()))
                {
                    BotResponse response = (BotResponse) invokableMethod.getMethod().invoke(
                        listableBeanFactory.getBean(invokableMethod.getMethod().getDeclaringClass()),
                        slackMessage);

                    returnedResponses.add(response);
                }
            }
            catch(IllegalAccessException | InvocationTargetException e)
            {
                log.debug(e.getMessage(), e);
            }
        });
        return returnedResponses;
    }

}
