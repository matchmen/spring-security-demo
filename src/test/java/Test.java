import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * author: liqm
 * 2019-07-23
 */
public class Test {


    public void m(){


    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        System.out.println(bCryptPasswordEncoder.encode("123"));
    }

}
