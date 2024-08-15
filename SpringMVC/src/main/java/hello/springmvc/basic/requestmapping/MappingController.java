package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    // @RequestMapping("/hello-basic") 기본 요청(HTTP 메서드[GET, HEAD, POST, PUT, PATCH, DELETE] 모두 허용)
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /* @GetMapping @PostMapping @PutMapping @DeleteMapping @PatchMapping */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }


    /** PathVariable(경로변수) 사용
     * @PathVariable 변수명과 파라미터 이름이 같으면
     * @PathVariable 파라미터 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    // public String mappingPath(@PathVariable("userId") String userId) {
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /* @PathVariable 다중 */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }


    /** 잘 사용하진 않음
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }


    /** HTTP 헤더를 사용하여 특정 헤더 조건 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }


    /** HTTP 요청의 Content-Type 헤더를 기반으로 미디어 타입 조건 매핑
     * Content-Type
     * 컨트롤러가 소비하는 타입 consumes
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /** HTTP 요청의 Accept 헤더를 기반으로 미디어 타입 조건 매핑
     * Accept
     * 컨트롤러가 생산해내는 타입 produce
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}