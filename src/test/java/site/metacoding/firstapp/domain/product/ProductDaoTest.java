package site.metacoding.firstapp.domain.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import site.metacoding.firstapp.config.MyBatisConfig;

//@SpringBootTest
@Import(MyBatisConfig.class) // MyBatisTest가 MyBatisConfig를 못읽음
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실DB 사용할때
@MybatisTest
// @SpringBootTest // 모든것을 다 띄우는 것
public class ProductDaoTest {

    // 단위테스트는 생성자 주입이 안됨 (junit은 빈생성자만 때리기 때문 / dao는 메모리에 떠있으니까)
    @Autowired // 생성자 주입
    private ProductDao productDao;

    @Test
    public void findById_test() { // return을 절대 못함!
        // 매개변수는 컨트롤러가 받아야하니까 가짜로 받기!

        // given (받아야할 것)
        Integer productId = 1;

        // when (테스트)
        Product productPS = productDao.findById(productId); // DB에서 들고온 영속화된 데이터니까 PS 붙여주기

        // then (검증)
        assertEquals("바나나", productPS.getProductName());
    }

    @Test
    public void findAll_test() {
        // given -> findAll의 매개변수가 없음

        // when
        List<Product> productListPs = productDao.findAll();
        // System.out.println(productListPs.get(1).getProductName());

        // then -> size로 확인
        assertEquals(2, productListPs.size());

        // 지금은 더미데이터로 테스트하지만 실제로는 더미데이터로 테스트하면 안됨!!
    }

    // 마이바티스는 ResultSet을 자바 Entity로 변경해줄 때, 빈생성자만 호출하고 setter가 없어도 값을 매핑해주는 프레임워크.
    // Junit은 메서드 실행직전에 트랜잭션이 걸리고, 메서드 실행이 끝나면 자동 rollback됨! (stack 안에서만 돌고 commit이
    // 안됨) ==> 잘됐는지 알수가 없으니까 터트려보기
    // new해서 메모리를 띄울 때
    @Test
    public void insert_test() {
        // given
        String productName = "수박";
        Integer productPrice = 1000;
        Integer productQty = 100;
        Product product = new Product(productName, productPrice, productQty); // null값을 넣으면 터짐 = then에서 검증할 필요가 없음

        // when
        int result = productDao.insert(product);
        // then
        assertEquals(1, result);
    } // 메서드가 종료되면 rollback됨

    @Test
    public void update_test() {
        // given
        // Product product = new Product(productName, productPrice, productQty); //
        // update는 given데이터를 select(영속화)해서 만들어야 함
        Integer productId = 1;
        String productName = "수박";
        Integer productPrice = 1000;
        Integer productQty = 100;

        Product product = new Product(productName, productPrice, productQty);
        product.setProductId(productId);

        // 객체로 만들어주기, 영속화 - 있는지 검증(verify)
        Product productPS = productDao.findById(product.getProductId());

        if (product == null) {
            System.out.println("update_test() : 해당 상품이 없습니다.");
        } else {
            System.out.println("update_test() : 해당 상품이 있습니다.");
        }
        assertTrue(product == null ? false : true);

        // when
        // Product에 update 메서드 만들고, productPS.update(product);
        // product [id=1, productName="수박", productPrice=1000, productQty=100]
        // productPS [id=1, productName="바나나", productPrice=3000, productQty=98,
        // createdAt=2022-09-29]
        productPS.update(product);

        // productPS [id=1, productName="수박", productPrice=1000, productQty=100,
        // createdAt=2022-09-29]
        int result = productDao.update(productPS); // 영속화한걸 넣어줘야 함 -> productPS에는 select한 결과가 들어있음

        // then
        assertEquals(1, result);
    }

    @Test
    public void deleteById_test() {
        // given
        Integer productId = 1;

        // verify(검증)
        Product productPS = productDao.findById(productId);
        assertTrue(productPS == null ? false : true);

        // when
        int result = productDao.deleteById(productId);

        // then
        assertEquals(1, result);
    }
}
