package no.arktekk.training.spring.batchclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import no.arktekk.training.spring.batchclient.AuctionCreator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class JmsClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:batchclient/jms-client.xml");

        AuctionCreator creator 
        	= context.getBean(AuctionCreator.class);

        // Old school file parsing. :)
        Resource batchFile = context
                .getResource("file:src/test/java/no/arktekk/training/spring/batchclient/auctions-batch.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(batchFile.getInputStream()));

        String row;
        while ( (row = reader.readLine()) != null) {
            String[] splitRow = row.split(":");
            creator.sendAuctionMessage(splitRow[0], Double.parseDouble(splitRow[1]));
        }
        reader.close();
        
    }

}
