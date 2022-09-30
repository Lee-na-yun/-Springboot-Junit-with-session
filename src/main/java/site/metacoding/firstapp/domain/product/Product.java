package site.metacoding.firstapp.domain.product;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer productQty;
    private Timestamp createdAt;

    // setter
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    private Product() {
    } // 빈생성자, MyBatis에게 필요한 것

    // insert할때 쓰는 생성자 초기화
    public Product(String productName, Integer productPrice, Integer productQty) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public void update(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productQty = product.getProductQty();
    }

}
