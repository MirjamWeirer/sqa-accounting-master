package at.campus02.exchange;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRates {
    private final BigDecimal usd_eur;
    private final BigDecimal usd_gbp;
    private final BigDecimal usd_cad;
    private final BigDecimal usd_jpy;

    public ExchangeRates(BigDecimal usd_eur, BigDecimal usd_gbp, BigDecimal usd_cad, BigDecimal usd_jpy) {
        this.usd_eur = usd_eur;
        this.usd_gbp = usd_gbp;
        this.usd_cad = usd_cad;
        this.usd_jpy = usd_jpy;
    }

    public BigDecimal eur_in_usd(BigDecimal eur) {
        return eur.divide(usd_eur, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal eur_in_gbp(BigDecimal eur) {
        return eur.multiply(usd_gbp).divide(usd_eur, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal eur_in_cad(BigDecimal eur) {
        return eur.multiply(usd_cad).divide(usd_eur, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal eur_in_jpy(BigDecimal eur) {
        return eur.multiply(usd_jpy).divide(usd_eur, 2, RoundingMode.HALF_EVEN);
    }

    public static ExchangeRates fromJson(JSONObject data) {
        if (data == null) {
            return null;
        }
        if (!data.getBoolean("success")) {
            System.out.println("Answer does not indicate success.");
        }
        JSONObject quotes = data.getJSONObject("quotes");
        return new ExchangeRates(
                quotes.getBigDecimal("USDEUR"),
                quotes.getBigDecimal("USDGBP"),
                quotes.getBigDecimal("USDCAD"),
                quotes.getBigDecimal("USDJPY")
                );
    }
}