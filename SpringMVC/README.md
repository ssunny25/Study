# SpringMVC

### LogTestController</br>
스프링부트 로깅 라이브러리 `@Slf4j`</br>
로그 레벨 : `TRACE` > `DEBUG` > `INFO` > `WARN` > `ERROR`</br>
### MappingController
`@RequestMapping`</br>
`@GetMapping` `@PostMapping`</br>
_MappingClassController_ : 요청 매핑 API 예시</br>
### RequestHeaderController</br>
HTTP 요청 (기본, 헤더 조회)</br>
`HttpServletRequest` `HttpServletResponse` `HttpMethod` `Locale` `@RequestHeader` `@CookieValue`
### RequestParamController</br>
HTTP 요청 파라미터 조회</br>
`@RequestParam` `@ModelAttribute`</br>
### RequestBodyStringController</br>
HTTP message body를 통해 데이터(String)가 직접 넘어오는 경우<br>
`@ResponseBody` `@RequestBody` `HttpEntity`</br> 
### RequestBodyJsonController</br>
HTTP message body를 통해 데이터(JSON)가 직접 넘어오는 경우<br>
`@ResponseBody` `@RequestBody` `HttpEntity`</br>
### ResponseViewController</br>
HTTP 응답 (뷰 템플릿 사용)</br>
`return "response/hello";`</br>
### ResponseBodyController</br>
HTTP 응답 (HTTP API, 메시지 바디에 직접 입력)</br>
`ResponseEntity` `@ResponseBody`
