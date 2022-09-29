package site.metacoding.firstapp.domain.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
// @MybatisTest
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
}
