# Slack Bot Builder

A Java spring boot framework for developing very simple slack bots.
To get started, take a look at the slack-bot-test-bot module.

## Dependencies

Build and install the slack-bot-builder-framework dependency and include it in your bot's pom.xml.

Within slack-bot-builder-framework:

```
mvn clean install -DskipTests
```

Dependency in pom:

```xml
        <dependency>
            <groupId>com.macdonald</groupId>
            <artifactId>slack-bot-builder-framework</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```

## Usage

Currently the framework uses a `@SlackBotResponseHandler' annotation where you can register a method to handle a specific slack event. 

```java

@Component
public class TestResponseHandler
{
    
    @SlackBotResponseHandler(regexPattern = "Hello", eventTrigger = SlackEventType.MESSAGE)
    public BotResponse replyToMessage(final SlackMessage message)
    {
        final BotResponse botResponse = BotResponse.builder()
            .text("hello <@" + message.getUser() + ">, how are you today?")
            .type("message")
            .id(1)
            .user(message.getUser()).channel(message.getChannel()).build();
        return botResponse;
    }
}

```
* Classes must be annotated with '@Component' or '@Service' to be registered as beans by Spring. 
* Valid methods must have a single SlackMessage object as a parameter and nothing else.
* Valid methods must return a response within the BotResponse object
* regexPattern parameter has a default of ".*" if not specific so will match all messages.
