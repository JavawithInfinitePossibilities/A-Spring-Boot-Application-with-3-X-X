package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.module.Product;
import com.sid.tutorials.spring.boot3.app.provider.ProductProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 29-12-2023
 */
@SpringBootTest(classes = Section22ReactiveTesting.class)
class Section22ReactiveTestingTest {
    @Autowired
    private ProductProvider productProvider;

    @Disabled
    @Test
    public void testMono() {
        Mono<String> mono = Mono.just("Welcome to reactive programming...");
        mono.log().map(value -> value.toUpperCase(Locale.ROOT))
                .subscribe(data -> System.out.println("Data : " + data));
    }

    @Disabled
    @Test
    public void testFlux() {
        Flux<String> flux = Flux
                .just("Welcome to reactive programming...", "I am new to reactive programming...");
        flux.log().map(value -> value.toUpperCase()).subscribe(data -> System.out.println("Data : " + data));
    }

    @Disabled
    @Test
    public void testFluxDelay() {
        try {
            Flux<String> flux = Flux
                    .just("Welcome to reactive programming...", "I am new to reactive programming...")
                    .delayElements(Duration.ofSeconds(2));
            flux.log().map(value -> value.toUpperCase()).subscribe(data -> System.out.println("Data : " + data));
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    public void testFluxItrableDelay() {
        try {
            Flux<String> flux = Flux
                    .fromIterable(Arrays.asList("Welcome to reactive programming...", "I am new to reactive programming..."))
                    .delayElements(Duration.ofSeconds(2));
            flux.log().map(value -> value.toUpperCase()).subscribe(data -> System.out.println("Data : " + data));
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    public void testFluxSubscribeDelay() {
        try {
            Flux<String> flux = Flux
                    .fromIterable(Arrays.asList("Welcome to reactive programming...", "I am new to reactive programming..."
                            , "Welcome to reactive programming 1...", "I am new to reactive programming 1..."
                            , "Welcome to reactive programming 2...", "I am new to reactive programming 2..."))
                    .delayElements(Duration.ofSeconds(2));
            flux.log().map(value -> value.toUpperCase())
                    .subscribe(new Subscriber<String>() {
                        int count = 0;
                        Subscription subscription;

                        @Override
                        public void onSubscribe(Subscription subscription) {
                            this.subscription = subscription;
                            subscription.request(Long.MAX_VALUE);
                        }

                        @Override
                        public void onNext(String s) {
                            count++;
                            if (count >= 2) {
                                count = 0;
                                this.subscription.request(2);
                            }
                            System.out.println(s);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            throwable.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("Complete");
                        }
                    });
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    public void testFluxProduct() {
        productProvider.provideProduct().subscribe(product -> System.out.println(product.toString()));
    }


    @Disabled
    @Test
    public void testStepVerifierProduct() {
        Product product = new Product(1, "Iphone".toUpperCase(), "phone", 1000);
        Product product1 = new Product(2, "Ipad".toUpperCase(), "tab", 1000);
        Product product2 = new Product(3, "Apple watch".toUpperCase(), "watch", 1000);
        Product product3 = new Product(4, "Galexy".toUpperCase(), "phone", 1000);
        Product product4 = new Product(5, "Galexy ultra".toUpperCase(), "tab", 1000);
        Product product5 = new Product(6, "Samsung watch".toUpperCase(), "watch", 1000);
        StepVerifier.create(productProvider.provideProduct())
                .expectSubscription()
                .expectNext(product).expectNext(product1).expectNext(product2)
                .expectNext(product3).expectNext(product4).expectNext(product5)
                .expectComplete()
                .verify();
    }

    @Disabled
    @Test
    public void testStepVerifierCountProduct() {
        StepVerifier.create(productProvider.provideProduct())
                .expectSubscription()
                .expectNextCount(6)
                .expectComplete()
                .verify();
    }
}