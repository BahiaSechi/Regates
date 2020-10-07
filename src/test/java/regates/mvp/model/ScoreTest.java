package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class ScoreTest {

    @Test
    public void testToString(){
        Date d = Date.from(Instant.now());
        Score s = new Score("Loan Le meilleur", 50f, d);
        Assert.assertEquals("Loan Le meilleur\t\t\t50.0\t\t\t" + new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH).format(d), s.toString());
    }

}
