package at.campus02.input;

import at.campus02.exchange.ExchangeRates;
import at.campus02.exchange.ExchangeRatesAPI;
import at.campus02.storage.Database;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class TestItemInput {
    @Test
    public void testViewItem() throws IOException {
        //setup
        InputHelper inputMock = mock(InputHelper.class);
        ExchangeRatesAPI apiMock = mock(ExchangeRatesAPI.class);
        when(inputMock.getItemId(InputHelper.ID_MUST_EXIST))
                .thenReturn(1);
        when(apiMock.fromAPI()).thenReturn(new ExchangeRates(
                BigDecimal.valueOf(2), //usd -> eur
                BigDecimal.valueOf(3), //usd -> gbp
                BigDecimal.valueOf(5), //usd -> cad
                BigDecimal.valueOf(7)  //usd -> jpy
        ));
        PrintStream outMock = mock(PrintStream.class);
        ItemInput itemInput = new ItemInput(inputMock, outMock, apiMock);
        Database.setupSampleDatabase();

        //execution
        itemInput.viewItem();


        //verification (brittle)
        verify(outMock, times(1)).println(
                "\tPrice [GBP]: 2.85" +
                "\n\tPrice [USD]: 0.95" +
                "\n\tPrice [CAD]: 4.75" +
                "\n\tPrice [JPY]: 6.65" +
                "\n");

        //verification (less brittle)
        verify(outMock, times(1)).println(
                matches("GBP.+2\\.85")
        );
        verify(outMock, times(1)).println(
                matches("JPY.+6\\.65")
        );

        //verify
        verify(apiMock, times(1)).fromAPI();
    }

    @Test
    public void removeItem() throws EOFException {
        //setup
        InputHelper inputMock = mock(InputHelper.class);
        when(inputMock.getItemId(InputHelper.ID_MUST_EXIST))
                .thenReturn(1);

        //execution
        Database.items.remove(1);

        //verify
        Assert.assertFalse(Database.items.containsKey(1));
        ;
    }
}
