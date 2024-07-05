import com.google.gson.annotations.SerializedName;
public record cambio(
        @SerializedName("time_last_update_utc") String timeLastUpdateUtc,
        @SerializedName("base_code") String baseCode,
        @SerializedName("target_code") String targetCode,
        @SerializedName("conversion_rate") float conversionRate,
        @SerializedName("conversion_result") float conversionResult) {
}

