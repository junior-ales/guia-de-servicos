package br.gov.servicos.frontend;

import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.pegdown.PegDownProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Markdown {

    PegDownProcessor pegdown;

    public Markdown() {
        pegdown = new PegDownProcessor();
    }

    @SneakyThrows
    public String toHtml(ClassPathResource resource) {
        InputStreamReader input = new InputStreamReader(resource.getInputStream(), "UTF-8");

        try (BufferedReader br = new BufferedReader(input)) {
            String conteudo = br.lines().collect(joining("\n"));
            return pegdown.markdownToHtml(conteudo);
        }
    }

}
