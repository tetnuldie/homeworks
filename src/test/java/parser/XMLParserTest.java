package parser;

import org.testng.Assert;
import org.testng.annotations.*;
import utilities.ListADT;

@Listeners(ParserTestListener.class)
public class XMLParserTest {
    static ListADT<String> errors;
    @Test
    @Parameters({"validFile"})
    public void testValidParser(String filePath){
        XMLParser parser = new XMLParser(filePath);
        parser.parseDocument();
        errors = parser.getErrors();
        Assert.assertTrue(parser.getErrors().isEmpty(), "Failed, errors shown in valid file");
    }

    @Test
    @Parameters({"inValidFile"})
    public void testInValidParser(String filePath){
        XMLParser parser = new XMLParser(filePath);
        parser.parseDocument();
        errors = parser.getErrors();
        Assert.assertFalse(parser.getErrors().isEmpty(), "Failed, corrupted file shown as valid");
    }
}
