package br.com.api.f1.models;

public record ResponseLinks(
    String self,
    String first,
    String next,
    String prev,
    String last
) {
}
