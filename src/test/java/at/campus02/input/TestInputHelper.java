package at.campus02.input;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TestInputHelper {
    @Test
    public void testValidDate() throws EOFException {
        //setup
        Scanner scanner = new Scanner("2022-03-31\n");
        InputHelper inputHelper = new InputHelper(scanner, System.out);
        //execute
        Date result = inputHelper.getDate("test");
        //verify
        Date expected = new Date(122, Calendar.MARCH,31);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void testValidDateAfterInvalidDate() throws EOFException {
        //setup
        Scanner scanner = new Scanner("31st Mar 2022\n2022-03-31\n");
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputstream,true);
        InputHelper inputHelper = new InputHelper(scanner,out);
        //execute
        Date result = inputHelper.getDate("test");
        //verify
        Date expected = new Date(122, Calendar.MARCH,31);
        Assert.assertEquals(expected,result);
        String msg = outputstream.toString(StandardCharsets.UTF_8);
        Assert.assertTrue(msg.contains("Wrong date format"));
    }
}
