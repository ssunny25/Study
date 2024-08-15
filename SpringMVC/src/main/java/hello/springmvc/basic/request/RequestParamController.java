package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // @RestController와 같은 기능
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    /* @RequestParam : HTTP 파라미터 이름이 변수 이름과 같으면 생략 가능 */
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /** String, int, Integer 등의 단순 타입이면 @RequestParam도 생략 가능
     * @RequestParam 애노테이션이 있으면 명확하게 요청 파라미터에서 데이터를 읽는다는 것을 알 수 있으므로 상황에 맞게 생략
     * @RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 required=false를 적용
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /** 파라미터 필수 여부 required (기본값: 파라미터 필수(true))
     * "/request-param-required?username=" -> 파라미터 이름만 있고 값이 없는 경우 빈문자로 통과
     * 기본형(primitive)에 null 입력 -> null을 int에 입력하는 것은 불가능(500 예외 발생)
     * -> null을 받을 수 있는 Integer로 변경하거나, 또는 defaultValue 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /** 기본값 적용 defaultValue
     * 빈 문자에도 적용, required는 의미가 사라짐
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /** 파라미터를 Map으로 조회화기
     * @RequestParam Map -> Map(key=value)
     * @RequestParam MultiValueMap -> MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
     * 파라미터의 값이 1개가 확실하다면 Map을 사용해도 되지만, 2개 이상인 경우 MultiValueMap을 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /** @ModelAttribute
     'HelloData' 객체 생성
    → 'HelloData' 객체의 프로퍼티의 setter 호출해서 파라미터의 값을 입력(바인딩)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    //    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /** @ModelAttribute 생략 가능
     * String, int, Integer 같은 단순 타입 → @RequestParam
     * 나머지 → @ModelAttribute (argument resolver로 지정해둔 타입 제외)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}