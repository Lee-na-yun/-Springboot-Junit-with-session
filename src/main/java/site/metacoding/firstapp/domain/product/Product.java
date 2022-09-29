package site.metacoding.firstapp.domain.product;

@Setter
@Getter
import java.security.Timestamp;

public class Product {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer productQty;
    private Timestamp createdAt;
}
