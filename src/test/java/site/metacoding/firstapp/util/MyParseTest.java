package site.metacoding.firstapp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyParseTest {

    @Test
    public void changeStringToInt_test() {
        // given : ﻿리턴도 못하고 뭘 받지도 못하기 떄문에 필요한 형태
        String value = "1"; // 문자를 숫자로

        // when : 실제로 테스트하기
        int result = Integer.parseInt(value);

        // then : 검증하기
        assertEquals(1, result);
    }
}