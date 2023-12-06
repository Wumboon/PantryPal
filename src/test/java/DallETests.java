import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class DallETests {
    DallEMock testE = new DallEMock();

    @Test
	void testGenerateImage() {
        String testPrompt = "Pizza";
        String testedURL = "wrong";
        try {
            testedURL = DallEMock.generateImage(testPrompt);
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        } catch (URISyntaxException e) {
            
            e.printStackTrace();
        }

        String expectedRes = "https://" + testPrompt + ".com";
		assertEquals(expectedRes, testedURL);

        testPrompt = "Spaghetti";
        try {
            testedURL = DallEMock.generateImage(testPrompt);
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        } catch (URISyntaxException e) {
            
            e.printStackTrace();
        }

        expectedRes = "https://" + testPrompt + ".com";
		assertEquals(expectedRes, testedURL);
	}
}
