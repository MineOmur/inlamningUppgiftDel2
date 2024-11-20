package TextCounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TextCounterTest {

    private TextCounter textCounter;

    // Återställ TextCounter.TextCounter-objektet före varje test

    @BeforeEach
    public void setUp() {
        textCounter = new TextCounter();

        // Återställer statiska variabler
        TextCounter.totalLines = 0;
        TextCounter.totalChars = 0;
    }


    // Test 1: Korrekt beräkning av antalet rader
    @Test
    public void testLineCount() {

        // Simulera indata: 3 rader matas in
        String simulatedInput = "Hej\nDetta är en testrad.\nEn sista rad.\nstop\n";

        // Ändra tillfälligt System.in-flödet
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Beräkna antalet rader genom att bearbeta indata
        textCounter.readInput();

        // Låt oss anta att 3 rader är skrivna
        Assertions.assertEquals(3, textCounter.getTotalLines(), "Antalet rader är fel");

        // Efter testet återgår vi till det gamla System.in-värdet
        System.setIn(originalSystemIn);
    }

    // Test 2: Korrekt beräkning av antalet tecken
    @Test
    public void testCharacterCount() {

        // Simulera indata: 3 rader och teckenlängder
        String simulatedInput = "Hej\nDetta är en testrad.\nEn sista rad.\nstop\n";


        // Ändra tillfälligt System.in-flödet
        InputStream originalSystemIn = System.in; // Sparar gammal version
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        //  Beräkna antalet tecken genom att bearbeta indata
        textCounter.readInput();

        // Rader: "Hej" (3 tecken), "Detta är en testrad." (20 tecken), "En sista rad." (13 tecken)
        //  Totalt: 3 + 20 + 13 = 36
        Assertions.assertEquals(36, textCounter.getTotalChars(), "Antalet tecken är fel");

//         Efter testning återgår den till det gamla System.in-värdet
        System.setIn(originalSystemIn);
    }

    // Test 3: Korrekt bearbetning av ordet "stop"
    @Test
    public void testStopKeywordProcessing() {

        //     Simulera indata: mata in 3 rader upp till ordet "stopp"
        String simulatedInput = "Hej\nDetta är en testrad.\nEn sista rad.\nstop\n";

        //   Ändra tillfälligt System.in-flödet
        InputStream originalSystemIn = System.in; // Sparar gammal version
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Styr behandlingen av ordet "stop" genom att bearbeta indata
        textCounter.readInput();

        //När 'stop' skrivs ska 2 rader bearbetas och antalet tecken beräknas.
        Assertions.assertEquals(3, textCounter.getTotalLines(), "Antal rader efter 'stop' är fel");
        Assertions.assertEquals(36, textCounter.getTotalChars(), "Antalet tecken efter 'stop' är felaktigt");

        //Efter testning återgår den till det gamla System.in-värdet
        System.setIn(originalSystemIn);
    }
}


