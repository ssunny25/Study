# Servlet
### HelloServlet
서블릿 등록하기</br>
`@WebServlet(name = "servletName", urlPatterns = "/urlMapping")`
### RequestHeaderServlet
HTTP 요청 메시지를 편리하게 사용할 수 있도록 HTTP 요청 메시지 파싱 → 그 결과를 `HttpServletRequest` 객체에 담아서 제공
### RequestParamServlet
GET 쿼리 파라미터
### RequestBodyStringServlet
HTTP message body에 데이터를 직접 담아서 요청 (단순 텍스트)
### RequestBodyJsonServlet
HTTP message body에 데이터를 직접 담아서 요청 (JSON)
* * *
### ResponseHeaderServlet
HttpServletResponse
### ResponseHtmlServlet
HTTP 응답 데이터 (HTML 응답)
### ResponseJsonServlet
HTTP 응답 데이터 (API JSON)
* * *
## 회원 관리 웹 애플리케이션 (v서블릿)
### Member, MemberRepository
핵심 비즈니스 로직
### MemberFormServlet, MemberSaveServlet, MemberListServlet
서블릿
