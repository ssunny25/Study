package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ComponentScan : @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록
@ComponentScan(
        // @ComponentScan을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에
        // /AppConfig, TestConfig 등 앞서 만든 설정 정보도 함께 등록, 실행
        // excludeFilters을 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외
        // (보통 제외하지 않음, 기존 예제코드를 유지하기 위해 선택)
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AutoAppConfig {

}
