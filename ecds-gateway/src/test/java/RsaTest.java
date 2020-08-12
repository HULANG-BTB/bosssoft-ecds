import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Test;

/**
 * @ClassName RsaTest
 * @Author AloneH
 * @Date 2020/8/9 21:36
 * @Description TODO
 **/
public class RsaTest {

    @Test
    public void test() {
        RSA rsa = new RSA();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println(publicKeyBase64);
        System.out.println(privateKeyBase64);
    }

}
