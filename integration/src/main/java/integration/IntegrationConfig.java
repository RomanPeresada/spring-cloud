package integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfig {
    public static final String INPUT_DIR = "C:\\Users\\rperes\\IdeaProjects\\spring-cloud\\integration\\in";
    public static final String OUTPUT_DIR = "C:\\Users\\rperes\\IdeaProjects\\spring-cloud\\integration\\out";

    @Autowired
    private Transformer transformer;


    @Bean
    public FileWritingMessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;
    }

    @Bean
    public FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(INPUT_DIR));
        return fileReadingMessageSource;
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows.from(fileReadingMessageSource(), conf ->
                conf.poller(Pollers.fixedDelay(2000)))
                .transform(transformer, "modifier")
                .handle(fileWritingMessageHandler())
                .get();
    }


}
