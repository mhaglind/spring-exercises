package no.arktekk.training.spring.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import no.arktekk.training.spring.endpoint.impl.AuctionCreator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class JmsClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:jms-client.xml");

        AuctionCreator creator = context.getBean(AuctionCreator.class);

        // Old school file parsing. :)
        Resource batchFile = context
                .getResource("file:src/test/java/no/arktekk/training/spring/jms/auctions-batch.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(batchFile.getInputStream()));

        String row;
        while ( (row = reader.readLine()) != null) {
            String[] splitRow = row.split(":");
            // TODO: Add call to creator here.
            // Hint: 
            //  creator.sendAuctionMessage(splitRow[0], Double.parseDouble(splitRow[1]));
        }
        reader.close();
        
    }

}
