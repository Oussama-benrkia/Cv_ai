package ai.analys.cvbrk.analyse;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
public class ServiceCallanalyse {
    private final WebClient webClient;

    public ServiceCallanalyse(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:5000").build();
    }

    public Mono<DataResponse> fetchData(int postId) {
        return webClient.get()
                .uri("/api/ana/cv/" + postId)
                .retrieve()
                .bodyToMono(DataResponse.class);
    }
}
