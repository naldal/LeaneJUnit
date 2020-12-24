package chapter3.HancrestTest;

import chapter3.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

public class AssertHamcrestTest {

    Account account;

    @Before
    public void create() {
        account = new Account("my big fat acct");
    }

    @Test
    public void assertFailTestWithHamcrest() {
        assertThat(new String[]{"a", "b", "c"}, equalTo(new String[]{"a", "b"}));
        assertThat(Arrays.asList(new String[] {"a"}), equalTo(Arrays.asList(new String[] {"a", "ab",})));
    }

    @Test
    public void assertSuccessTestWithHamcrest() {
        assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
        assertThat(Arrays.asList(new String[] {"a"}), equalTo(Arrays.asList(new String[] {"a"})));
    }

    @Test
    public void assertIsTest() {
        assertThat(account.getName(), is(equalTo("my big fat acct")));
    }

    @Test
    public void assertNotTest() {
        assertThat(account.getName(), not(equalTo("my big fat acct _ not equal")));
    }

    /**
     * Null 이 아닌 값을 자주 검사하는 것은 설계문제이거나 지나치게 걱정하는 것이다.
     * 별로 유용하지 않은 테스트.
     */
    @Test
    public void assertNullTest() {
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));
    }

    @Test
    public void assertFloatEqualTestWithEqualTo() {
        /**
         * 테스트를 통과할 것 같지만 실패함 (6.96 != 6.59999..)
         * float 혹은 double 양을 비교할 때는 두 수가 벌어질 수 있는 공차 또는 허용 오차를 지정해야 함.
         */
        assertThat(2.32*3, equalTo(6.96));
    }

    @Test
    public void assertFloatEqualTestWithAssertTrue() {
        /**
         * 테스트는 성공하지만 가독성은 매우 나빠짐. 이럴 때 Hamcrest 의 closeTo 메서드를 사용함.
         */
        assertTrue(Math.abs(2.32*3)-6.96 < 0.0005);
    }

    @Test
    public void assertFloatEqualTestWithCloseTo() {
        assertThat(2.32*3, closeTo(6.96, 0.0005));
    }

    @Test
    public void testWithWorthlessAssertionComment() {
        account.deposit(50);

        /**
         * 모든 JUnit 의 assert 에는 message(주석) 선택적 첫 번째 인자가 있다.
         * message 인자는 단언의 근거를 설명해 준다. 하지만...
         *
         * 아래 설명문은 테스트를 전혀 정확하게 설명하지도 않는다. 오히려 거짓말에 가까울 뿐.
         * 주석의 기대잔고는 100이지만 실제 코드의 기대값은 50이다.
         *
         * 구현 세부 사항을 설명하는 주석은 코드와 일치하지 않는 것으로 악명이 높다.
         * 필요악은 쓰지말것..
         */
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }

}
